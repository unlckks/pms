package com.powernode.pay.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.powernode.asset.domain.AssetHouse;
import com.powernode.asset.mapper.AssetHouseMapper;
import com.powernode.common.utils.DateUtils;
import com.powernode.common.utils.SecurityUtils;
import com.powernode.common.utils.uuid.IdUtils;
import com.powernode.pay.constants.PayConstants;
import com.powernode.pay.domain.PayOrder;
import com.powernode.pay.domain.PayTradeOrderItem;
import com.powernode.pay.entity.dto.TradeOrderDto;
import com.powernode.pay.mapper.PayOrderMapper;
import com.powernode.pay.mapper.PayTradeOrderItemMapper;
import com.powernode.system.service.ISysConfigService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.powernode.pay.mapper.PayTradeOrderMapper;
import com.powernode.pay.domain.PayTradeOrder;
import com.powernode.pay.service.IPayTradeOrderService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 支付订单Service业务层处理
 * 
 * @author mingyun
 * @date 2023-03-13
 */
@Service
public class PayTradeOrderServiceImpl implements IPayTradeOrderService
{
    @Resource
    private PayTradeOrderMapper payTradeOrderMapper;

    @Resource
    private AssetHouseMapper assetHouseMapper ;

    @Resource
    private ISysConfigService configService ;

    @Resource
    private PayTradeOrderItemMapper payTradeOrderItemMapper ;

    @Resource
    private PayOrderMapper payOrderMapper;


    /**
     * 查询支付订单
     *
     * @param id 支付订单主键
     * @return 支付订单
     */
    @Override
    public PayTradeOrder selectPayTradeOrderById(String id)
    {
        return payTradeOrderMapper.selectPayTradeOrderById(id);
    }

    /**
     * 查询支付订单列表
     * 
     * @param payTradeOrder 支付订单
     * @return 支付订单
     */
    @Override
    public List<PayTradeOrder> selectPayTradeOrderList(PayTradeOrder payTradeOrder)
    {
        return payTradeOrderMapper.selectPayTradeOrderList(payTradeOrder);
    }

    /**
     * 新增支付订单
     *
     * @param payTradeOrder 支付订单
     * @return 结果
     */
    @Override
    public int insertPayTradeOrder(PayTradeOrder payTradeOrder)
    {
        payTradeOrder.setCreateTime(DateUtils.getNowDate());
        return payTradeOrderMapper.insertPayTradeOrder(payTradeOrder);
    }

    /**
     * 修改支付订单
     *
     * @param payTradeOrder 支付订单
     * @return 结果
     */
    @Override
    public int updatePayTradeOrder(PayTradeOrder payTradeOrder)
    {
        payTradeOrder.setUpdateTime(DateUtils.getNowDate());
        return payTradeOrderMapper.updatePayTradeOrder(payTradeOrder);
    }

    /**
     * 批量删除支付订单
     *
     * @param ids 需要删除的支付订单主键
     * @return 结果
     */
    @Override
    public int deletePayTradeOrderByIds(String[] ids)
    {
        return payTradeOrderMapper.deletePayTradeOrderByIds(ids);
    }

    /**
     * 删除支付订单信息
     *
     * @param id 支付订单主键
     * @return 结果
     */
    @Override
    public int deletePayTradeOrderById(String id)
    {
        return payTradeOrderMapper.deletePayTradeOrderById(id);
    }

    /**
     * 创建支付的订单包含订单详情
     * @param tradeOrderDto
     */
    @Override
    @Transactional
    public void addPayTradeOrder(TradeOrderDto tradeOrderDto) {
       String payType =tradeOrderDto.getPayType();
       Long houseId =tradeOrderDto.getHouseId();
       //再根据houseId查询house
        AssetHouse assetHouse =this.assetHouseMapper.selectAssetHouseById(houseId);
        //设置主要订单号
        String  tradeId = IdUtils.createNoWithPrefix(configService.selectConfigByKey(PayConstants.PAY_PREFIX_TRADE_ORDER));
        //创建一个对象记录总金额
        BigDecimal amount =new BigDecimal("0");
        //创建String拼接subject
        String subject="商铺["+assetHouse.getName()+"]的";
        //创建String拼接body
        String body="";
        List<PayOrder> payOrders =tradeOrderDto.getPayOrders();
        //进行循环pay_trade_order_item
        for (PayOrder payOrder :payOrders){
            PayTradeOrderItem payTradeOrderItem =new PayTradeOrderItem() ;
            //把payorder里面的属性copy 到 payTradeOrderItem
            BeanUtils.copyProperties(payOrder,payTradeOrderItem);
            //设置主要订单号
            payTradeOrderItem.setTradeId(tradeId);
            payTradeOrderItemMapper.insertPayTradeOrderItem(payTradeOrderItem);
            //再进行计算总的金额
            amount =amount.add(payTradeOrderItem.getAmt());
            subject+="["+payOrder.getItemName()+"]";
            body = body +payOrder.getItemName()+",";
        }
        subject= subject+"缴费订单";
        //进行组装
        PayTradeOrder payTradeOrder =new PayTradeOrder() ;
        payTradeOrder.setId(tradeId);
        payTradeOrder.setAmount(amount);
        payTradeOrder.setSubject(subject);
        payTradeOrder.setBody(body);
        if (payType.equals(PayConstants.PAY_TYPE_CASH)){
            payTradeOrder.setWayCode(PayConstants.PAY_TYPE_CASH);
            payTradeOrder.setState(PayConstants.PAY_ORDER_STATE_2);
            payTradeOrder.setSuccessTime(new Date());
            //进行处理现金支付的问题
            for (PayOrder payOrder : payOrders){
                payOrder.setPayState("payed");
                payOrder.setPayType(payType);
                this.payOrderMapper.updatePayOrder(payOrder);
            }
        }else  if (payType.equals(PayConstants.PAY_TYPE_ALIPAY)){
            payTradeOrder.setWayCode(PayConstants.PAY_TYPE_ALIPAY);
            payTradeOrder.setState(PayConstants.PAY_ORDER_STATE_0);
        }
        payTradeOrder.setCreateTime(new Date());
        payTradeOrder.setCreateBy(SecurityUtils.getUsername());
        //进行保存
        this.payTradeOrderMapper.insertPayTradeOrder(payTradeOrder);

    }
}
