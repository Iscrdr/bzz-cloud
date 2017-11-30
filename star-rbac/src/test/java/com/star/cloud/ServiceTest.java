package com.star.cloud;

import com.star.cloud.rbac.entity.SysUser;
import com.star.cloud.rbac.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


/**
 * @PACKAGE_NAME: com.star.cloud.test
 * @CLASS_NAME: ServiceTest
 * @Description:
 * @Author : yang qianli
 * @Date: 2017-11-24 23:30
 * @Modified by:
 * @Date:
 */


@SpringBootTest

@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceTest {

    private static final Logger log = LoggerFactory.getLogger(ServiceTest.class);

    @Resource
    private SysUserService sysUserService;

    @Test
    public void testGetUser(){

        SysUser sysUser = new SysUser();
        sysUser.setId("8888");
        sysUser.setLoginName("admin");
        sysUser.setEmail("624003618@qq.com");
        sysUser.setNickName("系统管理员");
        sysUser.setMobile("15501236689");
        sysUser.setPassword("admin");
        sysUser.setName("管理员");
        sysUser.setCreateDate(new Date());
        sysUser.setUpdateDate(new Date());
        sysUser.setCreateUser(sysUser);
        sysUser.setUpdateUser(sysUser);
        sysUser.setDelFlag(0);

        sysUserService.save(sysUser);

    }



}
