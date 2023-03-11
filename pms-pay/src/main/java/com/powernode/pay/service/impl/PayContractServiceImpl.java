package com.powernode.pay.service.impl;

import java.util.Date;
import java.util.List;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.util.UUIDUtils;
import com.powernode.asset.domain.AssetHouse;
import com.powernode.asset.mapper.AssetHouseMapper;
import com.powernode.common.utils.DateUtils;
import com.powernode.common.utils.SecurityUtils;
import com.powernode.common.utils.uuid.IdUtils;
import com.powernode.owner.domain.OwnerUser;
import com.powernode.owner.mapper.OwnerUserMapper;
import com.powernode.pay.constants.PayConstants;
import com.powernode.pay.domain.PayContractFee;
import com.powernode.pay.entity.dto.ContractDto;
import com.powernode.pay.entity.query.ContractQuery;
import com.powernode.pay.entity.vo.ContractVo;
import com.powernode.pay.mapper.PayContractFeeMapper;
import com.powernode.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.powernode.pay.mapper.PayContractMapper;
import com.powernode.pay.domain.PayContract;
import com.powernode.pay.service.IPayContractService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 出租出售合同Service业务层处理
 *
 * @author mingyun
 * @date 2023-03-09
 */
@Service
public class PayContractServiceImpl implements IPayContractService {
    @Resource
    private PayContractMapper payContractMapper;

    @Resource
    private AssetHouseMapper houseMapper;

    @Resource
    private OwnerUserMapper ownerUserMapper;

    @Resource
    private PayContractFeeMapper payContractFeeMapper;
    @Resource
    private ISysConfigService configService;

    /**
     * 查询出租出售合同列表
     */
    @Override
    public List<ContractVo> selectPayContractList(ContractVo contractVo) {
        return this.payContractMapper.queryPayContractList(contractVo);
    }

    /**
     * 添加合同
     *
     * @param contractDto
     */
    @Override
    @Transactional
    public void addContract(ContractDto contractDto) {
        //进行获取合同类型
        String contractType = contractDto.getContractType();
        //进行更新合同
        Long ownerId = contractDto.getOwnerId();
        //获取商铺户主
        OwnerUser ownerUser = this.ownerUserMapper.selectOwnerUserById(ownerId);
        if (ownerUser == null) {
            throw new RuntimeException("业主不存在");
        }
        //如果状态相同
        if (contractType.equals(PayConstants.CONTRACT_TYPE_RENT)) {
            ownerUser.setRentedCount(ownerUser.getOwnCount() + 1);
        } else {
            ownerUser.setOwnCount(ownerUser.getOwnCount() + 1);
        }
        //最后进行更新
        ownerUser.setUpdateBy(SecurityUtils.getUsername());
        this.ownerUserMapper.updateOwnerUser(ownerUser);

        //再进行更新第二个表的内容商铺信息
        AssetHouse assetHouse = houseMapper.selectAssetHouseById(contractDto.getHouseId());
        if (assetHouse == null) {
            throw new RuntimeException("商铺不存在!");
        }
        //进行出租
        if (contractType.equals(PayConstants.CONTRACT_TYPE_RENT)) {
            //计算结束时间
            Date beginDate = contractDto.getBeginDate();
            Date endTime = DateUtil.offsetMonth(beginDate, Integer.valueOf(contractDto.getPeriod())).toJdkDate();
            assetHouse.setState(PayConstants.CONTRACT_TYPE_RENT);
            assetHouse.setStateCompany(ownerUser.getCompany());
            assetHouse.setStateEndTime(endTime);
        } else {
            //出售
            assetHouse.setState(PayConstants.CONTRACT_TYPE_SALE);
            assetHouse.setStateCompany(ownerUser.getCompany());
            assetHouse.setStateEndTime(null);
        }
        //更新
        this.houseMapper.updateAssetHouse(assetHouse);
        //进行添加更新信息
        PayContract payContract = new PayContract();
        payContract.setContractType(contractType);
        payContract.setCode(IdUtils.createNoWithPrefix(configService.selectConfigByKey(PayConstants.PAY_PREFIX_HT)));
        payContract.setHouseId(contractDto.getHouseId());
        payContract.setOwnerId(contractDto.getOwnerId());
        payContract.setUserTrade(contractDto.getUserTrade());
        payContract.setUserTradeDetail(contractDto.getUserTradeDetail());
        Date beginDate = contractDto.getBeginDate();
        payContract.setBeginDate(beginDate);
        payContract.setCreateTime(new Date());
        payContract.setCreateBy(SecurityUtils.getUsername());
        if (contractType.equals(PayConstants.CONTRACT_TYPE_RENT)) {
            Date endTime = DateUtil.offsetMonth(beginDate, Integer.valueOf(contractDto.getPeriod())).toJdkDate();
            payContract.setBeginDate(beginDate);
            payContract.setPeriod(contractDto.getPeriod());
            payContract.setRentFee(contractDto.getRentFee());
            payContract.setEndDate(endTime);
        }
        payContractMapper.insertPayContract(payContract);
        //添加合同信息
        Long[] contractFeeIds = contractDto.getContractFeeIds();
        if (contractFeeIds != null && contractFeeIds.length > 0) {
            Date endTime = null;
            if (contractType.equals(PayConstants.CONTRACT_TYPE_RENT)) {
                endTime = DateUtil.offsetMonth(beginDate, Integer.valueOf(contractDto.getPeriod())).toJdkDate();
            }
            for (Long contractFeeId : contractFeeIds) {
                PayContractFee payContractFee = new PayContractFee();
                payContractFee.setContractId(payContract.getId().toString());
                payContractFee.setFeeItemId(contractFeeId);
                payContractFee.setBeginDate(beginDate);
                //进行设置下一个周期的时间
                payContractFee.setNextBillDate(beginDate);
                //是出租就给结束时间
                payContractFee.setEndDate(contractType.equals(PayConstants.CONTRACT_TYPE_RENT) ? endTime : null);
                payContractFee.setTimes(0L);
                payContractFee.setCreateBy(SecurityUtils.getUsername());
                payContractFee.setCreateTime(new Date());
                payContractFeeMapper.insertPayContractFee(payContractFee);
            }
        }
    }

