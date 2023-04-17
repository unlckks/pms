package com.mingyun.park.service.impl;

import java.util.List;
import com.mingyun.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.mingyun.park.mapper.ParkMapper;
import com.mingyun.park.domain.Park;
import com.mingyun.park.service.IParkService;

import javax.annotation.Resource;

/**
 * 停车场Service业务层处理
 * 
 * @author mingyun
 * @date 2023-03-08
 */
@Service
public class ParkServiceImpl implements IParkService 
{
    @Resource
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

    /**
     * 查询所有停车场不分页
     *
     * @return
     */
    @Override
    public List<Park> getAllPark() {
        return  this.selectParkList(new Park());
    }
}
