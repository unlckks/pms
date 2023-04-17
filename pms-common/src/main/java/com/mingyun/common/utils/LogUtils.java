package com.mingyun.common.utils;

/**
 * 处理并记录日志文件
 * 
 * @author mingyun
 */
public class LogUtils
{
    public static String getBlock(Object msg)
    {
        if (msg == null)
        {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }
}
