package com.powernode.park.service.impl;

import java.util.List;
import com.powernode.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.powernode.park.mapper.ParkChargingRulesMapper;
import com.powernode.park.domain.ParkChargingRules;
import com.powernode.park.service.IParkChargingRulesService;

import javax.annotation.Resource;

/**
 * 停车场计费规则Service业务层处理
 * 
 * @author mingyun
 * @date 2023-03-08
 */
@Service
public class ParkChargingRulesServiceImpl implements IParkChargingRulesService 
{
    @Resource
    private ParkChargingRulesMapper parkChargingRulesMapper;

    /**
     * 查询停车场计费规则
     * 
     * @param id 停车场计费规则主键
     * @return 停车场计费规则
     */
    @Override
    public ParkChargingRules selectParkChargingRulesById(String id)
    {
        return parkChargingRulesMapper.selectParkChargingRulesById(id);
    }

    /**
     * 查询停车场计费规则列表
     * 
     * @param parkChargingRules 停车场计费规则
     * @return 停车场计费规则
     */
    @Override
    public List<ParkChargingRules> selectParkChargingRulesList(ParkChargingRules parkChargingRules)
    {
        return parkChargingRulesMapper.selectParkChargingRulesList(parkChargingRules);
    }

    /**
     * 新增停车场计费规则
     * 
     * @param parkChargingRules 停车场计费规则
     * @return 结果
     */
    @Override
    public int insertParkChargingRules(ParkChargingRules parkChargingRules)
    {
        parkChargingRules.setCreateTime(DateUtils.getNowDate());
        return parkChargingRulesMapper.insertParkChargingRules(parkChargingRules);
    }

    /**
     * 修改停车场计费规则
     * 
     * @param parkChargingRules 停车场计费规则
     * @return 结果
     */
    @Override
    public int updateParkChargingRules(ParkChargingRules parkChargingRules)
    {
        parkChargingRules.setUpdateTime(DateUtils.getNowDate());
        return parkChargingRulesMapper.updateParkChargingRules(parkChargingRules);
    }

    /**
     * 批量删除停车场计费规则
     * 
     * @param ids 需要删除的停车场计费规则主键
     * @return 结果
     */
    @Override
    public int deleteParkChargingRulesByIds(String[] ids)
    {
        return parkChargingRulesMapper.deleteParkChargingRulesByIds(ids);
    }

    /**
     * 删除停车场计费规则信息
     * 
     * @param id 停车场计费规则主键
     * @return 结果
     */
    @Override
    public int deleteParkChargingRulesById(String id)
    {
        return parkChargingRulesMapper.deleteParkChargingRulesById(id);
    }
}
