package com.powernode.park.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.powernode.common.annotation.Log;
import com.powernode.common.core.controller.BaseController;
import com.powernode.common.core.domain.AjaxResult;
import com.powernode.common.enums.BusinessType;
import com.powernode.park.domain.ParkChargingRules;
import com.powernode.park.service.IParkChargingRulesService;
import com.powernode.common.utils.poi.ExcelUtil;
import com.powernode.common.core.page.TableDataInfo;

/**
 * 停车场计费规则Controller
 *
 * @author mingyun
 * @date 2023-03-08
 */
@RestController
@RequestMapping("/park/rules")
public class ParkChargingRulesController extends BaseController
{
    @Resource
    private IParkChargingRulesService parkChargingRulesService;

    /**
     * 查询停车场计费规则列表
     */
    @PreAuthorize("@ss.hasPermi('park:rules:list')")
    @GetMapping("/list")
    public TableDataInfo list(ParkChargingRules parkChargingRules)
    {
        startPage();
        List<ParkChargingRules> list = parkChargingRulesService.selectParkChargingRulesList(parkChargingRules);
        return getDataTable(list);
    }

    /**
     * 导出停车场计费规则列表
     */
    @PreAuthorize("@ss.hasPermi('park:rules:export')")
    @Log(title = "停车场计费规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ParkChargingRules parkChargingRules)
    {
        List<ParkChargingRules> list = parkChargingRulesService.selectParkChargingRulesList(parkChargingRules);
        ExcelUtil<ParkChargingRules> util = new ExcelUtil<ParkChargingRules>(ParkChargingRules.class);
        util.exportExcel(response, list, "停车场计费规则数据");
    }

    /**
     * 获取停车场计费规则详细信息
     */
    @PreAuthorize("@ss.hasPermi('park:rules:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(parkChargingRulesService.selectParkChargingRulesById(id));
    }

    /**
     * 新增停车场计费规则
     */
    @PreAuthorize("@ss.hasPermi('park:rules:add')")
    @Log(title = "停车场计费规则", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ParkChargingRules parkChargingRules)
    {
        return toAjax(parkChargingRulesService.insertParkChargingRules(parkChargingRules));
    }

    /**
     * 修改停车场计费规则
     */
    @PreAuthorize("@ss.hasPermi('park:rules:edit')")
    @Log(title = "停车场计费规则", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ParkChargingRules parkChargingRules)
    {
        return toAjax(parkChargingRulesService.updateParkChargingRules(parkChargingRules));
    }

    /**
     * 删除停车场计费规则
     */
    @PreAuthorize("@ss.hasPermi('park:rules:remove')")
    @Log(title = "停车场计费规则", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(parkChargingRulesService.deleteParkChargingRulesByIds(ids));
    }
}
