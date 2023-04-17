package com.mingyun.asset.controller;

import java.util.List;
import java.util.Map;
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
import com.mingyun.asset.domain.AssetHouse;
import com.mingyun.asset.service.IAssetHouseService;
import com.mingyun.common.utils.poi.ExcelUtil;
import com.mingyun.common.core.page.TableDataInfo;

/**
 * 商铺管理Controller
 * 
 * @author mingyun
 * @date 2023-03-04
 */
@RestController
@RequestMapping("/asset/house")
public class AssetHouseController extends BaseController
{
    @Autowired
    private IAssetHouseService assetHouseService;

    /**
     * 查询商铺管理列表
     */
    @PreAuthorize("@ss.hasPermi('asset:house:list')")
    @GetMapping("/list")
    public TableDataInfo list(AssetHouse assetHouse)
    {
        startPage();
        List<AssetHouse> list = assetHouseService.selectAssetHouseList(assetHouse);
        return getDataTable(list);
    }

    /**
     * 导出商铺管理列表
     */
    @PreAuthorize("@ss.hasPermi('asset:house:export')")
    @Log(title = "商铺管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AssetHouse assetHouse)
    {
        List<AssetHouse> list = assetHouseService.selectAssetHouseList(assetHouse);
        ExcelUtil<AssetHouse> util = new ExcelUtil<AssetHouse>(AssetHouse.class);
        util.exportExcel(response, list, "商铺管理数据");
    }

    /**
     * 获取商铺管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('asset:house:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(assetHouseService.selectAssetHouseById(id));
    }

    /**
     * 新增商铺管理
     */
    @PreAuthorize("@ss.hasPermi('asset:house:add')")
    @Log(title = "商铺管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssetHouse assetHouse)
    {
        //设置name
        assetHouse.setName(assetHouse.getBlockId()+"-"+assetHouse.getBuildingId()+"-"+assetHouse.getFloor()+"-"+assetHouse.getCode());
        assetHouse.setCreateBy(getUsername());
        return toAjax(assetHouseService.insertAssetHouse(assetHouse));
    }

    /**
     * 修改商铺管理
     */
    @PreAuthorize("@ss.hasPermi('asset:house:edit')")
    @Log(title = "商铺管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssetHouse assetHouse)
    {
        //设置name
        assetHouse.setName(assetHouse.getBlockId()+"-"+assetHouse.getBuildingId()+"-"+assetHouse.getFloor()+"-"+assetHouse.getCode());
        assetHouse.setUpdateBy(getUsername());
        return toAjax(assetHouseService.updateAssetHouse(assetHouse));
    }

    /**
     * 删除商铺管理
     */
    @PreAuthorize("@ss.hasPermi('asset:house:remove')")
    @Log(title = "商铺管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(assetHouseService.deleteAssetHouseByIds(ids));
    }


    /**
     * 查询所有资源不分页
     */
    @GetMapping("getAllHouses")
    public AjaxResult getAllHouses(){
        List<AssetHouse> assetHouses=this.assetHouseService.queryAllHouses();
        return  AjaxResult.success(assetHouses);
    }

    /**
     *根据houseID名查询资源和所有的信息
     */
    @GetMapping("getAllResourceByHouseId/{houseId}")
    public  AjaxResult getAllResourceByHouseId(@PathVariable Long houseId){
        if (houseId == null ){
            return  AjaxResult.error("商铺ID不能为空!");
        }
        Map<String ,Object> data =this.assetHouseService.queryAllResourceByHouseId(houseId);
        return  AjaxResult.success(data);
    }
}
