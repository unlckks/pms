package com.mingyun.pay.mapper;

import java.util.List;
import com.mingyun.pay.domain.PayOrder;
import org.apache.ibatis.annotations.Param;

/**
 * 订单Mapper接口
 * 
 * @author mingyun
 * @date 2023-03-11
 */
public interface PayOrderMapper 
{
    /**
     * 查询订单
     * 
     * @param id 订单主键
     * @return 订单
     */
    public PayOrder selectPayOrderById(Long id);

    /**
     * 查询订单列表
     * 
     * @param payOrder 订单
     * @return 订单集合
     */
    public List<PayOrder> selectPayOrderList(PayOrder payOrder);

    /**
     * 新增订单
     * 
     * @param payOrder 订单
     * @return 结果
     */
    public int insertPayOrder(PayOrder payOrder);

    /**
     * 修改订单
     * 
     * @param payOrder 订单
     * @return 结果
     */
    public int updatePayOrder(PayOrder payOrder);

    /**
     * 删除订单
     * 
     * @param id 订单主键
     * @return 结果
     */
    public int deletePayOrderById(Long id);

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePayOrderByIds(Long[] ids);

    /**
     * 修改pay_order状态
     *
     * @param orderIds
     * @param payed
     */
    void updatePayOrderByOrderIds(@Param("orderIds") List<String> orderIds, @Param("payed") String payed);
}
