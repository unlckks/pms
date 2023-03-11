package com.powernode.park.controller;

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
import com.powernode.common.annotation.Log;
import com.powernode.common.core.controller.BaseController;
import com.powernode.common.core.domain.AjaxResult;
import com.powernode.common.enums.BusinessType;
import com.powernode.park.domain.ParkCheckpoint;
import com.powernode.park.service.IParkCheckpointService;
import com.powernode.common.utils.poi.ExcelUtil;
import com.powernode.common.core.page.TableDataInfo;

/**
 * 道闸管理Controller
 *
 * @author mingyun
 * @date 2023-03-11
 */
@RestController
@RequestMapping("/park/checkpoint")
public class ParkCheckpointController extends BaseController
{
    @Autowired
    private IParkCheckpointService parkCheckpointService;

    /**
     * 查询道闸管理列表
     */
    @PreAuthorize("@ss.hasPermi('park:checkpoint:list')")
    @GetMapping("/list")
    public TableDataInfo list(ParkCheckpoint parkCheckpoint)
    {
        startPage();
        List<ParkCheckpoint> list = parkCheckpointService.selectParkCheckpointList(parkCheckpoint);
        return getDataTable(list);
    }

    /**
     * 导出道闸管理列表
     */
    @PreAuthorize("@ss.hasPermi('park:checkpoint:export')")
    @Log(title = "道闸管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ParkCheckpoint parkCheckpoint)
    {
        List<ParkCheckpoint> list = parkCheckpointService.selectParkCheckpointList(parkCheckpoint);
        ExcelUtil<ParkCheckpoint> util = new ExcelUtil<ParkCheckpoint>(ParkCheckpoint.class);
        util.exportExcel(response, list, "道闸管理数据");
    }

    /**
     * 获取道闸管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('park:checkpoint:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(parkCheckpointService.selectParkCheckpointById(id));
    }

    /**
     * 新增道闸管理
     */
    @PreAuthorize("@ss.hasPermi('park:checkpoint:add')")
    @Log(title = "道闸管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ParkCheckpoint parkCheckpoint)
    {
        return toAjax(parkCheckpointService.insertParkCheckpoint(parkCheckpoint));
    }

    /**
     * 修改道闸管理
     */
    @PreAuthorize("@ss.hasPermi('park:checkpoint:edit')")
    @Log(title = "道闸管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ParkCheckpoint parkCheckpoint)
    {
        return toAjax(parkCheckpointService.updateParkCheckpoint(parkCheckpoint));
    }

    /**
     * 删除道闸管理
     */
    @PreAuthorize("@ss.hasPermi('park:checkpoint:remove')")
    @Log(title = "道闸管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(parkCheckpointService.deleteParkCheckpointByIds(ids));
    }



}
