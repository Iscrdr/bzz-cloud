package com.star.cloud.rbac.web;

import com.star.cloud.rbac.entity.SysUser;
import com.star.cloud.rbac.service.SysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController  {
	
	@Resource
	private SysUserService sysUserService;
	
	@GetMapping("/getUser")
	@ResponseBody
	public SysUser getUser(){
		
		SysUser sysUser = new SysUser();
		sysUser.setId("1e8149b33e774daa9a250f5a1a0ad23f");
		
		Long startTime = System.currentTimeMillis();
		SysUser sysUser1 = sysUserService.getUser(sysUser);
		Long endTime = System.currentTimeMillis();
		System.out.println("===================================================");
		
		System.out.println("用户ID："+sysUser1.getId()+"，耗时: "+ (endTime-startTime));
		System.out.println("===================================================");
		
		return sysUser;
	}
}
