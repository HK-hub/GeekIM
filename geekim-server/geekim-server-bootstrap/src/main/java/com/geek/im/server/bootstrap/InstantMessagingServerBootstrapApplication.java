package com.geek.im.server.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author : HK意境
 * @ClassName : InstantMessagingServerBootstrapApplication
 * @date : 2024/4/27 14:24
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@EnableAsync
@SpringBootApplication(scanBasePackages = "com.geek.im")
public class InstantMessagingServerBootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstantMessagingServerBootstrapApplication.class, args);
    }
}
