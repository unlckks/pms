package com.powernode.pay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.powernode.pay.mapper.PayTradeOrderItemMapper;
import com.powernode.pay.domain.PayTradeOrderItem;
import com.powernode.pay.service.IPayTradeOrderItemService;

/**
 * 支付订单详情Service业务层处理
 * 
 * @author mingyun
 * @date 2023-03-13
 */
@Service
public class PayTradeOrderItemServiceImpl implements IPayTradeOrderItemService 
{
    @Autowired
    private PayTradeOrderItemMapper payTradeOrderItemMapper;

    /**
     * 查询支付订单详情
     * 
     * @param orderId 支付订单详情主键
     * @return 支付订单详情
     */
    @Override
    public PayTradeOrderItem selectPayTradeOrderItemByOrderId(String orderId)
    {
        return payTradeOrderItemMapper.selectPayTradeOrderItemByOrderId(orderId);
    }

    /**
     * 查询支付订单详情列表
     * 
     * @param payTradeOrderItem 支付订单详情
     * @return 支付订单详情
     */
    @Override
    public List<PayTradeOrderItem> selectPayTradeOrderItemList(PayTradeOrderItem payTradeOrderItem)
    {
        return payTradeOrderItemMapper.selectPayTradeOrderItemList(payTradeOrderItem);
    }

    /**
     * 新增支付订单详情
     * 
     * @param payTradeOrderItem 支付订单详情
     * @return 结果
     */
    @Override
    public int insertPayTradeOrderItem(PayTradeOrderItem payTradeOrderItem)
    {
        return payTradeOrderItemMapper.insertPayTradeOrderItem(payTradeOrderItem);
    }

    /**
     * 修改支付订单详情
     * 
     * @param payTradeOrderItem 支付订单详情
     * @return 结果
     */
    @Override
    public int updatePayTradeOrderItem(PayTradeOrderItem payTradeOrderItem)
    {
        return payTradeOrderItemMapper.updatePayTradeOrderItem(payTradeOrderItem);
    }

    /**
     * 批量删除支付订单详情
     * 
     * @param orderIds 需要删除的支付订单详情主键
     * @return 结果
     */
    @Override
    public int deletePayTradeOrderItemByOrderIds(String[] orderIds)
    {
        return payTradeOrderItemMapper.deletePayTradeOrderItemByOrderIds(orderIds);
    }

    /**
     * 删除支付订单详情信息
     * 
     * @param orderId 支付订单详情主键
     * @return 结果
     */
    @Override
    public int deletePayTradeOrderItemByOrderId(String orderId)
    {
        return payTradeOrderItemMapper.deletePayTradeOrderItemByOrderId(orderId);
    }
}
