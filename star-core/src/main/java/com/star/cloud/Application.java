package com.star.cloud;

import com.star.cloud.framework.config.StarCloudDbConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@ComponentScans({
		@ComponentScan("com.star.cloud.core.*"),
		@ComponentScan("com.star.cloud.framework.*")
})
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@Import({StarCloudDbConfig.class})
@EnableTransactionManagement
public class Application {
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
	}
}
