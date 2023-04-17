package com.mingyun.pay.service.impl;

import java.util.List;
import com.mingyun.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.mingyun.pay.mapper.PayTempMapper;
import com.mingyun.pay.domain.PayTemp;
import com.mingyun.pay.service.IPayTempService;

import javax.annotation.Resource;

/**
 * 临时收费Service业务层处理
 *
 * @author mingyun
 * @date 2023-03-07
 */
@Service
public class PayTempServiceImpl implements IPayTempService 
{
    @Resource
    private PayTempMapper payTempMapper;

    /**
     * 查询临时收费
     * 
     * @param id 临时收费主键
     * @return 临时收费
     */
    @Override
    public PayTemp selectPayTempById(Long id)
    {
        return payTempMapper.selectPayTempById(id);
    }

    /**
     * 查询临时收费列表
     * 
     * @param payTemp 临时收费
     * @return 临时收费
     */
    @Override
    public List<PayTemp> selectPayTempList(PayTemp payTemp)
    {
        return payTempMapper.selectPayTempList(payTemp);
    }

    /**
     * 新增临时收费
     * 
     * @param payTemp 临时收费
     * @return 结果
     */
    @Override
    public int insertPayTemp(PayTemp payTemp)
    {
        payTemp.setCreateTime(DateUtils.getNowDate());
        return payTempMapper.insertPayTemp(payTemp);
    }

    /**
     * 修改临时收费
     * 
     * @param payTemp 临时收费
     * @return 结果
     */
    @Override
    public int updatePayTemp(PayTemp payTemp)
    {
        payTemp.setUpdateTime(DateUtils.getNowDate());
        return payTempMapper.updatePayTemp(payTemp);
    }

    /**
     * 批量删除临时收费
     * 
     * @param ids 需要删除的临时收费主键
     * @return 结果
     */
    @Override
    public int deletePayTempByIds(Long[] ids)
    {
        return payTempMapper.deletePayTempByIds(ids);
    }

    /**
     * 删除临时收费信息
     * 
     * @param id 临时收费主键
     * @return 结果
     */
    @Override
    public int deletePayTempById(Long id)
    {
        return payTempMapper.deletePayTempById(id);
    }
}
