package com.powernode.pay.service.impl;

import java.util.List;
import com.powernode.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.powernode.pay.mapper.PayFeeItemMapper;
import com.powernode.pay.domain.PayFeeItem;
import com.powernode.pay.service.IPayFeeItemService;

/**
 * 收费项目管理Service业务层处理
 * 
 * @author mingyun
 * @date 2023-03-06
 */
@Service
public class PayFeeItemServiceImpl implements IPayFeeItemService 
{
    @Autowired
    private PayFeeItemMapper payFeeItemMapper;

    /**
     * 查询收费项目管理
     * 
     * @param id 收费项目管理主键
     * @return 收费项目管理
     */
    @Override
    public PayFeeItem selectPayFeeItemById(Long id)
    {
        return payFeeItemMapper.selectPayFeeItemById(id);
    }

    /**
     * 查询收费项目管理列表
     * 
     * @param payFeeItem 收费项目管理
     * @return 收费项目管理
     */
    @Override
    public List<PayFeeItem> selectPayFeeItemList(PayFeeItem payFeeItem)
    {
        return payFeeItemMapper.selectPayFeeItemList(payFeeItem);
    }

    /**
     * 新增收费项目管理
     * 
     * @param payFeeItem 收费项目管理
     * @return 结果
     */
    @Override
    public int insertPayFeeItem(PayFeeItem payFeeItem)
    {
        payFeeItem.setCreateTime(DateUtils.getNowDate());
        return payFeeItemMapper.insertPayFeeItem(payFeeItem);
    }

    /**
     * 修改收费项目管理
     * 
     * @param payFeeItem 收费项目管理
     * @return 结果
     */
    @Override
    public int updatePayFeeItem(PayFeeItem payFeeItem)
    {
        payFeeItem.setUpdateTime(DateUtils.getNowDate());
        return payFeeItemMapper.updatePayFeeItem(payFeeItem);
    }

    /**
     * 批量删除收费项目管理
     * 
     * @param ids 需要删除的收费项目管理主键
     * @return 结果
     */
    @Override
    public int deletePayFeeItemByIds(Long[] ids)
    {
        return payFeeItemMapper.deletePayFeeItemByIds(ids);
    }

    /**
     * 删除收费项目管理信息
     * 
     * @param id 收费项目管理主键
     * @return 结果
     */
    @Override
    public int deletePayFeeItemById(Long id)
    {
        return payFeeItemMapper.deletePayFeeItemById(id);
    }
}
