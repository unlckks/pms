package com.powernode.owner.controller;

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
import com.powernode.owner.domain.OwnerUser;
import com.powernode.owner.service.IOwnerUserService;
import com.powernode.common.utils.poi.ExcelUtil;
import com.powernode.common.core.page.TableDataInfo;

/**
 * 业主信息Controller
 * 
 * @author mingyun
 * @date 2023-03-06
 */
@RestController
@RequestMapping("/owner/user")
public class OwnerUserController extends BaseController
{
    @Autowired
    private IOwnerUserService ownerUserService;

    /**
     * 查询业主信息列表
     */
    @PreAuthorize("@ss.hasPermi('owner:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(OwnerUser ownerUser)
    {
        startPage();
        List<OwnerUser> list = ownerUserService.selectOwnerUserList(ownerUser);
        return getDataTable(list);
    }

    /**
     * 导出业主信息列表
     */
    @PreAuthorize("@ss.hasPermi('owner:user:export')")
    @Log(title = "业主信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OwnerUser ownerUser)
    {
        List<OwnerUser> list = ownerUserService.selectOwnerUserList(ownerUser);
        ExcelUtil<OwnerUser> util = new ExcelUtil<OwnerUser>(OwnerUser.class);
        util.exportExcel(response, list, "业主信息数据");
    }

    /**
     * 获取业主信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('owner:user:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(ownerUserService.selectOwnerUserById(id));
    }

    /**
     * 新增业主信息
     */
    @PreAuthorize("@ss.hasPermi('owner:user:add')")
    @Log(title = "业主信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OwnerUser ownerUser)
    {
        return toAjax(ownerUserService.insertOwnerUser(ownerUser));
    }

    /**
     * 修改业主信息
     */
    @PreAuthorize("@ss.hasPermi('owner:user:edit')")
    @Log(title = "业主信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OwnerUser ownerUser)
    {
        return toAjax(ownerUserService.updateOwnerUser(ownerUser));
    }

}
