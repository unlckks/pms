package com.powernode.pay.mapper;

import java.util.List;

import com.powernode.pay.domain.PayPrestoreAccount;
import org.apache.ibatis.annotations.Param;

/**
 * 预存款余额账户Mapper接口
 *
 * @author powernode
 * @date 2023-03-07
 */
public interface PayPrestoreAccountMapper {
    /**
     * 查询预存款余额账户
     *
     * @param id 预存款余额账户主键
     * @return 预存款余额账户
     */
    public PayPrestoreAccount selectPayPrestoreAccountById(Long id);

    /**
     * 查询预存款余额账户列表
     *
     * @param payPrestoreAccount 预存款余额账户
     * @return 预存款余额账户集合
     */
    public List<PayPrestoreAccount> selectPayPrestoreAccountList(PayPrestoreAccount payPrestoreAccount);

    /**
     * 新增预存款余额账户
     *
     * @param payPrestoreAccount 预存款余额账户
     * @return 结果
     */
    public int insertPayPrestoreAccount(PayPrestoreAccount payPrestoreAccount);

    /**
     * 修改预存款余额账户
     *
     * @param payPrestoreAccount 预存款余额账户
     * @return 结果
     */
    public int updatePayPrestoreAccount(PayPrestoreAccount payPrestoreAccount);

    /**
     * 删除预存款余额账户
     *
     * @param id 预存款余额账户主键
     * @return 结果
     */
    public int deletePayPrestoreAccountById(Long id);

    /**
     * 批量删除预存款余额账户
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePayPrestoreAccountByIds(Long[] ids);

    /**
     * 根据业主ID和收费项目ID查询账户
     *
     * @param feeItemId
     * @param feeUserId
     * @return
     */
    PayPrestoreAccount selectUseFeeItemAccount(@Param("feeItemId") String feeItemId, @Param("feeUserId") Long feeUserId);

    /**
     * 根据业主ID查询账户
     *
     * @param feeUserId
     * @return
     */
    PayPrestoreAccount selectNoUseFeeItemAccount(@Param("feeUserId") Long feeUserId);

    /**
     * 根据id查询业主存款信息
     * @param ownerId
     * @return
     */
    List<PayPrestoreAccount> queryPayPrestoreAccountByUserOwnerId(@Param("ownerId") Long ownerId);
}
