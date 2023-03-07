package com.powernode.pay.constants;

/**
 * @Author: MingYun
 * @Date: 2023-03-07 10:20
 */
public interface PayConstants {
    //已支付
    String PAY_STATE_PAYED="payed";
    //已退费
    String PAY_STATE_REFUND="refunded";

    //已预存
    String PAY_STORE_STATE_PAY="pay";

    //已退还
    String PAY_STORE_STATE_REFUND="refund";

    //前缀-付款流水号
    String PAY_PREFIX_PAY="pay.prefix.pay";

    //账户没金额
    String N = "N";

    //账户有金额
    String Y="Y";

}
