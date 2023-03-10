package com.powernode.pay.entity.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: MingYun
 * @Date: 2023-03-09 16:34
 */
@Data
public class ContractVo {
    //商铺ID
    private Long resourceId;
    //商铺名
    private String resourceName;
    //状态
    private String state; //出售 出租 空置
    //业主ID
    private Long ownerId;
    //业主名称
    private String ownerName;
    //业主公司
    private String company;
    //业主电话
    private String phone;
    //参考租金house表里面
    private BigDecimal defaultRentFee;
    //租金 合同表里面
    private BigDecimal rentFee;
    //行业
    private String userTrade;
    //合同类型 //出租  出售
    private String contractType;
    //开始时间
    private Date beginDate;
    //合同周期
    private String period;
    //合同编号
    private String code;
    //结束时间
    private Date endDate;
    //合同状态
    private  String contractState ;


}
