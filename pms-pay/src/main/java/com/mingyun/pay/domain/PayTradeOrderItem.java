package com.mingyun.pay.domain;

import java.math.BigDecimal;
import com.mingyun.common.annotation.Excel;
import com.mingyun.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 支付订单详情对象 pay_trade_order_item
 *
 * @author mingyun
 * @date 2023-03-13
 */
public class PayTradeOrderItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** pay_order里面的order_id */
    private String orderId;

    /** 订单表的ID */
    @Excel(name = "订单表的ID")
    private String tradeId;

    /** 收费项目名 */
    @Excel(name = "收费项目名")
    private String itemName;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    /** 数量 */
    @Excel(name = "数量")
    private Long num;

    /** 总金额 */
    @Excel(name = "总金额")
    private BigDecimal amt;

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderId()
    {
        return orderId;
    }
    public void setTradeId(String tradeId)
    {
        this.tradeId = tradeId;
    }

    public String getTradeId()
    {
        return tradeId;
    }
    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public String getItemName()
    {
        return itemName;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }
    public void setNum(Long num)
    {
        this.num = num;
    }

    public Long getNum()
    {
        return num;
    }
    public void setAmt(BigDecimal amt)
    {
        this.amt = amt;
    }

    public BigDecimal getAmt()
    {
        return amt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("orderId", getOrderId())
                .append("tradeId", getTradeId())
                .append("itemName", getItemName())
                .append("price", getPrice())
                .append("num", getNum())
                .append("amt", getAmt())
                .toString();
    }
}
