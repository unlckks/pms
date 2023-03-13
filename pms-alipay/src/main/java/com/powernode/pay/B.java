package com.powernode.pay;

import java.util.Map;

/**
 * @Author: MingYun
 * @Date: 2023-03-13 14:39
 */
public class B {
    public static void main(String[] args) {
        String outTradeNo="PAY12131231443232888881";
        Map<String, Object> map = PayService.payBack(outTradeNo,null,"5","不想要了","true");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey()+":"+entry.getValue());
    }
    }
}
