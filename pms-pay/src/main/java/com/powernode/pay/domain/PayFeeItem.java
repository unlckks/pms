package com.powernode.pay.domain;

import java.math.BigDecimal;
import com.powernode.common.annotation.Excel;
import com.powernode.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 收费项目管理对象 pay_fee_item
 * 
 * @author mingyun
 * @date 2023-03-06
 */
public class PayFeeItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 收费项分类 */
    @Excel(name = "收费项分类")
    private String type;

    /** 收费项目名 */
    @Excel(name = "收费项目名")
    private String name;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    /** 数量类型 */
    @Excel(name = "数量类型")
    private String numType;

    /** 费用周期 */
    @Excel(name = "费用周期")
    private Integer period;

    /** 费用公式 */
    @Excel(name = "费用公式")
    private String formula;

    /** 自定义 */
    @Excel(name = "自定义")
    private String formulaExpression;

    /** 是否产生滞纳金 */
    @Excel(name = "是否产生滞纳金")
    private String lateFeeEnable;

    /** 滞纳金延迟多久收 */
    @Excel(name = "滞纳金延迟多久收")
    private Long lateFeeDays;

    /** 滞纳金比例 */
    @Excel(name = "滞纳金比例")
    private String lateFeeRate;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setNumType(String numType) 
    {
        this.numType = numType;
    }

    public String getNumType() 
    {
        return numType;
    }
    public void setPeriod(Integer period) 
    {
        this.period = period;
    }

    public Integer getPeriod() 
    {
        return period;
    }
    public void setFormula(String formula) 
    {
        this.formula = formula;
    }

    public String getFormula() 
    {
        return formula;
    }
    public void setFormulaExpression(String formulaExpression) 
    {
        this.formulaExpression = formulaExpression;
    }

    public String getFormulaExpression() 
    {
        return formulaExpression;
    }
    public void setLateFeeEnable(String lateFeeEnable) 
    {
        this.lateFeeEnable = lateFeeEnable;
    }

    public String getLateFeeEnable() 
    {
        return lateFeeEnable;
    }
    public void setLateFeeDays(Long lateFeeDays) 
    {
        this.lateFeeDays = lateFeeDays;
    }

    public Long getLateFeeDays() 
    {
        return lateFeeDays;
    }
    public void setLateFeeRate(String lateFeeRate) 
    {
        this.lateFeeRate = lateFeeRate;
    }

    public String getLateFeeRate() 
    {
        return lateFeeRate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("name", getName())
            .append("price", getPrice())
            .append("numType", getNumType())
            .append("period", getPeriod())
            .append("formula", getFormula())
            .append("formulaExpression", getFormulaExpression())
            .append("lateFeeEnable", getLateFeeEnable())
            .append("lateFeeDays", getLateFeeDays())
            .append("lateFeeRate", getLateFeeRate())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
