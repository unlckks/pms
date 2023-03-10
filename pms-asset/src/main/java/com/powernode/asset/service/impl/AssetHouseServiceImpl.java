package com.powernode.asset.service.impl;

import java.util.List;
import java.util.Map;

import com.powernode.asset.domain.AssetBuilding;
import com.powernode.asset.mapper.AssetBuildingMapper;
import com.powernode.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.powernode.asset.mapper.AssetHouseMapper;
import com.powernode.asset.domain.AssetHouse;
import com.powernode.asset.service.IAssetHouseService;

import javax.annotation.Resource;

/**
 * 商铺管理Service业务层处理
 * 
 * @author mingyun
 * @date 2023-03-04
 */
@Service
public class AssetHouseServiceImpl implements IAssetHouseService 
{
    @Resource
    private AssetHouseMapper assetHouseMapper;
    /**
     * 查询商铺管理
     * 
     * @param id 商铺管理主键
     * @return 商铺管理
     */
    @Override
    public AssetHouse selectAssetHouseById(Long id)
    {
        return assetHouseMapper.selectAssetHouseById(id);
    }

    /**
     * 查询商铺管理列表
     * 
     * @param assetHouse 商铺管理
     * @return 商铺管理
     */
    @Override
    public List<AssetHouse> selectAssetHouseList(AssetHouse assetHouse)
    {
        return assetHouseMapper.selectAssetHouseList(assetHouse);
    }

    /**
     * 新增商铺管理
     * 
     * @param assetHouse 商铺管理
     * @return 结果
     */
    @Override
    public int insertAssetHouse(AssetHouse assetHouse)
    {
        assetHouse.setCreateTime(DateUtils.getNowDate());
        return assetHouseMapper.insertAssetHouse(assetHouse);
    }

    /**
     * 修改商铺管理
     * 
     * @param assetHouse 商铺管理
     * @return 结果
     */
    @Override
    public int updateAssetHouse(AssetHouse assetHouse)
    {
        assetHouse.setUpdateTime(DateUtils.getNowDate());
        return assetHouseMapper.updateAssetHouse(assetHouse);
    }

    /**
     * 批量删除商铺管理
     * 
     * @param ids 需要删除的商铺管理主键
     * @return 结果
     */
    @Override
    public int deleteAssetHouseByIds(Long[] ids)
    {
        return assetHouseMapper.deleteAssetHouseByIds(ids);
    }

    /**
     * 删除商铺管理信息
     * 
     * @param id 商铺管理主键
     * @return 结果
     */
    @Override
    public int deleteAssetHouseById(Long id)
    {
        return assetHouseMapper.deleteAssetHouseById(id);
    }

    /**
     * 查询所有资源不分页
     * @return
     */
    @Override
    public List<AssetHouse> queryAllHouses() {
        AssetHouse assetHouse = new AssetHouse();
        return this.assetHouseMapper.selectAssetHouseList(assetHouse);
    }

    /**
     * 根据houseID名查询资源和所有的信息
     * @param houseId
     * @return
     */
    @Override
    public Map<String, Object> queryAllResourceByHouseId(Long houseId) {
        return this.assetHouseMapper.queryAllResourceByHouseId(houseId);
    }


}
