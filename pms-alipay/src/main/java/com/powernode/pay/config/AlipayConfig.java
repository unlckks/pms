package com.powernode.pay.config;


public class AlipayConfig {
    //应用的ID，申请时的APPID
    public static String app_id = "2021000122623520";
    //SHA256withRsa对应支付宝公钥
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArhllYG1iVISdnuS4BIhPSNf5kPPLQe8JR/1uAxA3/4EkHlbWTuvKnI/NnxbnStPUDkx8O9cxp0Wyx+hZu3LyKfbagtSl+QVuKEoJPoHxJPDJivv0kt38ODYHavnf+izElUlyo057vY4EClLyTYnlymypk6No6wUg0LMy99Ie9f650RW2FkfaoVo2uRk40sGKIYe+OeG2MRsCeAfBxoFatrWRoaPM16qO2Otf8nqmmgg4RrVBNiKOcDQFa1+AgBEMu1yZTRMKE29B9wXIq+g2r3J6XX0BPKnKRdwND9VOSswyQUn9CB0ViDFsLHko+e4wYUAE5KuIcLTueiP6q2J5FwIDAQAB";
    //签名方式
    public static String sign_type = "RSA2";
    //字码编码格式
    public static String charset = "utf-8";

}