package com.powernode.asset.service.impl;

import java.util.List;
import com.powernode.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.powernode.asset.mapper.AssetHouseBlockMapper;
import com.powernode.asset.domain.AssetHouseBlock;
import com.powernode.asset.service.IAssetHouseBlockService;

import javax.annotation.Resource;

/**
 * 商圈管理Service业务层处理
 * 
 * @author powernode
 * @date 2023-03-04
 */
@Service
public class AssetHouseBlockServiceImpl implements IAssetHouseBlockService 
{
    @Resource
    private AssetHouseBlockMapper assetHouseBlockMapper;

    /**
     * 查询商圈管理
     * 
     * @param id 商圈管理主键
     * @return 商圈管理
     */
    @Override
    public AssetHouseBlock selectAssetHouseBlockById(Long id)
    {
        return assetHouseBlockMapper.selectAssetHouseBlockById(id);
    }

    /**
     * 查询商圈管理列表
     * 
     * @param assetHouseBlock 商圈管理
     * @return 商圈管理
     */
    @Override
    public List<AssetHouseBlock> selectAssetHouseBlockList(AssetHouseBlock assetHouseBlock)
    {
        return assetHouseBlockMapper.selectAssetHouseBlockList(assetHouseBlock);
    }

    /**
     * 新增商圈管理
     * 
     * @param assetHouseBlock 商圈管理
     * @return 结果
     */
    @Override
    public int insertAssetHouseBlock(AssetHouseBlock assetHouseBlock)
    {
        assetHouseBlock.setCreateTime(DateUtils.getNowDate());
        return assetHouseBlockMapper.insertAssetHouseBlock(assetHouseBlock);
    }

    /**
     * 修改商圈管理
     * 
     * @param assetHouseBlock 商圈管理
     * @return 结果
     */
    @Override
    public int updateAssetHouseBlock(AssetHouseBlock assetHouseBlock)
    {
        assetHouseBlock.setUpdateTime(DateUtils.getNowDate());
        return assetHouseBlockMapper.updateAssetHouseBlock(assetHouseBlock);
    }

    /**
     * 批量删除商圈管理
     * 
     * @param ids 需要删除的商圈管理主键
     * @return 结果
     */
    @Override
    public int deleteAssetHouseBlockByIds(Long[] ids)
    {
        return assetHouseBlockMapper.deleteAssetHouseBlockByIds(ids);
    }

    /**
     * 删除商圈管理信息
     * 
     * @param id 商圈管理主键
     * @return 结果
     */
    @Override
    public int deleteAssetHouseBlockById(Long id)
    {
        return assetHouseBlockMapper.deleteAssetHouseBlockById(id);
    }

    @Override
    public List<AssetHouseBlock> queryAllBlock() {
        return this.selectAssetHouseBlockList(new AssetHouseBlock());
    }
}
