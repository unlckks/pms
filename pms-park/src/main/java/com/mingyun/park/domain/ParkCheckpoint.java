package com.mingyun.park.domain;

import java.math.BigDecimal;
import com.mingyun.common.annotation.Excel;
import com.mingyun.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 道闸管理对象 park_checkpoint
 *
 * @author mingyun
 * @date 2023-03-11
 */
public class ParkCheckpoint extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 编号 */
    @Excel(name = "编号")
    private Long code;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 停车场id */
    @Excel(name = "停车场id")
    private Long carParkId;

    /** 停车场名 */
    @Excel(name = "停车场名")
    private String carParkName;

    /** 卡点位置经度 */
    private BigDecimal positionX;

    /** 卡点位置纬度 */
    private BigDecimal positionY;

    /** 位置描述 */
    private String positionDescribe;

    /** 所在楼层 */
    private Integer floor;

    /** 模式 */
    private Integer mode;

    /** 卡点状态 */
    @Excel(name = "卡点状态")
    private Integer status;

    /** 删除标志 */
    private String delFlag;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setCode(Long code)
    {
        this.code = code;
    }

    public Long getCode()
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
    public void setCarParkId(Long carParkId)
    {
        this.carParkId = carParkId;
    }

    public Long getCarParkId()
    {
        return carParkId;
    }
    public void setCarParkName(String carParkName)
    {
        this.carParkName = carParkName;
    }

    public String getCarParkName()
    {
        return carParkName;
    }
    public void setPositionX(BigDecimal positionX)
    {
        this.positionX = positionX;
    }

    public BigDecimal getPositionX()
    {
        return positionX;
    }
    public void setPositionY(BigDecimal positionY)
    {
        this.positionY = positionY;
    }

    public BigDecimal getPositionY()
    {
        return positionY;
    }
    public void setPositionDescribe(String positionDescribe)
    {
        this.positionDescribe = positionDescribe;
    }

    public String getPositionDescribe()
    {
        return positionDescribe;
    }
    public void setFloor(Integer floor)
    {
        this.floor = floor;
    }

    public Integer getFloor()
    {
        return floor;
    }
    public void setMode(Integer mode)
    {
        this.mode = mode;
    }

    public Integer getMode()
    {
        return mode;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
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
                .append("code", getCode())
                .append("name", getName())
                .append("carParkId", getCarParkId())
                .append("carParkName", getCarParkName())
                .append("positionX", getPositionX())
                .append("positionY", getPositionY())
                .append("positionDescribe", getPositionDescribe())
                .append("floor", getFloor())
                .append("mode", getMode())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