    /**
     * 根据合同code删除合同
     *
     * @param code
     * @return
     */
    @Transactional
    @Override
    public int cancelContractByCode(String code) {
        //先根据合同code删除
        PayContract payContract = this.payContractMapper.selectPayContractByCode(code);
        if (payContract == null) {
            throw new RuntimeException("当前合同编号未查询到合同信息");
        }
        payContract.setState(PayConstants.PAY_CONTRACT_STATE_2);
        //进行更新商铺状态
        Long houseId = payContract.getHouseId();
        //查询house
        AssetHouse assetHouse = this.houseMapper.selectAssetHouseById(houseId);
        assetHouse.setState(PayConstants.ASSET_HOUSE_STATE_EMPTY);
        //再进行更新
        this.houseMapper.updateAssetHouse(assetHouse);
        //更新业主拥有的数量
        Long ownerId = payContract.getOwnerId();
        //根据ID查询业主
        OwnerUser ownerUser = this.ownerUserMapper.selectOwnerUserById(ownerId);
        if (payContract.getContractType().equals(PayConstants.CONTRACT_TYPE_RENT)) {
            ownerUser.setRentedCount(ownerUser.getRentedCount() - 1);
        } else {
            ownerUser.setOwnCount(ownerUser.getOwnCount() - 1);
        }
        this.ownerUserMapper.updateOwnerUser(ownerUser);
        return this.payContractMapper.updatePayContract(payContract);
    }

    /**
     * 查询可生成的编号
     *
     * @param contractQuery
     * @return
     */
    @Override
    public List<ContractVo> selectBillPayContractList(ContractQuery contractQuery) {
        return this.payContractMapper.queryBillPayContractList(contractQuery);
    }

    /**
     * 根据合同编号查询当前合同所有收费项目
     *
     * @param code
     * @return
     */
    @Override
    public PayContract queryPayContractByCode(String code) {
        return this.payContractMapper.selectPayContractByCode(code);
    }

    /**
     * 据商铺ID和状态查询合同信息
     * @param houseId
     * @param
     * @return
     */
    @Override
    public PayContract selectPayContractByHouseId(Long houseId, String contractState) {
        return this.payContractMapper.selectPayContractByHouseId(houseId ,contractState);
    }
}

