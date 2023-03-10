package com.powernode.pay.service;

import java.util.List;
import com.powernode.pay.domain.PayFeeSetting;

/**
 * 租售合同管理Service接口
 * 
 * @author mingyun
 * @date 2023-03-09
 */
public interface IPayFeeSettingService 
{
    /**
     * 查询租售合同管理
     * 
     * @param id 租售合同管理主键
     * @return 租售合同管理
     */
    public PayFeeSetting selectPayFeeSettingById(Long id);

    /**
     * 查询租售合同管理列表
     * 
     * @param payFeeSetting 租售合同管理
     * @return 租售合同管理集合
     */
    public List<PayFeeSetting> selectPayFeeSettingList(PayFeeSetting payFeeSetting);

    /**
     * 新增租售合同管理
     * 
     * @param payFeeSetting 租售合同管理
     * @return 结果
     */
    public int insertPayFeeSetting(PayFeeSetting payFeeSetting);

    /**
     * 修改租售合同管理
     * 
     * @param payFeeSetting 租售合同管理
     * @return 结果
     */
    public int updatePayFeeSetting(PayFeeSetting payFeeSetting);

    /**
     * 批量删除租售合同管理
     * 
     * @param ids 需要删除的租售合同管理主键集合
     * @return 结果
     */
    public int deletePayFeeSettingByIds(Long[] ids);

    /**
     * 删除租售合同管理信息
     * 
     * @param id 租售合同管理主键
     * @return 结果
     */
    public int deletePayFeeSettingById(Long id);
}
