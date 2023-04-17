package com.mingyun.park.controller;

import java.util.List;
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
import com.mingyun.common.annotation.Log;
import com.mingyun.common.core.controller.BaseController;
import com.mingyun.common.core.domain.AjaxResult;
import com.mingyun.common.enums.BusinessType;
import com.mingyun.park.domain.Park;
import com.mingyun.park.service.IParkService;
import com.mingyun.common.utils.poi.ExcelUtil;
import com.mingyun.common.core.page.TableDataInfo;

/**
 * 停车场Controller
 * 
 * @author mingyun
 * @date 2023-03-08
 */
@RestController
@RequestMapping("/park/park")
public class ParkController extends BaseController
{
    @Autowired
    private IParkService parkService;

    /**
     * 查询停车场列表
     */
    @PreAuthorize("@ss.hasPermi('park:park:list')")
    @GetMapping("/list")
    public TableDataInfo list(Park park)
    {
        startPage();
        List<Park> list = parkService.selectParkList(park);
        return getDataTable(list);
    }

    /**
     * 导出停车场列表
     */
    @PreAuthorize("@ss.hasPermi('park:park:export')")
    @Log(title = "停车场", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Park park)
    {
        List<Park> list = parkService.selectParkList(park);
        ExcelUtil<Park> util = new ExcelUtil<Park>(Park.class);
        util.exportExcel(response, list, "停车场数据");
    }

    /**
     * 获取停车场详细信息
     */
    @PreAuthorize("@ss.hasPermi('park:park:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(parkService.selectParkById(id));
    }

    /**
     * 新增停车场
     */
    @PreAuthorize("@ss.hasPermi('park:park:add')")
    @Log(title = "停车场", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Park park)
    {
        return toAjax(parkService.insertPark(park));
    }

    /**
     * 修改停车场
     */
    @PreAuthorize("@ss.hasPermi('park:park:edit')")
    @Log(title = "停车场", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Park park)
    {
        return toAjax(parkService.updatePark(park));
    }

    /**
     * 删除停车场
     */
    @PreAuthorize("@ss.hasPermi('park:park:remove')")
    @Log(title = "停车场", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(parkService.deleteParkByIds(ids));
    }

    /**
     * 查询所有停车场不分页
     */
    @GetMapping("getAllPark")
    public AjaxResult getAllPark(){
        List<Park>   park = this.parkService.getAllPark();
        return AjaxResult.success(park);
    }
}
