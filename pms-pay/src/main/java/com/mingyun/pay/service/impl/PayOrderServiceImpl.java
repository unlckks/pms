package com.mingyun.pay.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import cn.hutool.core.date.DateUtil;
import com.mingyun.asset.domain.AssetHouse;
import com.mingyun.asset.mapper.AssetHouseMapper;

import com.mingyun.common.utils.SecurityUtils;
import com.mingyun.common.utils.uuid.IdUtils;
import com.mingyun.pay.constants.PayConstants;
import com.mingyun.pay.domain.PayContract;
import com.mingyun.pay.domain.PayContractFee;
import com.mingyun.pay.domain.PayFeeItem;
import com.mingyun.pay.mapper.PayContractFeeMapper;
import com.mingyun.pay.mapper.PayContractMapper;
import com.mingyun.pay.mapper.PayFeeItemMapper;
import com.mingyun.system.service.ISysConfigService;

import org.springframework.stereotype.Service;
import com.mingyun.pay.mapper.PayOrderMapper;
import com.mingyun.pay.domain.PayOrder;
import com.mingyun.pay.service.IPayOrderService;

import javax.annotation.Resource;

/**
 * 订单Service业务层处理
 *
 * @author mingyun
 * @date 2023-03-11
 */
@Service
public class PayOrderServiceImpl implements IPayOrderService {
    @Resource
    private PayOrderMapper payOrderMapper;
    @Resource
    private PayContractMapper contractMapper;
    @Resource
    private AssetHouseMapper assetHouseMapper;
    @Resource
    private PayContractFeeMapper payContractFeeMapper;
    @Resource
    private PayFeeItemMapper payFeeItemMapper;
    @Resource
    private ISysConfigService configService;


    /**
     * 查询订单
     *
     * @param id 订单主键
     * @return 订单
     */
    @Override
    public PayOrder selectPayOrderById(Long id) {
        return payOrderMapper.selectPayOrderById(id);
    }

    /**
     * 查询订单列表
     *
     * @param payOrder 订单
     * @return 订单
     */
    @Override
    public List<PayOrder> selectPayOrderList(PayOrder payOrder) {
        return payOrderMapper.selectPayOrderList(payOrder);
    }


    /**
     * 添加常规收费项目
     *
     * @param code
     * @param ids
     */
    @Override
    public void addOrderNormal(String code, long[] ids) {
        //根据合同ID查询合同信息
        PayContract contract = this.contractMapper.selectPayContractByCode(code);
        Long houseId = contract.getHouseId();
        AssetHouse assetHouse = assetHouseMapper.selectAssetHouseById(houseId);
        //根据收费ID查询当前合同的收费项目集合
        List<PayContractFee> payContractFees = this.payContractFeeMapper.queryPayContractFeeByContractIdAndItemIds(contract.getId(), ids);
        //根据收费id查询收费项目详情
        List<PayFeeItem> payFeeItems = this.payFeeItemMapper.queryPayFeeItemsByIds(ids);
        //使用stream将list转为map，payFeeItem为 key ，payFeeItem作为value，
        Map<Long, PayFeeItem> payFeeItemMap = payFeeItems.stream().collect(Collectors.toMap(PayFeeItem::getId, Function.identity()));
        String prefix = configService.selectConfigByKey(PayConstants.PAY_PREFIX_ORDER);
        //进行添加
        for (PayContractFee contractFee : payContractFees) {
            //取出收费项目的详情
            Long feeItemId = contractFee.getFeeItemId();
            PayFeeItem feeItem = payFeeItemMap.get(feeItemId);
            //进行创建订单
            PayOrder payOrder = new PayOrder();
            //设置订单号
            payOrder.setOrderId(IdUtils.createNoWithPrefix(prefix));
            //设置项目名
            payOrder.setItemName(feeItem.getName());
            //开始时间
            Date beginDate = contractFee.getNextBillDate();
            //结束时间
            Date endDate = DateUtil.offsetMonth(beginDate, feeItem.getPeriod());
            //设置
            payOrder.setBeginTime(beginDate);
            payOrder.setEndTime(endDate);
            //获取金额
            payOrder.setPrice(feeItem.getPrice());
            //设置数量
            payOrder.setNum(feeItem.getPeriod().longValue());
            //设置支付状态
            payOrder.setPayState("noPay");
            //设置商铺id
            payOrder.setHouseId(houseId);
            //设置合同id
            payOrder.setContractCode(code);
            //设置创建时间
            payOrder.setCreateTime(new Date());
            //谁创建
            payOrder.setCreateBy(SecurityUtils.getUsername());
            //进行计算金额
            clcAmt(feeItem, contract, assetHouse, payOrder);
            this.payOrderMapper.insertPayOrder(payOrder);
            //更新下次收费时间
            //TODO 没有修改下次收费时间
            contractFee.setNextBillDate(endDate);
            this.payContractFeeMapper.updatePayContractFee(contractFee);

        }

    }

    /**
     * 计算金额方法
     *
     * @param feeItem
     * @param contract
     * @param assetHouse
     * @param payOrder
     */
    private void clcAmt(PayFeeItem feeItem, PayContract contract, AssetHouse assetHouse, PayOrder payOrder) {
        switch (feeItem.getNumType()) {
            //按户数收费
            case PayConstants.FEE_ITEM_NUM_TYPE_FLAT:
                payOrder.setAmt(new BigDecimal(feeItem.getPrice().doubleValue() * feeItem.getPeriod()));
                break;
            case PayConstants.FEE_ITEM_NUM_TYPE_PEOPLE:
                break;
            case PayConstants.FEE_ITEM_NUM_TYPE_FLOOR:
                break;
                //按占地面积收费
            case PayConstants.FEE_ITEM_NUM_TYPE_BUILDING_AREA:
                payOrder.setAmt(new BigDecimal(feeItem.getPrice().doubleValue() * feeItem.getPeriod() * assetHouse.getBuildingSquare().doubleValue()));
                break;
                //按使用面积收费
            case PayConstants.FEE_ITEM_NUM_TYPE_USER_AREA:
                payOrder.setAmt(new BigDecimal(feeItem.getPrice().doubleValue() * feeItem.getPeriod() * assetHouse.getUseSquare().doubleValue()));
                break;
            case PayConstants.FEE_ITEM_NUM_TYPE_METER:
                break;
                //按租金收费
            case PayConstants.FEE_ITEM_NUM_TYPE_RENT_FEE:
                payOrder.setPrice(contract.getRentFee());
                payOrder.setAmt(new BigDecimal(contract.getRentFee().doubleValue() * feeItem.getPeriod()));
                break;
        }

    }
}
