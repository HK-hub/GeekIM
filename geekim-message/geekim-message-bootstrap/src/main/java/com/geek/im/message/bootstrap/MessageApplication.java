package com.geek.im.message.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author : HK意境
 * @ClassName : MessageApplication
 * @date : 2024/3/10 19:54
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.geek.im")
public class MessageApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MessageApplication.class, args);
    }

}
