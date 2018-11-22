package com.bzz.cloud.rbac.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bzz.cloud.core.service.BaseService;
import com.bzz.cloud.framework.annotations.DataBaseSourceTarget;
import com.bzz.cloud.rbac.entity.SysUser;
import com.bzz.cloud.rbac.dao.SysUserDao;
import com.bzz.cloud.rbac.model.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 系统管理，安全相关实体的管理类,包括用户、角色、菜单.
 * @author yang-ql
 * @version 2013-12-05
 */

@Service
@Transactional(readOnly = true)
public class SysUserService extends BaseService  {
	
	
	
	@Autowired
	private SysUserDao userDao;
	
	
	@DataBaseSourceTarget(dataBaseDialect = "mysql",dataSourceValue = "dataSourceA")
	public Page<SysUser> selectPage(Page<SysUser> page){
		Page<SysUser> sysUserMyPage = userDao.selectPage(page);
		return sysUserMyPage;
	}
	
	@DataBaseSourceTarget(dataBaseDialect = "oracle",dataSourceValue = "dataSourceB")
	public Page<SysUser> selectPageOracle(Page<SysUser> page){
		Page<SysUser> sysUserMyPage = userDao.selectPage(page);
		return sysUserMyPage;
	}
}
