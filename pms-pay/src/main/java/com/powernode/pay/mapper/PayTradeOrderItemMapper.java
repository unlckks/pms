package com.powernode.pay.mapper;

import java.util.List;
import com.powernode.pay.domain.PayTradeOrderItem;
import org.apache.ibatis.annotations.Param;

/**
 * 支付订单详情Mapper接口
 *
 * @author mingyun
 * @date 2023-03-13
 */
public interface PayTradeOrderItemMapper
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
     * 删除支付订单详情
     *
     * @param orderId 支付订单详情主键
     * @return 结果
     */
    public int deletePayTradeOrderItemByOrderId(String orderId);

    /**
     * 批量删除支付订单详情
     *
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePayTradeOrderItemByOrderIds(String[] orderIds);

    /**
     * 根据主订单ID查询订单详情
     *
     * @param tradeId
     * @return
     */
    List<PayTradeOrderItem> selectTradeOrderItemByTradeId(@Param("tradeId") String tradeId);
}
