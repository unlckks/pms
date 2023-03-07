package com.powernode.pay.service;

import java.util.List;
import com.powernode.pay.domain.PayPrestore;
import com.powernode.pay.domain.PayPrestoreAccount;

/**
 * 费用预存Service接口
 * 
 * @author powernode
 * @date 2023-03-07
 */
public interface IPayPrestoreService 
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
     * 批量删除费用预存
     * 
     * @param ids 需要删除的费用预存主键集合
     * @return 结果
     */
    public int deletePayPrestoreByIds(Long[] ids);

    /**
     * 删除费用预存信息
     * 
     * @param id 费用预存主键
     * @return 结果
     */
    public int deletePayPrestoreById(Long id);

    /**
     * 根据业主ID查询业主的所有账户的信息
     * @return
     */
    List<PayPrestoreAccount> queryPayPrestoreAccountByUserOwnerId(Long ownerId);

    /**
     * 退存款
     * @param type
     * @param accounts
     * @param username
     */
    void refundPrestoreAccount(String type, PayPrestoreAccount[] accounts, String username);
}
