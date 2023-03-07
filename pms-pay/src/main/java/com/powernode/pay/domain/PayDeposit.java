package com.powernode.pay.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.powernode.common.annotation.Excel;
import com.powernode.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 押金对象 pay_deposit
 * 
 * @author powernode
 * @date 2023-03-06
 */
public class PayDeposit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 资源名 */
    @Excel(name = "资源名")
    private String resourceName;

    /** 资源id */
    private Long resourceId;

    /** 商圈 */
    @Excel(name = "商圈")
    private Long block;

    /** 收费项目ID */
    @Excel(name = "收费项目ID")
    private Long feeItemId;

    /** 收费项名 */
    @Excel(name = "收费项名")
    private String feeItemName;

    /** 客户姓名 */
    @Excel(name = "客户姓名")
    private String feeUser;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal amt;

    /** 付款方式 */
    @Excel(name = "付款方式")
    private String payType;

    /** 退费方式 */
    @Excel(name = "退费方式")
    private String refundType;

    /** 收款人 */
    @Excel(name = "收款人")
    private String operateUser;

    /** 收款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operateTime;

    /** 退款人 */
    @Excel(name = "退款人")
    private String refundUser;

    /** 退款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "退款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date refundTime;

    /** 支付单号 */
    @Excel(name = "支付单号")
    private String payNo;

    /** 退款单号 */
    @Excel(name = "退款单号")
    private String refundNo;

    /** 状态 */
    @Excel(name = "状态")
    private String state;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setResourceName(String resourceName) 
    {
        this.resourceName = resourceName;
    }

    public String getResourceName() 
    {
        return resourceName;
    }
    public void setResourceId(Long resourceId) 
    {
        this.resourceId = resourceId;
    }

    public Long getResourceId() 
    {
        return resourceId;
    }
    public void setBlock(Long block) 
    {
        this.block = block;
    }

    public Long getBlock() 
    {
        return block;
    }
    public void setFeeItemId(Long feeItemId) 
    {
        this.feeItemId = feeItemId;
    }

    public Long getFeeItemId() 
    {
        return feeItemId;
    }
    public void setFeeItemName(String feeItemName) 
    {
        this.feeItemName = feeItemName;
    }

    public String getFeeItemName() 
    {
        return feeItemName;
    }
    public void setFeeUser(String feeUser) 
    {
        this.feeUser = feeUser;
    }

    public String getFeeUser() 
    {
        return feeUser;
    }
    public void setAmt(BigDecimal amt) 
    {
        this.amt = amt;
    }

    public BigDecimal getAmt() 
    {
        return amt;
    }
    public void setPayType(String payType) 
    {
        this.payType = payType;
    }

    public String getPayType() 
    {
        return payType;
    }
    public void setRefundType(String refundType) 
    {
        this.refundType = refundType;
    }

    public String getRefundType() 
    {
        return refundType;
    }
    public void setOperateUser(String operateUser) 
    {
        this.operateUser = operateUser;
    }

    public String getOperateUser() 
    {
        return operateUser;
    }
    public void setOperateTime(Date operateTime) 
    {
        this.operateTime = operateTime;
    }

    public Date getOperateTime() 
    {
        return operateTime;
    }
    public void setRefundUser(String refundUser) 
    {
        this.refundUser = refundUser;
    }

    public String getRefundUser() 
    {
        return refundUser;
    }
    public void setRefundTime(Date refundTime) 
    {
        this.refundTime = refundTime;
    }

    public Date getRefundTime() 
    {
        return refundTime;
    }
    public void setPayNo(String payNo) 
    {
        this.payNo = payNo;
    }

    public String getPayNo() 
    {
        return payNo;
    }
    public void setRefundNo(String refundNo) 
    {
        this.refundNo = refundNo;
    }

    public String getRefundNo() 
    {
        return refundNo;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("resourceName", getResourceName())
            .append("resourceId", getResourceId())
            .append("block", getBlock())
            .append("feeItemId", getFeeItemId())
            .append("feeItemName", getFeeItemName())
            .append("feeUser", getFeeUser())
            .append("amt", getAmt())
            .append("payType", getPayType())
            .append("refundType", getRefundType())
            .append("operateUser", getOperateUser())
            .append("operateTime", getOperateTime())
            .append("refundUser", getRefundUser())
            .append("refundTime", getRefundTime())
            .append("remark", getRemark())
            .append("payNo", getPayNo())
            .append("refundNo", getRefundNo())
            .append("state", getState())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
