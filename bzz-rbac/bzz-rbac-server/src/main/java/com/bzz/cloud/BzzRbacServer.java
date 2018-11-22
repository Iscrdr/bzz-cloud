package com.bzz.cloud;

import com.bzz.cloud.framework.config.BzzCloudDbConfig;
import com.netflix.config.sources.URLConfigurationSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @PACKAGE_NAME: com.bzz.cloud
 * @CLASS_NAME: Appbzz.ore
 * @Author : yang qianli 
 * @Date: 2017-12-06 0:02
 * @Modified by:
 * @Date:
 * @Description:
 */
@Import({BzzCloudDbConfig.class})
@SpringBootApplication
@ComponentScans({
        @ComponentScan("com.bzz.cloud.core.*"),
        @ComponentScan("com.bzz.cloud.framework.*")
})
@EnableAspectJAutoProxy
@Configuration
@EnableDiscoveryClient
public class BzzRbacServer {
    public static void main(String[] args) {
        URLConfigurationSource  url = new URLConfigurationSource();
        SpringApplication.run(BzzRbacServer.class, args);
    }
}
