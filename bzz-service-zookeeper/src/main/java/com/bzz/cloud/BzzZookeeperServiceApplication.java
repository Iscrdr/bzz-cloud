package com.bzz.cloud;

/*
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
*/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*@Configuration
@RestController*/
@SpringBootApplication
//@EnableDiscoveryClient
public class BzzZookeeperServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BzzZookeeperServiceApplication.class, args);
	}
}
