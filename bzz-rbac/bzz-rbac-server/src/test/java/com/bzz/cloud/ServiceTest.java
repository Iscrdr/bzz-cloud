package com.bzz.cloud;

import com.bzz.cloud.rbac.entity.SysUser;
import com.bzz.cloud.rbac.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
	
	@Autowired
	private SysUserService sysUserService;
	
	@Test
	public void getUser() throws Exception {
		SysUser sysUser = new SysUser();
		sysUser.setId("6bf10a72b57843e0a7cb4835fc6f6647");
		
		Long startTime = System.currentTimeMillis();
		SysUser sysUser1 = sysUserService.getUser(sysUser);
		Long endTime = System.currentTimeMillis();
		System.out.println("===================================================");
		
		System.out.println("用户ID："+sysUser1.getId()+"，耗时: "+ (endTime-startTime));
		System.out.println("===================================================");
	}

}
