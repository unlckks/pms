package com.mingyun.asset.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mingyun.common.annotation.Excel;
import com.mingyun.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商铺管理对象 asset_house
 * 
 * @author mingyun
 * @date 2023-03-04
 */
public class AssetHouse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 房号 */
    @Excel(name = "房号")
    private String code;

    /** 完整编号 */
    @Excel(name = "完整编号")
    private String name;

    /** 商圈 */
    @Excel(name = "商圈")
    private Long blockId;

    /** 楼栋 */
    @Excel(name = "楼栋")
    private Long buildingId;

    /** 楼层 */
    @Excel(name = "楼层")
    private String floor;

    /** 状态 */
    @Excel(name = "状态")
    private String state;

    /** 状态结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "状态结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date stateEndTime;

    /** 绑定的单位 */
    @Excel(name = "绑定的单位")
    private String stateCompany;

    /** 占地面积 */
    @Excel(name = "占地面积")
    private BigDecimal buildingSquare;

    /** 使用面积 */
    @Excel(name = "使用面积")
    private BigDecimal useSquare;

    /** 租金 */
    @Excel(name = "租金")
    private BigDecimal rentFee;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setBlockId(Long blockId) 
    {
        this.blockId = blockId;
    }

    public Long getBlockId() 
    {
        return blockId;
    }
    public void setBuildingId(Long buildingId) 
    {
        this.buildingId = buildingId;
    }

    public Long getBuildingId() 
    {
        return buildingId;
    }
    public void setFloor(String floor) 
    {
        this.floor = floor;
    }

    public String getFloor() 
    {
        return floor;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }
    public void setStateEndTime(Date stateEndTime) 
    {
        this.stateEndTime = stateEndTime;
    }

    public Date getStateEndTime() 
    {
        return stateEndTime;
    }
    public void setStateCompany(String stateCompany) 
    {
        this.stateCompany = stateCompany;
    }

    public String getStateCompany() 
    {
        return stateCompany;
    }
    public void setBuildingSquare(BigDecimal buildingSquare) 
    {
        this.buildingSquare = buildingSquare;
    }

    public BigDecimal getBuildingSquare() 
    {
        return buildingSquare;
    }
    public void setUseSquare(BigDecimal useSquare) 
    {
        this.useSquare = useSquare;
    }

    public BigDecimal getUseSquare() 
    {
        return useSquare;
    }
    public void setRentFee(BigDecimal rentFee) 
    {
        this.rentFee = rentFee;
    }

    public BigDecimal getRentFee() 
    {
        return rentFee;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("name", getName())
            .append("blockId", getBlockId())
            .append("buildingId", getBuildingId())
            .append("floor", getFloor())
            .append("state", getState())
            .append("stateEndTime", getStateEndTime())
            .append("stateCompany", getStateCompany())
            .append("buildingSquare", getBuildingSquare())
            .append("useSquare", getUseSquare())
            .append("rentFee", getRentFee())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
