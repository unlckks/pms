package com.powernode.asset.service.impl;

import java.util.List;
import com.powernode.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.powernode.asset.mapper.AssetBuildingMapper;
import com.powernode.asset.domain.AssetBuilding;
import com.powernode.asset.service.IAssetBuildingService;

import javax.annotation.Resource;

/**
 * 楼栋管理Service业务层处理
 * 
 * @author powernode
 * @date 2023-03-04
 */
@Service
public class AssetBuildingServiceImpl implements IAssetBuildingService 
{
    @Resource
    private AssetBuildingMapper assetBuildingMapper;

    /**
     * 查询楼栋管理
     * 
     * @param id 楼栋管理主键
     * @return 楼栋管理
     */
    @Override
    public AssetBuilding selectAssetBuildingById(Long id)
    {
        return assetBuildingMapper.selectAssetBuildingById(id);
    }

    /**
     * 查询楼栋管理列表
     * 
     * @param assetBuilding 楼栋管理
     * @return 楼栋管理
     */
    @Override
    public List<AssetBuilding> selectAssetBuildingList(AssetBuilding assetBuilding)
    {
        return assetBuildingMapper.selectAssetBuildingList(assetBuilding);
    }

    /**
     * 新增楼栋管理
     * 
     * @param assetBuilding 楼栋管理
     * @return 结果
     */
    @Override
    public int insertAssetBuilding(AssetBuilding assetBuilding)
    {
        assetBuilding.setCreateTime(DateUtils.getNowDate());
        return assetBuildingMapper.insertAssetBuilding(assetBuilding);
    }

    /**
     * 修改楼栋管理
     * 
     * @param assetBuilding 楼栋管理
     * @return 结果
     */
    @Override
    public int updateAssetBuilding(AssetBuilding assetBuilding)
    {
        assetBuilding.setUpdateTime(DateUtils.getNowDate());
        return assetBuildingMapper.updateAssetBuilding(assetBuilding);
    }

    /**
     * 批量删除楼栋管理
     * 
     * @param ids 需要删除的楼栋管理主键
     * @return 结果
     */
    @Override
    public int deleteAssetBuildingByIds(Long[] ids)
    {
        return assetBuildingMapper.deleteAssetBuildingByIds(ids);
    }

    /**
     * 删除楼栋管理信息
     * 
     * @param id 楼栋管理主键
     * @return 结果
     */
    @Override
    public int deleteAssetBuildingById(Long id)
    {
        return assetBuildingMapper.deleteAssetBuildingById(id);
    }

    /**
     * 查询不分页
     * @return
     */
    @Override
    public List<AssetBuilding> getAllBuilding() {
        return   this.assetBuildingMapper.selectAssetBuildingList(new AssetBuilding());
    }
}
