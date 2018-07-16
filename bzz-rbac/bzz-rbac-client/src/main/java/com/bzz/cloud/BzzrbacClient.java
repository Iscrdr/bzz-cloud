package com.bzz.cloud;


import com.bzz.cloud.ribbon.config.RbacRibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient(autoRegister=false)
@EnableFeignClients
@RibbonClients(value = {
		@RibbonClient(name = "rbacRibbonConfig",configuration = RbacRibbonConfig.class)
})
public class BzzrbacClient {
	public static void main(String[] args) {
		SpringApplication.run(BzzrbacClient.class, args);
	}
}
