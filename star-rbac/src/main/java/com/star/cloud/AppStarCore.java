package com.star.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @PACKAGE_NAME: com.star.cloud
 * @CLASS_NAME: AppStarCore
 * @Author : yang qianli 
 * @Date: 2017-12-06 0:02
 * @Modified by:
 * @Date:
 * @Description:
 */
@SpringBootApplication
@EnableConfigurationProperties
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.star.cloud"})
@Configuration

public class AppStarCore {
    public static void main(String[] args) {
        SpringApplication.run(AppStarCore.class, args);
    }
}
