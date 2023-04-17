package com.mingyun.asset.controller;

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
import com.mingyun.asset.domain.AssetHouseBlock;
import com.mingyun.asset.service.IAssetHouseBlockService;
import com.mingyun.common.utils.poi.ExcelUtil;
import com.mingyun.common.core.page.TableDataInfo;

/**
 * 商圈管理Controller
 * 
 * @author mingyun
 * @date 2023-03-04
 */
@RestController
@RequestMapping("/asset/block")
public class AssetHouseBlockController extends BaseController
{
    @Autowired
    private IAssetHouseBlockService assetHouseBlockService;

    /**
     * 查询商圈管理列表
     */
    @PreAuthorize("@ss.hasPermi('asset:block:list')")
    @GetMapping("/list")
    public TableDataInfo list(AssetHouseBlock assetHouseBlock)
    {
        startPage();
        List<AssetHouseBlock> list = assetHouseBlockService.selectAssetHouseBlockList(assetHouseBlock);
        return getDataTable(list);
    }

    /**
     * 导出商圈管理列表
     */
    @PreAuthorize("@ss.hasPermi('asset:block:export')")
    @Log(title = "商圈管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AssetHouseBlock assetHouseBlock)
    {
        List<AssetHouseBlock> list = assetHouseBlockService.selectAssetHouseBlockList(assetHouseBlock);
        ExcelUtil<AssetHouseBlock> util = new ExcelUtil<AssetHouseBlock>(AssetHouseBlock.class);
        util.exportExcel(response, list, "商圈管理数据");
    }

    /**
     * 获取商圈管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('asset:block:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(assetHouseBlockService.selectAssetHouseBlockById(id));
    }

    /**
     * 新增商圈管理
     */
    @PreAuthorize("@ss.hasPermi('asset:block:add')")
    @Log(title = "商圈管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssetHouseBlock assetHouseBlock)
    {
        //添加创建者
        assetHouseBlock.setCreateBy(getUsername());
        return toAjax(assetHouseBlockService.insertAssetHouseBlock(assetHouseBlock));
    }

    /**
     * 修改商圈管理
     */
    @PreAuthorize("@ss.hasPermi('asset:block:edit')")
    @Log(title = "商圈管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssetHouseBlock assetHouseBlock)
    {
        //添加修改者
        assetHouseBlock.setUpdateBy(getUsername());
        return toAjax(assetHouseBlockService.updateAssetHouseBlock(assetHouseBlock));
    }

    /**
     * 删除商圈管理
     */
    @PreAuthorize("@ss.hasPermi('asset:block:remove')")
    @Log(title = "商圈管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(assetHouseBlockService.deleteAssetHouseBlockByIds(ids));
    }

    /**
     * 查询所有商圈不分页
     */
    @GetMapping("getAllBlock")
    public AjaxResult getAllBlock(){
        List<AssetHouseBlock> houseBlocks=this.assetHouseBlockService.queryAllBlock();
        return AjaxResult.success(houseBlocks);
    }
}
