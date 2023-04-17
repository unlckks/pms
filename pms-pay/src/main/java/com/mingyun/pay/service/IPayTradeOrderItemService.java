package com.mingyun.pay.service;

import java.util.List;
import com.mingyun.pay.domain.PayTradeOrderItem;

/**
 * 支付订单详情Service接口
 * 
 * @author mingyun
 * @date 2023-03-13
 */
public interface IPayTradeOrderItemService 
{
    /**
     * 查询支付订单详情
     * 
     * @param orderId 支付订单详情主键
     * @return 支付订单详情
     */
    public PayTradeOrderItem selectPayTradeOrderItemByOrderId(String orderId);

    /**
     * 查询支付订单详情列表
     * 
     * @param payTradeOrderItem 支付订单详情
     * @return 支付订单详情集合
     */
    public List<PayTradeOrderItem> selectPayTradeOrderItemList(PayTradeOrderItem payTradeOrderItem);

    /**
     * 新增支付订单详情
     * 
     * @param payTradeOrderItem 支付订单详情
     * @return 结果
     */
    public int insertPayTradeOrderItem(PayTradeOrderItem payTradeOrderItem);

    /**
     * 修改支付订单详情
     * 
     * @param payTradeOrderItem 支付订单详情
     * @return 结果
     */
    public int updatePayTradeOrderItem(PayTradeOrderItem payTradeOrderItem);

    /**
     * 批量删除支付订单详情
     * 
     * @param orderIds 需要删除的支付订单详情主键集合
     * @return 结果
     */
    public int deletePayTradeOrderItemByOrderIds(String[] orderIds);

    /**
     * 删除支付订单详情信息
     * 
     * @param orderId 支付订单详情主键
     * @return 结果
     */
    public int deletePayTradeOrderItemByOrderId(String orderId);
}
