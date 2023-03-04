package com.powernode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author powernode
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class PmsApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(PmsApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  智慧物业启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
