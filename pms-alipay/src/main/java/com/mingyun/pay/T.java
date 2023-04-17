package com.mingyun.pay;

import java.util.Map;

/**
 * @author: mingyun
 * 时间: 2023-03-13 09:33
 */
public class T {
    public static void main(String[] args) {
        String outTradeNo= "PAY12131231443232888881";
        String subject="智慧物业系统支付平台";
        String totalAmount="100";
        String undiscountableAmount=null;
        String body="买房用的";
        String notifyUrl="http://rfjs2j.natappfree.cc/callback/";
        Map<String, Object> map = PayService.pay(outTradeNo, subject, totalAmount, undiscountableAmount, body, notifyUrl);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey()+"="+entry.getValue());
        }
    }
}
