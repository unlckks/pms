package com.powernode.park.domain;

import java.math.BigDecimal;
import com.powernode.common.annotation.Excel;
import com.powernode.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 停车场计费规则对象 park_charging_rules
 * 
 * @author mingyun
 * @date 2023-03-08
 */
public class ParkChargingRules extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 规则名 */
    @Excel(name = "规则名")
    private String name;

    /** 免费分钟 */
    @Excel(name = "免费分钟")
    private Integer freeMin;

    /** 首段收费时间 */
    @Excel(name = "首段收费时间")
    private Integer firstMin;

    /** 首段收费金额 */
    @Excel(name = "首段收费金额")
    private BigDecimal firstMoney;

    /** 超过首段间隔时间 */
    @Excel(name = "超过首段间隔时间")
    private Integer afterStepMin;

    /** 超过首段收费金额 */
    @Excel(name = "超过首段收费金额")
    private BigDecimal afterStepMoney;

    /** 尾段开始时间 */
    @Excel(name = "尾段开始时间")
    private Integer lastMin;

    /** 尾段间隔时间 */
    @Excel(name = "尾段间隔时间")
    private Integer lastStepMin;

    /** 尾段间隔收费 */
    @Excel(name = "尾段间隔收费")
    private BigDecimal lastStepMoney;

    /** 24小时收费上限 */
    @Excel(name = "24小时收费上限")
    private BigDecimal maxMoney;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setFreeMin(Integer freeMin) 
    {
        this.freeMin = freeMin;
    }

    public Integer getFreeMin() 
    {
        return freeMin;
    }
    public void setFirstMin(Integer firstMin) 
    {
        this.firstMin = firstMin;
    }

    public Integer getFirstMin() 
    {
        return firstMin;
    }
    public void setFirstMoney(BigDecimal firstMoney) 
    {
        this.firstMoney = firstMoney;
    }

    public BigDecimal getFirstMoney() 
    {
        return firstMoney;
    }
    public void setAfterStepMin(Integer afterStepMin) 
    {
        this.afterStepMin = afterStepMin;
    }

    public Integer getAfterStepMin() 
    {
        return afterStepMin;
    }
    public void setAfterStepMoney(BigDecimal afterStepMoney) 
    {
        this.afterStepMoney = afterStepMoney;
    }

    public BigDecimal getAfterStepMoney() 
    {
        return afterStepMoney;
    }
    public void setLastMin(Integer lastMin) 
    {
        this.lastMin = lastMin;
    }

    public Integer getLastMin() 
    {
        return lastMin;
    }
    public void setLastStepMin(Integer lastStepMin) 
    {
        this.lastStepMin = lastStepMin;
    }

    public Integer getLastStepMin() 
    {
        return lastStepMin;
    }
    public void setLastStepMoney(BigDecimal lastStepMoney) 
    {
        this.lastStepMoney = lastStepMoney;
    }

    public BigDecimal getLastStepMoney() 
    {
        return lastStepMoney;
    }
    public void setMaxMoney(BigDecimal maxMoney) 
    {
        this.maxMoney = maxMoney;
    }

    public BigDecimal getMaxMoney() 
    {
        return maxMoney;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("freeMin", getFreeMin())
            .append("firstMin", getFirstMin())
            .append("firstMoney", getFirstMoney())
            .append("afterStepMin", getAfterStepMin())
            .append("afterStepMoney", getAfterStepMoney())
            .append("lastMin", getLastMin())
            .append("lastStepMin", getLastStepMin())
            .append("lastStepMoney", getLastStepMoney())
            .append("maxMoney", getMaxMoney())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
