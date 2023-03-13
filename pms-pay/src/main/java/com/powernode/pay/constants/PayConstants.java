package com.powernode.pay.constants;

/**
 * @Author: MingYun
 * @Date: 2023-03-07 10:20
 */
public interface PayConstants {



    //未支付
    String PAY_STATE_NO_PAY="nopay";


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

    //合同类型
    String CONTRACT_TYPE_RENT = "rented";

    //合同类型
    String CONTRACT_TYPE_SALE = "sale";

    //前缀-合同流水号
    String PAY_PREFIX_HT="pay.prefix.ht";

    //前缀-订单流水号
    String PAY_PREFIX_ORDER="pay.prefix.order";

    //前缀-支付订单流水号
    String PAY_PREFIX_TRADE_ORDER="pay.prefix.trade";

    //合同状态
    String PAY_CONTRACT_STATE_1 = "1";//正常
    String PAY_CONTRACT_STATE_2 = "2";//撤销
    String PAY_CONTRACT_STATE_3 = "3";//结束

    //商铺状态
    String ASSET_HOUSE_STATE_RENTED="rented";
    String ASSET_HOUSE_STATE_SALE="sale";
    String ASSET_HOUSE_STATE_EMPTY="empty";

    /**
     *按户数收费
     * 按人口数收费
     * 按楼层收费
     * 按占地面积收费
     * 按使用面积收费
     * 按走表数量
     * 按租金收费
     */
    String FEE_ITEM_NUM_TYPE_FLAT="flat";
    String FEE_ITEM_NUM_TYPE_PEOPLE="people";
    String FEE_ITEM_NUM_TYPE_FLOOR="floor";
    String FEE_ITEM_NUM_TYPE_BUILDING_AREA="building_area";
    String FEE_ITEM_NUM_TYPE_USER_AREA="use_area";
    String FEE_ITEM_NUM_TYPE_METER="meter";
    String FEE_ITEM_NUM_TYPE_RENT_FEE="rent_fee";


    /**
     * 支付方式
     * cash
     * weixi
     * aliPay
     * cardPay
     * transferPay
     * miniapp_weixin
     */
    String PAY_TYPE_CASH="cash";
    String PAY_TYPE_ALIPAY="aliPay";

    //支付状态: 0-订单生成, 1-支付中, 2-支付成功, 3-支付失败, 4-已撤销, 5-已退款, 6-订单关闭
    String PAY_ORDER_STATE_0 = "0";
    String PAY_ORDER_STATE_1 = "1";
    String PAY_ORDER_STATE_2 = "2";
    String PAY_ORDER_STATE_3 = "3";
    String PAY_ORDER_STATE_4 = "4";
    String PAY_ORDER_STATE_5 = "5";
    String PAY_ORDER_STATE_6 = "6";
}
