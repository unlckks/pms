package com.powernode.common.utils.uuid;

import java.util.Random;

/**
 * ID生成器工具类
 * 
 * @author powernode
 */
public class IdUtils
{
    private static Random random=new Random();
    /**
     * 获取随机UUID
     * 
     * @return 随机UUID
     */
    public static String randomUUID()
    {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     * 
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID()
    {
        return UUID.randomUUID().toString(true);
    }

    /**
     * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
     * 
     * @return 随机UUID
     */
    public static String fastUUID()
    {
        return UUID.fastUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线，使用性能更好的ThreadLocalRandom生成UUID
     * 
     * @return 简化的UUID，去掉了横线
     */
    public static String fastSimpleUUID()
    {
        return UUID.fastUUID().toString(true);
    }
    /**
     *
     * 根据前缀+时间生成单号
     *  生成规则PRE+时间戳+4个random
     *
     */
    public static String createNoWithPrefix(String prefix){
        return prefix+System.currentTimeMillis()+(random.nextInt(9000)+1000);
    }
}
