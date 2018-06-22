package com.star.cloud.rbac.service;

import com.star.cloud.core.service.BaseService;
import com.star.cloud.rbac.entity.SysUser;
import com.star.cloud.rbac.dao.SysUserDao;
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
	
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	
	@Autowired
	private SysUserDao userDao;
	

	//@Autowired
	//private IdentityService identityService;

	//-- User Service --//
	
	
	/**
	 * 获取用户
	 * @param
	 * @return
	 */
	public SysUser getUser(SysUser sysUser) {
		return userDao.get(sysUser);
	}

	/**
	 * 根据登录名获取用户
	 * @param
	 * @return
	 */
	public SysUser getUserByLoginName(SysUser sysUser) {
		return userDao.getByLoginName(sysUser);
	}
	
	
}
