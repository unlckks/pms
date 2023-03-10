package com.powernode.pay.service.impl;

import java.util.List;

import com.powernode.common.core.domain.AjaxResult;
import com.powernode.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.powernode.pay.mapper.PayFeeItemMapper;
import com.powernode.pay.domain.PayFeeItem;
import com.powernode.pay.service.IPayFeeItemService;

import javax.annotation.Resource;

/**
 * 收费项目管理Service业务层处理
 * 
 * @author powernode
 * @date 2023-03-06
 */
@Service
public class PayFeeItemServiceImpl implements IPayFeeItemService 
{
    @Resource
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

    /**
     * 根据项目类型查询收费项目
     * @param type  house temp deposit
     * @return
     */
    @Override
    public List<PayFeeItem> queryPayFeeItemByType(String type) {
        PayFeeItem payFeeItem=new PayFeeItem();
        payFeeItem.setType(type);
        return payFeeItemMapper.selectPayFeeItemList(payFeeItem);
    }

    /**
     * 根据类型查询已配置默认收费项目
     * @param type
     * @return
     */
    @Override
    public List<PayFeeItem> queryDefaultPayFeeItemByType(String type) {
        return this.payFeeItemMapper.queryDefaultPayFeeItemByType(type);
    }

    /**
     * 删除默认收费项目
     * @param id
     * @param type
     * @return
     */
    @Override
    public int deleteDefaultPayFeeItemByIdAndType(Long id, String type) {
        return this.payFeeItemMapper.deleteDefaultPayFeeItemByIdAndType(id,type);
    }

    @Override
    public int addDefaultPayItemConfig(String type, Long[] ids) {
        return this.payFeeItemMapper.addDefaultPayItemConfig(type,ids);
    }
}
