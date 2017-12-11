package com.star.cloud.rbac.web;

import com.star.cloud.rbac.entity.SysUser;
import com.star.cloud.rbac.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @PACKAGE_NAME: com.star.cloud.rbac.web
 * @CLASS_NAME: UserController
 * @Author : yang qianli 
 * @Date: 2017-12-05 22:38
 * @Modified by: 
 * @Date:
 * @Description:
 */
@Controller
public class UserController {

    @Resource
    private SysUserService sysUserService;

    @GetMapping(value = "/user")
    public List<SysUser> getUser(){
        List<SysUser> list = sysUserService.findList(null);
        return list;
    }
    @RequestMapping(value = "/index")
    public String index(){
        System.out.println("这是首页1========================");
        return "rbac/index";
    }
    
}
