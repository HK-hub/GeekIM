package com.geek.im.security.content.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : HK意境
 * @ClassName : ContentSecurityApplication
 * @date : 2024/2/23 11:37
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@SpringBootApplication(scanBasePackages = "com.geek.im")
public class ContentSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentSecurityApplication.class, args);
    }

}
