package com.powernode.asset.service;

import java.util.List;
import com.powernode.asset.domain.AssetBuilding;

/**
 * 楼栋管理Service接口
 * 
 * @author powernode
 * @date 2023-03-04
 */
public interface IAssetBuildingService 
{
    /**
     * 查询楼栋管理
     * 
     * @param id 楼栋管理主键
     * @return 楼栋管理
     */
    public AssetBuilding selectAssetBuildingById(Long id);

    /**
     * 查询楼栋管理列表
     * 
     * @param assetBuilding 楼栋管理
     * @return 楼栋管理集合
     */
    public List<AssetBuilding> selectAssetBuildingList(AssetBuilding assetBuilding);

    /**
     * 新增楼栋管理
     * 
     * @param assetBuilding 楼栋管理
     * @return 结果
     */
    public int insertAssetBuilding(AssetBuilding assetBuilding);

    /**
     * 修改楼栋管理
     * 
     * @param assetBuilding 楼栋管理
     * @return 结果
     */
    public int updateAssetBuilding(AssetBuilding assetBuilding);

    /**
     * 批量删除楼栋管理
     * 
     * @param ids 需要删除的楼栋管理主键集合
     * @return 结果
     */
    public int deleteAssetBuildingByIds(Long[] ids);

    /**
     * 删除楼栋管理信息
     * 
     * @param id 楼栋管理主键
     * @return 结果
     */
    public int deleteAssetBuildingById(Long id);
}
