package com.mingyun.pay.service.impl;

import java.util.List;
import com.mingyun.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mingyun.pay.mapper.PayDepositMapper;
import com.mingyun.pay.domain.PayDeposit;
import com.mingyun.pay.service.IPayDepositService;

/**
 * 押金Service业务层处理
 * 
 * @author mingyun
 * @date 2023-03-06
 */
@Service
public class PayDepositServiceImpl implements IPayDepositService 
{
    @Autowired
    private PayDepositMapper payDepositMapper;

    /**
     * 查询押金
     * 
     * @param id 押金主键
     * @return 押金
     */
    @Override
    public PayDeposit selectPayDepositById(Long id)
    {
        return payDepositMapper.selectPayDepositById(id);
    }

    /**
     * 查询押金列表
     * 
     * @param payDeposit 押金
     * @return 押金
     */
    @Override
    public List<PayDeposit> selectPayDepositList(PayDeposit payDeposit)
    {
        return payDepositMapper.selectPayDepositList(payDeposit);
    }

    /**
     * 新增押金
     * 
     * @param payDeposit 押金
     * @return 结果
     */
    @Override
    public int insertPayDeposit(PayDeposit payDeposit)
    {
        payDeposit.setCreateTime(DateUtils.getNowDate());
        return payDepositMapper.insertPayDeposit(payDeposit);
    }

    /**
     * 修改押金
     * 
     * @param payDeposit 押金
     * @return 结果
     */
    @Override
    public int updatePayDeposit(PayDeposit payDeposit)
    {
        payDeposit.setUpdateTime(DateUtils.getNowDate());
        return payDepositMapper.updatePayDeposit(payDeposit);
    }

    /**
     * 批量删除押金
     * 
     * @param ids 需要删除的押金主键
     * @return 结果
     */
    @Override
    public int deletePayDepositByIds(Long[] ids)
    {
        return payDepositMapper.deletePayDepositByIds(ids);
    }

    /**
     * 删除押金信息
     * 
     * @param id 押金主键
     * @return 结果
     */
    @Override
    public int deletePayDepositById(Long id)
    {
        return payDepositMapper.deletePayDepositById(id);
    }
}
