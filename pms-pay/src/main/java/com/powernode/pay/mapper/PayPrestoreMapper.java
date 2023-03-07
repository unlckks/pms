package com.powernode.pay.mapper;

import java.util.List;
import com.powernode.pay.domain.PayPrestore;

/**
 * 费用预存Mapper接口
 *
 * @author mingyun
 * @date 2023-03-07
 */
public interface PayPrestoreMapper 
{
    /**
     * 查询费用预存
     * 
     * @param id 费用预存主键
     * @return 费用预存
     */
    public PayPrestore selectPayPrestoreById(Long id);

    /**
     * 查询费用预存列表
     * 
     * @param payPrestore 费用预存
     * @return 费用预存集合
     */
    public List<PayPrestore> selectPayPrestoreList(PayPrestore payPrestore);

    /**
     * 新增费用预存
     * 
     * @param payPrestore 费用预存
     * @return 结果
     */
    public int insertPayPrestore(PayPrestore payPrestore);

    /**
     * 修改费用预存
     * 
     * @param payPrestore 费用预存
     * @return 结果
     */
    public int updatePayPrestore(PayPrestore payPrestore);

    /**
     * 删除费用预存
     * 
     * @param id 费用预存主键
     * @return 结果
     */
    public int deletePayPrestoreById(Long id);

    /**
     * 批量删除费用预存
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePayPrestoreByIds(Long[] ids);
}
