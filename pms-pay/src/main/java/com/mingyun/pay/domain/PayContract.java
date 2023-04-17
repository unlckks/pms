package com.mingyun.pay.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mingyun.common.annotation.Excel;
import com.mingyun.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 出租出售合同对象 pay_contract
 * 
 * @author mingyun
 * @date 2023-03-09
 */
public class PayContract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 合同编号HT202303091212125612 */
    @Excel(name = "合同编号HT202303091212125612")
    private String code;

    /** 商铺ID */
    @Excel(name = "商铺ID")
    private Long houseId;

    /** 业主id */
    @Excel(name = "业主id")
    private Long ownerId;

    /** 合同类型(sale/rented) */
    @Excel(name = "合同类型(sale/rented)")
    private String contractType;

    /** 租金 */
    @Excel(name = "租金")
    private BigDecimal rentFee;

    /** 开始使用时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始使用时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beginDate;

    /** 合约有效时间 */
    @Excel(name = "合约有效时间")
    private String period;

    /** 结束使用时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束使用时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 从事的行业 */
    @Excel(name = "从事的行业")
    private String userTrade;

    /** 行业的详细描述 */
    @Excel(name = "行业的详细描述")
    private String userTradeDetail;

    /** 标志（0正常 1,撤销,2代表结束） */
    @Excel(name = "标志", readConverterExp = "0=正常,1=,撤销,2代表结束")
    private String state;

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
    public void setHouseId(Long houseId) 
    {
        this.houseId = houseId;
    }

    public Long getHouseId() 
    {
        return houseId;
    }
    public void setOwnerId(Long ownerId) 
    {
        this.ownerId = ownerId;
    }

    public Long getOwnerId() 
    {
        return ownerId;
    }
    public void setContractType(String contractType) 
    {
        this.contractType = contractType;
    }

    public String getContractType() 
    {
        return contractType;
    }
    public void setRentFee(BigDecimal rentFee) 
    {
        this.rentFee = rentFee;
    }

    public BigDecimal getRentFee() 
    {
        return rentFee;
    }
    public void setBeginDate(Date beginDate) 
    {
        this.beginDate = beginDate;
    }

    public Date getBeginDate() 
    {
        return beginDate;
    }
    public void setPeriod(String period) 
    {
        this.period = period;
    }

    public String getPeriod() 
    {
        return period;
    }
    public void setEndDate(Date endDate) 
    {
        this.endDate = endDate;
    }

    public Date getEndDate() 
    {
        return endDate;
    }
    public void setUserTrade(String userTrade) 
    {
        this.userTrade = userTrade;
    }

    public String getUserTrade() 
    {
        return userTrade;
    }
    public void setUserTradeDetail(String userTradeDetail) 
    {
        this.userTradeDetail = userTradeDetail;
    }

    public String getUserTradeDetail() 
    {
        return userTradeDetail;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("houseId", getHouseId())
            .append("ownerId", getOwnerId())
            .append("contractType", getContractType())
            .append("rentFee", getRentFee())
            .append("beginDate", getBeginDate())
            .append("period", getPeriod())
            .append("endDate", getEndDate())
            .append("userTrade", getUserTrade())
            .append("userTradeDetail", getUserTradeDetail())
            .append("state", getState())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
