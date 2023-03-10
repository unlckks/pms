package com.powernode.park.service.impl;

import java.util.List;
import com.powernode.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.powernode.park.mapper.ParkMapper;
import com.powernode.park.domain.Park;
import com.powernode.park.service.IParkService;

/**
 * 停车场Service业务层处理
 * 
 * @author powernode
 * @date 2023-03-08
 */
@Service
public class ParkServiceImpl implements IParkService 
{
    @Autowired
    private ParkMapper parkMapper;

    /**
     * 查询停车场
     * 
     * @param id 停车场主键
     * @return 停车场
     */
    @Override
    public Park selectParkById(Long id)
    {
        return parkMapper.selectParkById(id);
    }

    /**
     * 查询停车场列表
     * 
     * @param park 停车场
     * @return 停车场
     */
    @Override
    public List<Park> selectParkList(Park park)
    {
        return parkMapper.selectParkList(park);
    }

    /**
     * 新增停车场
     * 
     * @param park 停车场
     * @return 结果
     */
    @Override
    public int insertPark(Park park)
    {
        park.setCreateTime(DateUtils.getNowDate());
        return parkMapper.insertPark(park);
    }

    /**
     * 修改停车场
     * 
     * @param park 停车场
     * @return 结果
     */
    @Override
    public int updatePark(Park park)
    {
        park.setUpdateTime(DateUtils.getNowDate());
        return parkMapper.updatePark(park);
    }

    /**
     * 批量删除停车场
     * 
     * @param ids 需要删除的停车场主键
     * @return 结果
     */
    @Override
    public int deleteParkByIds(Long[] ids)
    {
        return parkMapper.deleteParkByIds(ids);
    }

    /**
     * 删除停车场信息
     * 
     * @param id 停车场主键
     * @return 结果
     */
    @Override
    public int deleteParkById(Long id)
    {
        return parkMapper.deleteParkById(id);
    }
}
