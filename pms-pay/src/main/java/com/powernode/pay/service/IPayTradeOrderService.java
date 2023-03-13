package com.powernode.pay.service;

import java.util.List;
import com.powernode.pay.domain.PayTradeOrder;
import com.powernode.pay.entity.dto.TradeOrderDto;

/**
 * 支付订单Service接口
 * 
 * @author mingyun
 * @date 2023-03-13
 */
public interface IPayTradeOrderService 
{
    /**
     * 查询支付订单
     * 
     * @param id 支付订单主键
     * @return 支付订单
     */
    public PayTradeOrder selectPayTradeOrderById(String id);

    /**
     * 查询支付订单列表
     * 
     * @param payTradeOrder 支付订单
     * @return 支付订单集合
     */
    public List<PayTradeOrder> selectPayTradeOrderList(PayTradeOrder payTradeOrder);

    /**
     * 新增支付订单
     * 
     * @param payTradeOrder 支付订单
     * @return 结果
     */
    public int insertPayTradeOrder(PayTradeOrder payTradeOrder);

    /**
     * 修改支付订单
     * 
     * @param payTradeOrder 支付订单
     * @return 结果
     */
    public int updatePayTradeOrder(PayTradeOrder payTradeOrder);

    /**
     * 批量删除支付订单
     * 
     * @param ids 需要删除的支付订单主键集合
     * @return 结果
     */
    public int deletePayTradeOrderByIds(String[] ids);

    /**
     * 删除支付订单信息
     * 
     * @param id 支付订单主键
     * @return 结果
     */
    public int deletePayTradeOrderById(String id);

    /**
     * 创建支付的订单包含订单详情
     * @param tradeOrderDto
     */
    void addPayTradeOrder(TradeOrderDto tradeOrderDto);

}
