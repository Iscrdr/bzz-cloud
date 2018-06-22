package com.star.cloud;

import com.star.cloud.framework.config.StarCloudDbConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
@ComponentScans({
        @ComponentScan("com.star.cloud.core.*"),
        @ComponentScan("com.star.cloud.framework.*")
})
@EnableAspectJAutoProxy
@Import({StarCloudDbConfig.class})
@EnableTransactionManagement
@Configuration
public class AppStarCore {
    public static void main(String[] args) {
        SpringApplication.run(AppStarCore.class, args);
    }
}
