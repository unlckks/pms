package com.mingyun.park.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.mingyun.common.annotation.Excel;
import com.mingyun.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 停车场对象 park
 * 
 * @author mingyun
 * @date 2023-03-08
 */
public class Park extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商铺id */
    private Long id;

    /** 停车场名字 */
    @Excel(name = "停车场名字")
    private String name;

    /** 停车场照片 */
    private String image;

    /** 经度 */
    private BigDecimal positionX;

    /** 纬度 */
    private BigDecimal positionY;

    /** 开始营业时间 */
    private Date startBusinessHours;

    /** 结束营业时间 */
    private Date endBusinessHours;

    /** 车位数 */
    @Excel(name = "车位数")
    private Long parkingSpaceNumber;

    /** 已用车位数 */
    @Excel(name = "已用车位数")
    private Long usedParkingSpaceNumber;

    /** 停车场地址 */
    @Excel(name = "停车场地址")
    private String address;

    /** 手机 */
    @Excel(name = "手机")
    private String phone;

    /** 座机 */
    private String landline;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 国家id */
    private String countryId;

    /** 省份id */
    private String provinceId;

    /** 州或城市id */
    private String stateOrCityId;

    /** 区或县id */
    private String zoneOrCountyId;

    /** 乡镇id */
    private String townshipId;

    /** 收费规则 */
    @Excel(name = "收费规则")
    private String ruleId;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
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
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
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
    public void setStartBusinessHours(Date startBusinessHours) 
    {
        this.startBusinessHours = startBusinessHours;
    }

    public Date getStartBusinessHours() 
    {
        return startBusinessHours;
    }
    public void setEndBusinessHours(Date endBusinessHours) 
    {
        this.endBusinessHours = endBusinessHours;
    }

    public Date getEndBusinessHours() 
    {
        return endBusinessHours;
    }
    public void setParkingSpaceNumber(Long parkingSpaceNumber) 
    {
        this.parkingSpaceNumber = parkingSpaceNumber;
    }

    public Long getParkingSpaceNumber() 
    {
        return parkingSpaceNumber;
    }
    public void setUsedParkingSpaceNumber(Long usedParkingSpaceNumber) 
    {
        this.usedParkingSpaceNumber = usedParkingSpaceNumber;
    }

    public Long getUsedParkingSpaceNumber() 
    {
        return usedParkingSpaceNumber;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setLandline(String landline) 
    {
        this.landline = landline;
    }

    public String getLandline() 
    {
        return landline;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setCountryId(String countryId) 
    {
        this.countryId = countryId;
    }

    public String getCountryId() 
    {
        return countryId;
    }
    public void setProvinceId(String provinceId) 
    {
        this.provinceId = provinceId;
    }

    public String getProvinceId() 
    {
        return provinceId;
    }
    public void setStateOrCityId(String stateOrCityId) 
    {
        this.stateOrCityId = stateOrCityId;
    }

    public String getStateOrCityId() 
    {
        return stateOrCityId;
    }
    public void setZoneOrCountyId(String zoneOrCountyId) 
    {
        this.zoneOrCountyId = zoneOrCountyId;
    }

    public String getZoneOrCountyId() 
    {
        return zoneOrCountyId;
    }
    public void setTownshipId(String townshipId) 
    {
        this.townshipId = townshipId;
    }

    public String getTownshipId() 
    {
        return townshipId;
    }
    public void setRuleId(String ruleId) 
    {
        this.ruleId = ruleId;
    }

    public String getRuleId() 
    {
        return ruleId;
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
            .append("image", getImage())
            .append("positionX", getPositionX())
            .append("positionY", getPositionY())
            .append("startBusinessHours", getStartBusinessHours())
            .append("endBusinessHours", getEndBusinessHours())
            .append("parkingSpaceNumber", getParkingSpaceNumber())
            .append("usedParkingSpaceNumber", getUsedParkingSpaceNumber())
            .append("address", getAddress())
            .append("phone", getPhone())
            .append("landline", getLandline())
            .append("status", getStatus())
            .append("countryId", getCountryId())
            .append("provinceId", getProvinceId())
            .append("stateOrCityId", getStateOrCityId())
            .append("zoneOrCountyId", getZoneOrCountyId())
            .append("townshipId", getTownshipId())
            .append("ruleId", getRuleId())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
