package com.mingyun.pay.service.impl;

import java.util.List;

import com.mingyun.pay.mapper.PayFeeSettingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mingyun.pay.domain.PayFeeSetting;
import com.mingyun.pay.service.IPayFeeSettingService;

/**
 * 租售合同管理Service业务层处理
 * 
 * @author mingyun
 * @date 2023-03-09
 */
@Service
public class PayFeeSettingServiceImpl implements IPayFeeSettingService 
{
    @Autowired
    private PayFeeSettingMapper payFeeSettingMapper;

    /**
     * 查询租售合同管理
     * 
     * @param id 租售合同管理主键
     * @return 租售合同管理
     */
    @Override
    public PayFeeSetting selectPayFeeSettingById(Long id)
    {
        return payFeeSettingMapper.selectPayFeeSettingById(id);
    }

    /**
     * 查询租售合同管理列表
     * 
     * @param payFeeSetting 租售合同管理
     * @return 租售合同管理
     */
    @Override
    public List<PayFeeSetting> selectPayFeeSettingList(PayFeeSetting payFeeSetting)
    {
        return payFeeSettingMapper.selectPayFeeSettingList(payFeeSetting);
    }

    /**
     * 新增租售合同管理
     * 
     * @param payFeeSetting 租售合同管理
     * @return 结果
     */
    @Override
    public int insertPayFeeSetting(PayFeeSetting payFeeSetting)
    {
        return payFeeSettingMapper.insertPayFeeSetting(payFeeSetting);
    }

    /**
     * 修改租售合同管理
     * 
     * @param payFeeSetting 租售合同管理
     * @return 结果
     */
    @Override
    public int updatePayFeeSetting(PayFeeSetting payFeeSetting)
    {
        return payFeeSettingMapper.updatePayFeeSetting(payFeeSetting);
    }

    /**
     * 批量删除租售合同管理
     * 
     * @param ids 需要删除的租售合同管理主键
     * @return 结果
     */
    @Override
    public int deletePayFeeSettingByIds(Long[] ids)
    {
        return payFeeSettingMapper.deletePayFeeSettingByIds(ids);
    }

    /**
     * 删除租售合同管理信息
     * 
     * @param id 租售合同管理主键
     * @return 结果
     */
    @Override
    public int deletePayFeeSettingById(Long id)
    {
        return payFeeSettingMapper.deletePayFeeSettingById(id);
    }
}
