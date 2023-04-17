package com.mingyun.pay.mapper;

import java.util.List;
import com.mingyun.pay.domain.PayDeposit;

/**
 * 押金Mapper接口
 * 
 * @author mingyun
 * @date 2023-03-06
 */
public interface PayDepositMapper 
{
    /**
     * 查询押金
     * 
     * @param id 押金主键
     * @return 押金
     */
    public PayDeposit selectPayDepositById(Long id);

    /**
     * 查询押金列表
     * 
     * @param payDeposit 押金
     * @return 押金集合
     */
    public List<PayDeposit> selectPayDepositList(PayDeposit payDeposit);

    /**
     * 新增押金
     * 
     * @param payDeposit 押金
     * @return 结果
     */
    public int insertPayDeposit(PayDeposit payDeposit);

    /**
     * 修改押金
     * 
     * @param payDeposit 押金
     * @return 结果
     */
    public int updatePayDeposit(PayDeposit payDeposit);

    /**
     * 删除押金
     * 
     * @param id 押金主键
     * @return 结果
     */
    public int deletePayDepositById(Long id);

    /**
     * 批量删除押金
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePayDepositByIds(Long[] ids);
}
