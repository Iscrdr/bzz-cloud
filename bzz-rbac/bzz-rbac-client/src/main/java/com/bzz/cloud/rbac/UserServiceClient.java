package com.bzz.cloud.rbac;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class UserServiceClient {
	
	@Autowired
	private  UserService userService;
	
	@RequestMapping("/getUser")
	public Object getUser() {
		System.out.println("================================");
		return userService.getUser();
	}
	
}
