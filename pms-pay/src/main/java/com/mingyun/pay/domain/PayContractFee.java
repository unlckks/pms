package com.mingyun.pay.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mingyun.common.annotation.Excel;
import com.mingyun.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 合同收费项目对象 pay_contract_fee
 * 
 * @author mingyun
 * @date 2023-03-09
 */
public class PayContractFee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 收费项id */
    @Excel(name = "收费项id")
    private Long feeItemId;

    /** 合同ID */
    @Excel(name = "合同ID")
    private String contractId;

    /** 计费开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计费开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beginDate;

    /** 收费结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收费结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 下次计费时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下次计费时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date nextBillDate;

    /** 费用的征收次数 */
    @Excel(name = "费用的征收次数")
    private Long times;

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
    public void setContractId(String contractId) 
    {
        this.contractId = contractId;
    }

    public String getContractId() 
    {
        return contractId;
    }
    public void setBeginDate(Date beginDate) 
    {
        this.beginDate = beginDate;
    }

    public Date getBeginDate() 
    {
        return beginDate;
    }
    public void setEndDate(Date endDate) 
    {
        this.endDate = endDate;
    }

    public Date getEndDate() 
    {
        return endDate;
    }
    public void setNextBillDate(Date nextBillDate) 
    {
        this.nextBillDate = nextBillDate;
    }

    public Date getNextBillDate() 
    {
        return nextBillDate;
    }
    public void setTimes(Long times) 
    {
        this.times = times;
    }

    public Long getTimes() 
    {
        return times;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("feeItemId", getFeeItemId())
            .append("contractId", getContractId())
            .append("beginDate", getBeginDate())
            .append("endDate", getEndDate())
            .append("nextBillDate", getNextBillDate())
            .append("times", getTimes())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
