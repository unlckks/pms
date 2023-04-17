package com.mingyun.asset.mapper;

import java.util.List;
import com.mingyun.asset.domain.AssetHouseBlock;

/**
 * 商圈管理Mapper接口
 * 
 * @author mingyun
 * @date 2023-03-04
 */
public interface AssetHouseBlockMapper 
{
    /**
     * 查询商圈管理
     * 
     * @param id 商圈管理主键
     * @return 商圈管理
     */
    public AssetHouseBlock selectAssetHouseBlockById(Long id);

    /**
     * 查询商圈管理列表
     * 
     * @param assetHouseBlock 商圈管理
     * @return 商圈管理集合
     */
    public List<AssetHouseBlock> selectAssetHouseBlockList(AssetHouseBlock assetHouseBlock);

    /**
     * 新增商圈管理
     * 
     * @param assetHouseBlock 商圈管理
     * @return 结果
     */
    public int insertAssetHouseBlock(AssetHouseBlock assetHouseBlock);

    /**
     * 修改商圈管理
     * 
     * @param assetHouseBlock 商圈管理
     * @return 结果
     */
    public int updateAssetHouseBlock(AssetHouseBlock assetHouseBlock);

    /**
     * 删除商圈管理
     * 
     * @param id 商圈管理主键
     * @return 结果
     */
    public int deleteAssetHouseBlockById(Long id);

    /**
     * 批量删除商圈管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAssetHouseBlockByIds(Long[] ids);
}
