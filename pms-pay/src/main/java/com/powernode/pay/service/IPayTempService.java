package com.powernode.pay.service;

import java.util.List;
import com.powernode.pay.domain.PayTemp;

/**
 * 临时收费Service接口
 * 
 * @author mingyun
 * @date 2023-03-07
 */
public interface IPayTempService 
{
    /**
     * 查询临时收费
     * 
     * @param id 临时收费主键
     * @return 临时收费
     */
    public PayTemp selectPayTempById(Long id);

    /**
     * 查询临时收费列表
     * 
     * @param payTemp 临时收费
     * @return 临时收费集合
     */
    public List<PayTemp> selectPayTempList(PayTemp payTemp);

    /**
     * 新增临时收费
     * 
     * @param payTemp 临时收费
     * @return 结果
     */
    public int insertPayTemp(PayTemp payTemp);

    /**
     * 修改临时收费
     * 
     * @param payTemp 临时收费
     * @return 结果
     */
    public int updatePayTemp(PayTemp payTemp);

    /**
     * 批量删除临时收费
     * 
     * @param ids 需要删除的临时收费主键集合
     * @return 结果
     */
    public int deletePayTempByIds(Long[] ids);

    /**
     * 删除临时收费信息
     * 
     * @param id 临时收费主键
     * @return 结果
     */
    public int deletePayTempById(Long id);
}
