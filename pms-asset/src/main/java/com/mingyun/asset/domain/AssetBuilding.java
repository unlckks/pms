package com.mingyun.asset.domain;

import com.mingyun.common.annotation.Excel;
import com.mingyun.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 楼栋管理对象 asset_building
 * 
 * @author mingyun
 * @date 2023-03-04
 */
public class AssetBuilding extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 商圈编号 */
    @Excel(name = "商圈编号")
    private Long blockId;

    /** 楼栋名称 */
    @Excel(name = "楼栋名称")
    private String name;

    /** 楼栋号 */
    @Excel(name = "楼栋号")
    private String number;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBlockId(Long blockId) 
    {
        this.blockId = blockId;
    }

    public Long getBlockId() 
    {
        return blockId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setNumber(String number) 
    {
        this.number = number;
    }

    public String getNumber() 
    {
        return number;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("blockId", getBlockId())
            .append("name", getName())
            .append("number", getNumber())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
