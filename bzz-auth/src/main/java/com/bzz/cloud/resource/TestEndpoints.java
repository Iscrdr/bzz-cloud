package com.bzz.cloud.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestEndpoints {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@GetMapping("/product/{id}")
	public String getProduct(@PathVariable String id) {
		//for debug
		/*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();*/
		
		logger.info("id："+id);
		
		return "product id : " + id;
	}
	
	@GetMapping("/order/{id}")
	public String getOrder(@PathVariable String id) {
		//for debug
		/*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();*/
		return "order id : " + id;
	}
	
	@GetMapping("/bzzLogin")
	public String getLogin(@PathVariable String loginName,@PathVariable String password) {
		logger.info("用户名："+loginName+"，密码："+password);
		
		
		
		return "{'loginName':"+loginName+",'password:'"+password+"}";
	}
}
