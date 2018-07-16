package com.bzz.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @CLASS_NAME: $
 * @PACKAGE_NAME: com.bzz.cloud$
 * @Author : yang qian li
 * @Date: 2017-12-10 1:18
 * @Modified by:
 * @Date:
 * @Description:
 */
@SpringBootApplication
@EnableEurekaServer
@EnableDiscoveryClient
public class BzzEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(BzzEurekaApplication.class, args);
    }
}
