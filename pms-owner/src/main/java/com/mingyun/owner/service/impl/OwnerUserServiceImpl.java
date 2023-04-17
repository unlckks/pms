package com.mingyun.owner.service.impl;

import java.util.List;
import com.mingyun.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mingyun.owner.mapper.OwnerUserMapper;
import com.mingyun.owner.domain.OwnerUser;
import com.mingyun.owner.service.IOwnerUserService;

/**
 * 业主信息Service业务层处理
 * 
 * @author mingyun
 * @date 2023-03-06
 */
@Service
public class OwnerUserServiceImpl implements IOwnerUserService 
{
    @Autowired
    private OwnerUserMapper ownerUserMapper;

    /**
     * 查询业主信息
     * 
     * @param id 业主信息主键
     * @return 业主信息
     */
    @Override
    public OwnerUser selectOwnerUserById(Long id)
    {
        return ownerUserMapper.selectOwnerUserById(id);
    }

    /**
     * 查询业主信息列表
     * 
     * @param ownerUser 业主信息
     * @return 业主信息
     */
    @Override
    public List<OwnerUser> selectOwnerUserList(OwnerUser ownerUser)
    {
        return ownerUserMapper.selectOwnerUserList(ownerUser);
    }

    /**
     * 新增业主信息
     * 
     * @param ownerUser 业主信息
     * @return 结果
     */
    @Override
    public int insertOwnerUser(OwnerUser ownerUser)
    {
        ownerUser.setCreateTime(DateUtils.getNowDate());
        return ownerUserMapper.insertOwnerUser(ownerUser);
    }

    /**
     * 修改业主信息
     * 
     * @param ownerUser 业主信息
     * @return 结果
     */
    @Override
    public int updateOwnerUser(OwnerUser ownerUser)
    {
        ownerUser.setUpdateTime(DateUtils.getNowDate());
        return ownerUserMapper.updateOwnerUser(ownerUser);
    }

    /**
     * 批量删除业主信息
     * 
     * @param ids 需要删除的业主信息主键
     * @return 结果
     */
    @Override
    public int deleteOwnerUserByIds(Long[] ids)
    {
        return ownerUserMapper.deleteOwnerUserByIds(ids);
    }

    /**
     * 删除业主信息信息
     * 
     * @param id 业主信息主键
     * @return 结果
     */
    @Override
    public int deleteOwnerUserById(Long id)
    {
        return ownerUserMapper.deleteOwnerUserById(id);
    }
}
