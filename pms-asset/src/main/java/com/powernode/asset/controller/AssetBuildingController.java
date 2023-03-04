package com.powernode.asset.controller;

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
import com.powernode.asset.domain.AssetBuilding;
import com.powernode.asset.service.IAssetBuildingService;
import com.powernode.common.utils.poi.ExcelUtil;
import com.powernode.common.core.page.TableDataInfo;

/**
 * 楼栋管理Controller
 * 
 * @author powernode
 * @date 2023-03-04
 */
@RestController
@RequestMapping("/asset/building")
public class AssetBuildingController extends BaseController
{
    @Autowired
    private IAssetBuildingService assetBuildingService;

    /**
     * 查询楼栋管理列表
     */
    @PreAuthorize("@ss.hasPermi('asset:building:list')")
    @GetMapping("/list")
    public TableDataInfo list(AssetBuilding assetBuilding)
    {
        startPage();
        List<AssetBuilding> list = assetBuildingService.selectAssetBuildingList(assetBuilding);
        return getDataTable(list);
    }

    /**
     * 导出楼栋管理列表
     */
    @PreAuthorize("@ss.hasPermi('asset:building:export')")
    @Log(title = "楼栋管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AssetBuilding assetBuilding)
    {
        List<AssetBuilding> list = assetBuildingService.selectAssetBuildingList(assetBuilding);
        ExcelUtil<AssetBuilding> util = new ExcelUtil<AssetBuilding>(AssetBuilding.class);
        util.exportExcel(response, list, "楼栋管理数据");
    }

    /**
     * 获取楼栋管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('asset:building:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(assetBuildingService.selectAssetBuildingById(id));
    }

    /**
     * 新增楼栋管理
     */
    @PreAuthorize("@ss.hasPermi('asset:building:add')")
    @Log(title = "楼栋管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssetBuilding assetBuilding)
    {
        assetBuilding.setCreateBy(getUsername());
        return toAjax(assetBuildingService.insertAssetBuilding(assetBuilding));
    }

    /**
     * 修改楼栋管理
     */
    @PreAuthorize("@ss.hasPermi('asset:building:edit')")
    @Log(title = "楼栋管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssetBuilding assetBuilding)
    {
        assetBuilding.setUpdateBy(getUsername());
        return toAjax(assetBuildingService.updateAssetBuilding(assetBuilding));
    }

    /**
     * 删除楼栋管理
     */
    @PreAuthorize("@ss.hasPermi('asset:building:remove')")
    @Log(title = "楼栋管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(assetBuildingService.deleteAssetBuildingByIds(ids));
    }
}
