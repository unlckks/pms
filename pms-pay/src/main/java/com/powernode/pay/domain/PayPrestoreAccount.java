package com.powernode.pay.domain;

import java.math.BigDecimal;
import com.powernode.common.annotation.Excel;
import com.powernode.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 预存款余额账户对象 pay_prestore_account
 * 
 * @author powernode
 * @date 2023-03-07
 */
public class PayPrestoreAccount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 业主ID */
    @Excel(name = "业主ID")
    private Long feeUserId;

    /** 业主名称 */
    @Excel(name = "业主名称")
    private String feeUserName;

    /** 是否指定收费项目 */
    @Excel(name = "是否指定收费项目")
    private String useFeeItem;

    /** 收费项目名称 */
    @Excel(name = "收费项目名称")
    private String feeItemName;

    /** 收费项目ID */
    @Excel(name = "收费项目ID")
    private Long feeItemId;

    /** 总金额 */
    @Excel(name = "总金额")
    private BigDecimal amt;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
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
    public void setUseFeeItem(String useFeeItem) 
    {
        this.useFeeItem = useFeeItem;
    }

    public String getUseFeeItem() 
    {
        return useFeeItem;
    }
    public void setFeeItemName(String feeItemName) 
    {
        this.feeItemName = feeItemName;
    }

    public String getFeeItemName() 
    {
        return feeItemName;
    }
    public void setFeeItemId(Long feeItemId) 
    {
        this.feeItemId = feeItemId;
    }

    public Long getFeeItemId() 
    {
        return feeItemId;
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
            .append("id", getId())
            .append("feeUserId", getFeeUserId())
            .append("feeUserName", getFeeUserName())
            .append("useFeeItem", getUseFeeItem())
            .append("feeItemName", getFeeItemName())
            .append("feeItemId", getFeeItemId())
            .append("amt", getAmt())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
