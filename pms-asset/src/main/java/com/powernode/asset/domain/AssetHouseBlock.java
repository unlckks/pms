package com.powernode.asset.domain;

import com.powernode.common.annotation.Excel;
import com.powernode.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商圈管理对象 asset_house_block
 * 
 * @author powernode
 * @date 2023-03-04
 */
public class AssetHouseBlock extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 商圈编号 */
    @Excel(name = "商圈编号")
    private String code;

    /** 商圈名称 */
    @Excel(name = "商圈名称")
    private String name;

    /** 商圈地址 */
    @Excel(name = "商圈地址")
    private String address;

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
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("name", getName())
            .append("address", getAddress())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
