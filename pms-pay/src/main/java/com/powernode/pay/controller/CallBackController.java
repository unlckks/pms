package com.powernode.pay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.powernode.pay.config.AlipayConfig;
import com.powernode.pay.service.IPayTradeOrderService;
import com.powernode.pay.service.impl.PayTradeOrderServiceImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 动力节点·武汉
 * 时间: 2023-03-13 11:00
 */
@RestController
public class CallBackController {

    private ObjectMapper objectMapper=new ObjectMapper();

    @Resource
    private IPayTradeOrderService payTradeOrderService;

    /**
     * 当前方法给支付宝进行回调
     */
    @PostMapping("callback/{orderId}")
    public void callback(@PathVariable String orderId, HttpServletRequest request){
        Map<String, String> parameterMap = this.getParameterMap(request);
        String trade_status = parameterMap.get("trade_status");
        //验证是否为支付宝的调用
        try {
            boolean singVerified = AlipaySignature.rsaCheckV1(parameterMap, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
            System.out.println(singVerified);//只有支付宝调用我们系统的接口才能去更新系统订单状态
            System.out.println("验证签名结果:"+singVerified);
            if(trade_status.equals("TRADE_SUCCESS")){
                //根据orderId去更新我们自己系统的订单状态
                //区分是退费成功还是支付成功
                String refundFee = parameterMap.get("refund_fee");
                if(null!=refundFee){
                    System.out.println("这时退费的回调");
                }else{
                    String trade_no = parameterMap.get("trade_no");
                    System.out.println("支付成功的回调");
                    payTradeOrderService.paySuccess(orderId,trade_no);
                }
            }else if(trade_status.equals("WAIT_BUYER_PAY")){
                System.out.println("交易创建，等待买家付款。--用户已扫码");
            }else if(trade_status.equals("TRADE_CLOSED")){
                System.out.println("未付款交易超时关闭，或支付完成后全额退款。");
            }else if(trade_status.equals("TRADE_FINISHED")){
                System.out.println("交易结束，不可退款。");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            System.out.println("验证签名出现异常");
        }
    }

    /**
     * 获取request中的参数集合转Map
     * Map<String,String> parameterMap = RequestUtil.getParameterMap(request)
     * @param request
     * @return
     */
    public Map<String, String> getParameterMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }
        return map;
    }
}
