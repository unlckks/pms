package com.powernode.pay.mapper;

import java.util.List;
import com.powernode.pay.domain.PayFeeItem;

/**
 * 收费项目管理Mapper接口
 * 
 * @author mingyun
 * @date 2023-03-06
 */
public interface PayFeeItemMapper 
{
    /**
     * 查询收费项目管理
     * 
     * @param id 收费项目管理主键
     * @return 收费项目管理
     */
    public PayFeeItem selectPayFeeItemById(Long id);

    /**
     * 查询收费项目管理列表
     * 
     * @param payFeeItem 收费项目管理
     * @return 收费项目管理集合
     */
    public List<PayFeeItem> selectPayFeeItemList(PayFeeItem payFeeItem);

    /**
     * 新增收费项目管理
     * 
     * @param payFeeItem 收费项目管理
     * @return 结果
     */
    public int insertPayFeeItem(PayFeeItem payFeeItem);

    /**
     * 修改收费项目管理
     * 
     * @param payFeeItem 收费项目管理
     * @return 结果
     */
    public int updatePayFeeItem(PayFeeItem payFeeItem);

    /**
     * 删除收费项目管理
     * 
     * @param id 收费项目管理主键
     * @return 结果
     */
    public int deletePayFeeItemById(Long id);

    /**
     * 批量删除收费项目管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePayFeeItemByIds(Long[] ids);
}
