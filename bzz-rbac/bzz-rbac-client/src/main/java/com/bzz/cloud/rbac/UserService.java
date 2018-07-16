package com.bzz.cloud.rbac;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "bzz-server-zookeeper")
public interface UserService {
	
	@GetMapping(value = "/getUser")
	Object getUser();
}
