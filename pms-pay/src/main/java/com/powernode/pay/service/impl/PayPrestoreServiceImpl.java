package com.powernode.pay.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.powernode.common.utils.DateUtils;
import com.powernode.pay.constants.PayConstants;
import com.powernode.pay.domain.PayPrestoreAccount;
import com.powernode.pay.mapper.PayPrestoreAccountMapper;
import org.springframework.stereotype.Service;
import com.powernode.pay.mapper.PayPrestoreMapper;
import com.powernode.pay.domain.PayPrestore;
import com.powernode.pay.service.IPayPrestoreService;

import javax.annotation.Resource;

/**
 * 费用预存Service业务层处理
 *
 * @author mingyun
 * @date 2023-03-07
 */
@Service
public class PayPrestoreServiceImpl implements IPayPrestoreService {
    @Resource
    private PayPrestoreMapper payPrestoreMapper;

    @Resource
    private PayPrestoreAccountMapper payPrestoreAccountMapper;

    /**
     * 查询费用预存
     *
     * @param id 费用预存主键
     * @return 费用预存
     */
    @Override
    public PayPrestore selectPayPrestoreById(Long id) {
        return payPrestoreMapper.selectPayPrestoreById(id);
    }

    /**
     * 查询费用预存列表
     *
     * @param payPrestore 费用预存
     * @return 费用预存
     */
    @Override
    public List<PayPrestore> selectPayPrestoreList(PayPrestore payPrestore) {
        return payPrestoreMapper.selectPayPrestoreList(payPrestore);
    }

    /**
     * 新增费用预存
     *
     * @param payPrestore 费用预存
     * @return 结果
     */
    @Override
    public int insertPayPrestore(PayPrestore payPrestore) {
        payPrestore.setCreateTime(DateUtils.getNowDate());
        //业主ID
        Long feeUserId = payPrestore.getFeeUserId();
        //业主名称
        String feeUserName = payPrestore.getFeeUserName();
        //收费项目ID
        String feeItemId = payPrestore.getFeeItemId();
        //收费项目名称
        String feeItemName = payPrestore.getFeeItemName();
        //是否指定收费项目
        String useFeeItem = payPrestore.getUseFeeItem();
        //代表为未指定项目
        if (useFeeItem == null) {
            useFeeItem = PayConstants.N;
        }
        PayPrestoreAccount account = null;
        if (useFeeItem.equals(PayConstants.Y)) {
            //说明有收费项
            account = this.payPrestoreAccountMapper.selectUseFeeItemAccount(feeItemId, feeUserId);
        } else {
            //没有收费项
            account = this.payPrestoreAccountMapper.selectNoUseFeeItemAccount(feeUserId);
        }
        //如果account为null说明还没有开户
        if (account == null) {
            account = new PayPrestoreAccount();
            account.setFeeItemId(useFeeItem.equals(PayConstants.N) ? null : Long.valueOf(feeItemId));
            account.setFeeItemName(feeItemName);
            account.setFeeUserId(feeUserId);
            account.setFeeUserName(feeUserName);
            account.setAmt(payPrestore.getAmt());
            account.setCreateTime(new Date());
            account.setCreateBy(payPrestore.getCreateBy());
            //进行保存
            this.payPrestoreAccountMapper.insertPayPrestoreAccount(account);
        } else {
            BigDecimal amt = account.getAmt();
            account.setAmt(amt.add(payPrestore.getAmt()));
            //进行更新
            this.payPrestoreAccountMapper.updatePayPrestoreAccount(account);
        }
        //进行保存
        return payPrestoreMapper.insertPayPrestore(payPrestore);
    }

    /**
     * 修改费用预存
     *
     * @param payPrestore 费用预存
     * @return 结果
     */
    @Override
    public int updatePayPrestore(PayPrestore payPrestore) {
        payPrestore.setUpdateTime(DateUtils.getNowDate());
        return payPrestoreMapper.updatePayPrestore(payPrestore);
    }

    /**
     * 批量删除费用预存
     *
     * @param ids 需要删除的费用预存主键
     * @return 结果
     */
    @Override
    public int deletePayPrestoreByIds(Long[] ids) {
        return payPrestoreMapper.deletePayPrestoreByIds(ids);
    }

    /**
     * 删除费用预存信息
     *
     * @param id 费用预存主键
     * @return 结果
     */
    @Override
    public int deletePayPrestoreById(Long id) {
        return payPrestoreMapper.deletePayPrestoreById(id);
    }
}
