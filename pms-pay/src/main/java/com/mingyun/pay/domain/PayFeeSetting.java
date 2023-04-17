package com.mingyun.pay.domain;

import com.mingyun.common.annotation.Excel;
import com.mingyun.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 租售合同管理对象 pay_fee_setting
 * 
 * @author mingyun
 * @date 2023-03-09
 */
public class PayFeeSetting extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 收费项ID */
    @Excel(name = "收费项ID")
    private Long feeItemId;

    /** 出租还是出售默认项(rented/selled) */
    @Excel(name = "出租还是出售默认项(rented/selled)")
    private String type;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFeeItemId(Long feeItemId) 
    {
        this.feeItemId = feeItemId;
    }

    public Long getFeeItemId() 
    {
        return feeItemId;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("feeItemId", getFeeItemId())
            .append("type", getType())
            .toString();
    }
}
