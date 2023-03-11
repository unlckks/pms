package com.powernode.pay.mapper;

import java.util.List;

import com.powernode.common.core.domain.AjaxResult;
import com.powernode.pay.domain.PayFeeItem;
import org.apache.ibatis.annotations.Param;

/**
 * 收费项目管理Mapper接口
 * 
 * @author powernode
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

    /**
     * 根据类型查询已配置默认收费项目
     *
     * @param type
     * @return
     */
    List<PayFeeItem> queryDefaultPayFeeItemByType(@Param("type") String type);

    /**
     * 删除默认收费项目
     * @param id
     * @param type
     * @return
     */
    int deleteDefaultPayFeeItemByIdAndType(@Param("id") Long id, @Param("type") String type);

    /**
     * 进行添加收费项目
     *
     * @param type
     * @param ids
     * @return
     */
    int addDefaultPayItemConfig(@Param("type") String type, @Param("ids") Long[] ids);

    /**
     * 根据合同编号查询当前合同所有收费项目
     * @param id
     * @return
     */
    List<PayFeeItem> queryContractFeeItemsByContractId(@Param("id") Long id);

    /**
     * 根据收费id查询收费项目详情
     *
     * @param ids
     * @return
     */
    List<PayFeeItem> queryPayFeeItemsByIds(@Param("ids") long[] ids);
}
