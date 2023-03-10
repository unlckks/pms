package com.powernode.park.service;

import java.util.List;
import com.powernode.park.domain.ParkChargingRules;

/**
 * 停车场计费规则Service接口
 * 
 * @author mingyun
 * @date 2023-03-08
 */
public interface IParkChargingRulesService 
{
    /**
     * 查询停车场计费规则
     * 
     * @param id 停车场计费规则主键
     * @return 停车场计费规则
     */
    public ParkChargingRules selectParkChargingRulesById(String id);

    /**
     * 查询停车场计费规则列表
     * 
     * @param parkChargingRules 停车场计费规则
     * @return 停车场计费规则集合
     */
    public List<ParkChargingRules> selectParkChargingRulesList(ParkChargingRules parkChargingRules);

    /**
     * 新增停车场计费规则
     * 
     * @param parkChargingRules 停车场计费规则
     * @return 结果
     */
    public int insertParkChargingRules(ParkChargingRules parkChargingRules);

    /**
     * 修改停车场计费规则
     * 
     * @param parkChargingRules 停车场计费规则
     * @return 结果
     */
    public int updateParkChargingRules(ParkChargingRules parkChargingRules);

    /**
     * 批量删除停车场计费规则
     * 
     * @param ids 需要删除的停车场计费规则主键集合
     * @return 结果
     */
    public int deleteParkChargingRulesByIds(String[] ids);

    /**
     * 删除停车场计费规则信息
     * 
     * @param id 停车场计费规则主键
     * @return 结果
     */
    public int deleteParkChargingRulesById(String id);
}
