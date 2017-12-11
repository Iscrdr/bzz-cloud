package com.star.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @CLASS_NAME: $
 * @PACKAGE_NAME: com.star.cloud$
 * @Author : yang qian li
 * @Date: 2017-12-10 1:18
 * @Modified by:
 * @Date:
 * @Description:
 */
@SpringBootApplication
@EnableEurekaServer
public class StarEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(StarEurekaApplication.class, args);
    }
}
