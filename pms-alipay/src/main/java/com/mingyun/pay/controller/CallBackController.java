package com.mingyun.pay.controller;


import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mingyun.pay.config.AlipayConfig;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: MingYun
 * @Date: 2023-03-13 11:34
 */
@RestController
public class CallBackController {


    private ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 当方法给支付宝就回调
     */
    @PostMapping("callBack/{orderId}")
    public void  callBack(@PathVariable String orderId , HttpServletRequest request ){
        Map<String, String> parameterMap = this.getParameterMap(request);
        String trade_status = parameterMap.get("trade_status");
        //验证是否为支付宝的调用
        try {
            boolean singVerified = AlipaySignature.rsaCheckV1(parameterMap, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
            System.out.println(singVerified);//只有支付宝调用我们系统的接口才能去更新系统订单状态
            System.out.println("验证签名结果:"+singVerified);
            if(trade_status.equals("TRADE_SUCCESS")){
                //根据orderId去更新我们自己系统的订单状态

            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            System.out.println("验证签名出现异常");
        }


    }

    private Map<String, String> getParameterMap(HttpServletRequest request) {
        HashMap<String, String> map = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String paramName =parameterNames.nextElement();
            String[] paraValues =request.getParameterValues(paramName);
            if (paraValues.length == 1 ){
                String paramValue =paraValues[0];
                if (paramValue.length()!= 0){
                    map.put(paramName,paramValue);
                }
            }
        }

        return map;
    }

}
