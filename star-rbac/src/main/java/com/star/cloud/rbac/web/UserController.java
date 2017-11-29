package com.star.cloud.rbac.web;

import com.star.cloud.rbac.entity.SysUser;
import com.star.cloud.rbac.service.SysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * class_name: $
 * package: com.star.cloud.core.web$
 * desc: TODO
 * author : yang qian li
 * creat_time: 2017-11-19 22:09
 */
@RestController
public class UserController {

    @Resource
    private SysUserService sysUserService;

    @GetMapping(value = "/user")
    public List<SysUser> getUser(){
        List<SysUser> list = sysUserService.findList(null);
        return list;
    }

    
}
