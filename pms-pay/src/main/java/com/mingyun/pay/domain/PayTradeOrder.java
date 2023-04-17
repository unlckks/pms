package com.mingyun.pay.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mingyun.common.annotation.Excel;
import com.mingyun.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 支付订单对象 pay_trade_order
 * 
 * @author mingyun
 * @date 2023-03-13
 */
public class PayTradeOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 支付订单号 */
    private String id;

    /** 应用ID */
    @Excel(name = "应用ID")
    private String appId;

    /** 支付方式代码fee_method_options */
    @Excel(name = "支付方式代码fee_method_options")
    private String wayCode;

    /** 支付金额 */
    @Excel(name = "支付金额")
    private BigDecimal amount;

    /** 支付状态: 0-订单生成, 1-支付中, 2-支付成功, 3-支付失败, 4-已撤销, 5-已退款, 6-订单关闭 */
    @Excel(name = "支付状态: 0-订单生成, 1-支付中, 2-支付成功, 3-支付失败, 4-已撤销, 5-已退款, 6-订单关闭")
    private String state;

    /** 商品标题 */
    @Excel(name = "商品标题")
    private String subject;

    /** 商品描述信息 */
    @Excel(name = "商品描述信息")
    private String body;

    /** 渠道用户标识,如微信openId,支付宝账号 */
    @Excel(name = "渠道用户标识,如微信openId,支付宝账号")
    private String openId;

    /** 第三第支付的订单ID */
    @Excel(name = "第三第支付的订单ID")
    private String tradeNo;

    /** 渠道支付错误码 */
    @Excel(name = "渠道支付错误码")
    private String errCode;

    /** 渠道支付错误描述 */
    @Excel(name = "渠道支付错误描述")
    private String errMsg;

    /** 订单支付成功时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订单支付成功时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date successTime;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setAppId(String appId) 
    {
        this.appId = appId;
    }

    public String getAppId() 
    {
        return appId;
    }
    public void setWayCode(String wayCode) 
    {
        this.wayCode = wayCode;
    }

    public String getWayCode() 
    {
        return wayCode;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }
    public void setSubject(String subject) 
    {
        this.subject = subject;
    }

    public String getSubject() 
    {
        return subject;
    }
    public void setBody(String body) 
    {
        this.body = body;
    }

    public String getBody() 
    {
        return body;
    }
    public void setOpenId(String openId) 
    {
        this.openId = openId;
    }

    public String getOpenId() 
    {
        return openId;
    }
    public void setTradeNo(String tradeNo)
    {
        this.tradeNo = tradeNo;
    }

    public String getTradeNo()
    {
        return tradeNo;
    }
    public void setErrCode(String errCode) 
    {
        this.errCode = errCode;
    }

    public String getErrCode() 
    {
        return errCode;
    }
    public void setErrMsg(String errMsg) 
    {
        this.errMsg = errMsg;
    }

    public String getErrMsg() 
    {
        return errMsg;
    }
    public void setSuccessTime(Date successTime) 
    {
        this.successTime = successTime;
    }

    public Date getSuccessTime() 
    {
        return successTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("appId", getAppId())
            .append("wayCode", getWayCode())
            .append("amount", getAmount())
            .append("state", getState())
            .append("subject", getSubject())
            .append("body", getBody())
            .append("openId", getOpenId())
            .append("tradeNo", getTradeNo())
            .append("errCode", getErrCode())
            .append("errMsg", getErrMsg())
            .append("successTime", getSuccessTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
