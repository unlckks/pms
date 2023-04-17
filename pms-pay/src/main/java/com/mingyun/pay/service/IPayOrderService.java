package com.mingyun.pay.service;

import java.util.List;
import com.mingyun.pay.domain.PayOrder;

/**
 * 订单Service接口
 * 
 * @author mingyun
 * @date 2023-03-11
 */
public interface IPayOrderService 
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
     * 添加常规收费项目
     * @param code
     * @param ids
     */
    void addOrderNormal(String code, long[] ids);
}
