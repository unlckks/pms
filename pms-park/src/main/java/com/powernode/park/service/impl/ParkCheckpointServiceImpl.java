package com.powernode.park.service.impl;

import java.util.List;
import com.powernode.common.utils.DateUtils;
import com.powernode.common.utils.uuid.IdUtils;
import com.powernode.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.powernode.park.mapper.ParkCheckpointMapper;
import com.powernode.park.domain.ParkCheckpoint;
import com.powernode.park.service.IParkCheckpointService;

import javax.annotation.Resource;

/**
 * 道闸管理Service业务层处理
 *
 * @author mingyun
 * @date 2023-03-10
 */
@Service
public class ParkCheckpointServiceImpl implements IParkCheckpointService
{
    @Resource
    private ParkCheckpointMapper parkCheckpointMapper;



    /**
     * 查询道闸管理
     *
     * @param id 道闸管理主键
     * @return 道闸管理
     */
    @Override
    public ParkCheckpoint selectParkCheckpointById(Long id)
    {
        return parkCheckpointMapper.selectParkCheckpointById(id);
    }

    /**
     * 查询道闸管理列表
     *
     * @param parkCheckpoint 道闸管理
     * @return 道闸管理
     */
    @Override
    public List<ParkCheckpoint> selectParkCheckpointList(ParkCheckpoint parkCheckpoint)
    {
        return parkCheckpointMapper.selectParkCheckpointList(parkCheckpoint);
    }

    /**
     * 新增道闸管理
     *
     * @param parkCheckpoint 道闸管理
     * @return 结果
     */
    @Override
    public int insertParkCheckpoint(ParkCheckpoint parkCheckpoint)
    {
        parkCheckpoint.setCreateTime(DateUtils.getNowDate());
        parkCheckpoint.setCode(IdUtils.createNumber());
        parkCheckpoint.setCarParkId(IdUtils.createNumber());
        return parkCheckpointMapper.insertParkCheckpoint(parkCheckpoint);
    }
    /**
     * 修改道闸管理
     *
     * @param parkCheckpoint 道闸管理
     * @return 结果
     */
    @Override
    public int updateParkCheckpoint(ParkCheckpoint parkCheckpoint)
    {
        parkCheckpoint.setUpdateTime(DateUtils.getNowDate());
        return parkCheckpointMapper.updateParkCheckpoint(parkCheckpoint);
    }

    /**
     * 批量删除道闸管理
     *
     * @param ids 需要删除的道闸管理主键
     * @return 结果
     */
    @Override
    public int deleteParkCheckpointByIds(Long[] ids)
    {
        return parkCheckpointMapper.deleteParkCheckpointByIds(ids);
    }

    /**
     * 删除道闸管理信息
     *
     * @param id 道闸管理主键
     * @return 结果
     */
    @Override
    public int deleteParkCheckpointById(Long id)
    {
        return parkCheckpointMapper.deleteParkCheckpointById(id);
    }

}
