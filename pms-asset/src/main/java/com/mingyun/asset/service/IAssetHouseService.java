package com.mingyun.asset.service;

import java.util.List;
import java.util.Map;

import com.mingyun.asset.domain.AssetHouse;

/**
 * 商铺管理Service接口
 * 
 * @author mingyun
 * @date 2023-03-04
 */
public interface IAssetHouseService 
{
    /**
     * 查询商铺管理
     * 
     * @param id 商铺管理主键
     * @return 商铺管理
     */
    public AssetHouse selectAssetHouseById(Long id);

    /**
     * 查询商铺管理列表
     * 
     * @param assetHouse 商铺管理
     * @return 商铺管理集合
     */
    public List<AssetHouse> selectAssetHouseList(AssetHouse assetHouse);

    /**
     * 新增商铺管理
     * 
     * @param assetHouse 商铺管理
     * @return 结果
     */
    public int insertAssetHouse(AssetHouse assetHouse);

    /**
     * 修改商铺管理
     * 
     * @param assetHouse 商铺管理
     * @return 结果
     */
    public int updateAssetHouse(AssetHouse assetHouse);

    /**
     * 批量删除商铺管理
     * 
     * @param ids 需要删除的商铺管理主键集合
     * @return 结果
     */
    public int deleteAssetHouseByIds(Long[] ids);

    /**
     * 删除商铺管理信息
     * 
     * @param id 商铺管理主键
     * @return 结果
     */
    public int deleteAssetHouseById(Long id);

    /**
     * 查询所有资源不分页
     * @return
     */
    List<AssetHouse> queryAllHouses();

    /**
     * 根据houseID名查询资源和所有的信息
     * @param houseId
     * @return
     */
    Map<String, Object> queryAllResourceByHouseId(Long houseId);
}
