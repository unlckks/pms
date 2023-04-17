package com.mingyun.pay.entity.dto;

import com.mingyun.pay.domain.PayOrder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Author: MingYun
 * @Date: 2023-03-13 17:26
 */
@Data
public class TradeOrderDto {
    /**
     * 支付类型
     */
    @NotBlank
    private String payType;

    /**
     * 商铺ID
     */
    @NotBlank
    private Long houseId;
    /**
     * 所选择的收费项目
     */
    @NotBlank
    private List<PayOrder> payOrders;
}
