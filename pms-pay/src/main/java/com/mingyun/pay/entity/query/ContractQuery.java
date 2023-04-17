package com.mingyun.pay.entity.query;

import lombok.Data;

/**
 * @Author: MingYun
 * @Date: 2023-03-09 16:31
 */
@Data
public class ContractQuery {
    //业主姓名
    private String ownerName;

    //商铺完整编号
    private String resourceName;

    //状态
    private String state; //出租  出售  空置
}
