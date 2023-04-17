package com.mingyun.pay.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mingyun.common.annotation.Excel;
import com.mingyun.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 费用预存对象 pay_prestore
 *
 * @author mingyun
 * @date 2023-03-07
 */
public class PayPrestore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 收费项目ID */
    @Excel(name = "收费项目ID")
    private String feeItemId;

    /** 收费项名 */
    @Excel(name = "收费项名")
    private String feeItemName;

    /** 客户ID */
    @Excel(name = "客户ID")
    private Long feeUserId;

    /** 客户姓名 */
    @Excel(name = "客户姓名")
    private String feeUserName;

    /** 发生金额 */
    @Excel(name = "发生金额")
    private BigDecimal amt;

    /** 付款方式 */
    @Excel(name = "付款方式")
    private String payType;

    /** 支取还是收款 */
    @Excel(name = "支取还是收款")
    private String type;

    /** 是否指定收费项 */
    @Excel(name = "是否指定收费项")
    private String useFeeItem;

    /** 收款人 */
    @Excel(name = "收款人")
    private String operateUser;

    /** 收款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operateTime;

    /** 支付单号 */
    @Excel(name = "支付单号")
    private String payNo;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFeeItemId(String feeItemId) 
    {
        this.feeItemId = feeItemId;
    }

    public String getFeeItemId() 
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
    public void setFeeUserId(Long feeUserId) 
    {
        this.feeUserId = feeUserId;
    }

    public Long getFeeUserId() 
    {
        return feeUserId;
    }
    public void setFeeUserName(String feeUserName) 
    {
        this.feeUserName = feeUserName;
    }

    public String getFeeUserName() 
    {
        return feeUserName;
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
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setUseFeeItem(String useFeeItem) 
    {
        this.useFeeItem = useFeeItem;
    }

    public String getUseFeeItem() 
    {
        return useFeeItem;
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
    public void setPayNo(String payNo) 
    {
        this.payNo = payNo;
    }

    public String getPayNo() 
    {
        return payNo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("feeItemId", getFeeItemId())
            .append("feeItemName", getFeeItemName())
            .append("feeUserId", getFeeUserId())
            .append("feeUserName", getFeeUserName())
            .append("amt", getAmt())
            .append("payType", getPayType())
            .append("type", getType())
            .append("useFeeItem", getUseFeeItem())
            .append("operateUser", getOperateUser())
            .append("operateTime", getOperateTime())
            .append("remark", getRemark())
            .append("payNo", getPayNo())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
