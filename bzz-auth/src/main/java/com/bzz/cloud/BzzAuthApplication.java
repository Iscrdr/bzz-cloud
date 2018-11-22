package com.bzz.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.sql.DataSource;


@SpringBootApplication
public class BzzAuthApplication {
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(BzzAuthApplication.class, args);
	}
}
