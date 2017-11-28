package com.star.cloud.rbac.service;

import com.star.cloud.core.service.CrudService;
import com.star.cloud.rbac.entity.SysUser;
import com.star.cloud.rbac.dao.SysUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @PACKAGE_NAME: com.star.cloud.rbac.service
 * @CLASS_NAME: SysUserService
 * @Description:
 * @Author : yang qianli 
 * @Date: 2017-11-25 0:39
 * @Modified by: 
 * @Date:
 */
@Service
@Transactional
public class SysUserService extends CrudService<SysUserDao,SysUser> {





}
