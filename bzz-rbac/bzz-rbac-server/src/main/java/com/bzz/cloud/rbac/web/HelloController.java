package com.bzz.cloud.rbac.web;

import com.bzz.cloud.rbac.entity.SysUser;
import com.bzz.cloud.rbac.service.SysUserService;
import com.bzz.common.Utils.IdUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class HelloController  {
	
	@Resource
	private SysUserService sysUserService;
	
	@GetMapping("/getUser")
	@ResponseBody
	public SysUser getUser(){
		
		SysUser sysUser = new SysUser();
		sysUser.setId("6bf10a72b57843e0a7cb4835fc6f6647");
		
		Long startTime = System.currentTimeMillis();
		SysUser sysUser1 = sysUserService.getUser(sysUser);
		Long endTime = System.currentTimeMillis();
		System.out.println("===================================================");
		
		System.out.println("用户ID："+sysUser1.getId()+"，耗时: "+ (endTime-startTime));
		System.out.println("===================================================");
		
		return sysUser;
	}
	
	@GetMapping("/addUser")
	public SysUser saveUser(){
		
		SysUser sysUser = new SysUser();
		sysUser.setId(IdUtils.uuid());
		sysUser.setEmail("624003618@qq.com");
		sysUser.setLoginFlag("Yes");
		sysUser.setMobile("15501236689");
		sysUser.setName("杨千里");
		sysUser.setLoginName("qianli");
		sysUser.setPassword("admin");
		sysUser.setPhone("15501236689");
		sysUser.setUserType("0");
		sysUser.setWorkNum("1001");
		Long startTime = System.currentTimeMillis();
		sysUserService.save(sysUser);
		Long endTime = System.currentTimeMillis();
		System.out.println("===================================================");
		System.out.println("保存用户："+sysUser.getId()+"成功，耗时: "+ (endTime-startTime));
		System.out.println("===================================================");
		return sysUser;
	}
	@GetMapping("/editUser")
	public SysUser editUser(){
		SysUser sysUser = new SysUser();
		sysUser.setId("a2cfe6b800fa4fc4b0b17fc4215dbd24");
		sysUser.setLoginName("qianli8811");
		Long startTime = System.currentTimeMillis();
		sysUserService.update(sysUser);
		Long endTime = System.currentTimeMillis();
		System.out.println("===================================================");
		System.out.println("修改用户："+sysUser.getId()+"成功，耗时: "+ (endTime-startTime));
		System.out.println("===================================================");
		return sysUser;
	}
}
