package com.powernode.park.mapper;

import java.util.List;
import com.powernode.park.domain.ParkCheckpoint;

/**
 * 道闸管理Mapper接口
 *
 * @author mingyun
 * @date 2023-03-11
 */
public interface ParkCheckpointMapper
{
    /**
     * 查询道闸管理
     *
     * @param id 道闸管理主键
     * @return 道闸管理
     */
    public ParkCheckpoint selectParkCheckpointById(Long id);

    /**
     * 查询道闸管理列表
     *
     * @param parkCheckpoint 道闸管理
     * @return 道闸管理集合
     */
    public List<ParkCheckpoint> selectParkCheckpointList(ParkCheckpoint parkCheckpoint);

    /**
     * 新增道闸管理
     *
     * @param parkCheckpoint 道闸管理
     * @return 结果
     */
    public int insertParkCheckpoint(ParkCheckpoint parkCheckpoint);

    /**
     * 修改道闸管理
     *
     * @param parkCheckpoint 道闸管理
     * @return 结果
     */
    public int updateParkCheckpoint(ParkCheckpoint parkCheckpoint);

    /**
     * 删除道闸管理
     *
     * @param id 道闸管理主键
     * @return 结果
     */
    public int deleteParkCheckpointById(Long id);

    /**
     * 批量删除道闸管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteParkCheckpointByIds(Long[] ids);
}
