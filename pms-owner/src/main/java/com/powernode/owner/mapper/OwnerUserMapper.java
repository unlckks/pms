package com.powernode.owner.mapper;

import java.util.List;
import com.powernode.owner.domain.OwnerUser;

/**
 * 业主信息Mapper接口
 * 
 * @author mingyun
 * @date 2023-03-06
 */
public interface OwnerUserMapper 
{
    /**
     * 查询业主信息
     * 
     * @param id 业主信息主键
     * @return 业主信息
     */
    public OwnerUser selectOwnerUserById(Long id);

    /**
     * 查询业主信息列表
     * 
     * @param ownerUser 业主信息
     * @return 业主信息集合
     */
    public List<OwnerUser> selectOwnerUserList(OwnerUser ownerUser);

    /**
     * 新增业主信息
     * 
     * @param ownerUser 业主信息
     * @return 结果
     */
    public int insertOwnerUser(OwnerUser ownerUser);

    /**
     * 修改业主信息
     * 
     * @param ownerUser 业主信息
     * @return 结果
     */
    public int updateOwnerUser(OwnerUser ownerUser);

    /**
     * 删除业主信息
     * 
     * @param id 业主信息主键
     * @return 结果
     */
    public int deleteOwnerUserById(Long id);

    /**
     * 批量删除业主信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOwnerUserByIds(Long[] ids);
}
