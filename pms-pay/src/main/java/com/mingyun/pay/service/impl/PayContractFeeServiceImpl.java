package com.mingyun.pay.service.impl;

import java.util.List;
import com.mingyun.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.mingyun.pay.mapper.PayContractFeeMapper;
import com.mingyun.pay.domain.PayContractFee;
import com.mingyun.pay.service.IPayContractFeeService;

import javax.annotation.Resource;

/**
 * 合同收费项目Service业务层处理
 * 
 * @author mingyun
 * @date 2023-03-09
 */
@Service
public class PayContractFeeServiceImpl implements IPayContractFeeService 
{
    @Resource
    private PayContractFeeMapper payContractFeeMapper;

    /**
     * 查询合同收费项目
     * 
     * @param id 合同收费项目主键
     * @return 合同收费项目
     */
    @Override
    public PayContractFee selectPayContractFeeById(Long id)
    {
        return payContractFeeMapper.selectPayContractFeeById(id);
    }

    /**
     * 查询合同收费项目列表
     * 
     * @param payContractFee 合同收费项目
     * @return 合同收费项目
     */
    @Override
    public List<PayContractFee> selectPayContractFeeList(PayContractFee payContractFee)
    {
        return payContractFeeMapper.selectPayContractFeeList(payContractFee);
    }

    /**
     * 新增合同收费项目
     * 
     * @param payContractFee 合同收费项目
     * @return 结果
     */
    @Override
    public int insertPayContractFee(PayContractFee payContractFee)
    {
        payContractFee.setCreateTime(DateUtils.getNowDate());
        return payContractFeeMapper.insertPayContractFee(payContractFee);
    }

    /**
     * 修改合同收费项目
     * 
     * @param payContractFee 合同收费项目
     * @return 结果
     */
    @Override
    public int updatePayContractFee(PayContractFee payContractFee)
    {
        payContractFee.setUpdateTime(DateUtils.getNowDate());
        return payContractFeeMapper.updatePayContractFee(payContractFee);
    }

    /**
     * 批量删除合同收费项目
     * 
     * @param ids 需要删除的合同收费项目主键
     * @return 结果
     */
    @Override
    public int deletePayContractFeeByIds(Long[] ids)
    {
        return payContractFeeMapper.deletePayContractFeeByIds(ids);
    }

    /**
     * 删除合同收费项目信息
     * 
     * @param id 合同收费项目主键
     * @return 结果
     */
    @Override
    public int deletePayContractFeeById(Long id)
    {
        return payContractFeeMapper.deletePayContractFeeById(id);
    }
}
