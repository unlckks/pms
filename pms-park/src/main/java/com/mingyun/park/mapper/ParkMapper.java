package com.mingyun.park.mapper;

import java.util.List;
import com.mingyun.park.domain.Park;

/**
 * 停车场Mapper接口
 * 
 * @author mingyun
 * @date 2023-03-08
 */
public interface ParkMapper 
{
    /**
     * 查询停车场
     * 
     * @param id 停车场主键
     * @return 停车场
     */
    public Park selectParkById(Long id);

    /**
     * 查询停车场列表
     * 
     * @param park 停车场
     * @return 停车场集合
     */
    public List<Park> selectParkList(Park park);

    /**
     * 新增停车场
     * 
     * @param park 停车场
     * @return 结果
     */
    public int insertPark(Park park);

    /**
     * 修改停车场
     * 
     * @param park 停车场
     * @return 结果
     */
    public int updatePark(Park park);

    /**
     * 删除停车场
     * 
     * @param id 停车场主键
     * @return 结果
     */
    public int deleteParkById(Long id);

    /**
     * 批量删除停车场
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteParkByIds(Long[] ids);


}
