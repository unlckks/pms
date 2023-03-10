package com.powernode.pay.entity.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: MingYun
 * @Date: 2023-03-09 16:36
 */
@Data
public class ContractDto {
    //主键
    private Long id;
    //商铺ID
    @NotNull
    private Long houseId;
    //业主ID
    @NotNull
    private Long ownerId;
    //合同类型
    @NotBlank
    private String contractType;
    //从事行业
    private String userTrade;
    //行业详情
    private String userTradeDetail;
    //周期
    private String period;
    //租金
    private BigDecimal rentFee;
    //开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    //费用ID
    private Long [] contractFeeIds;
}

