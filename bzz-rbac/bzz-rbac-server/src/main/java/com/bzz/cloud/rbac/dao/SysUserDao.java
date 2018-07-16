
package com.bzz.cloud.rbac.dao;

import java.util.List;

import com.bzz.cloud.core.dao.CrudDao;
import com.bzz.cloud.rbac.entity.SysUser;
import org.springframework.stereotype.Repository;


/**
 * 用户DAO接口
 * @author yang-ql
 * @version 2014-05-16
 */
@Repository
public interface SysUserDao extends CrudDao<SysUser> {
	
	/**
	 * 根据登录名称查询用户
	 * @param
	 * @return
	 */
	public SysUser getByLoginName(SysUser user);

	/**
	 * 通过OfficeId获取用户列表，仅返回用户id和name（树查询用户时用）
	 * @param user
	 * @return
	 */
	public List<SysUser> findUserByOfficeId(SysUser user);
	
	/**
	 * 查询全部用户数目
	 * @return
	 */
	public long findAllCount(SysUser user);
	
	/**
	 * 更新用户密码
	 * @param user
	 * @return
	 */
	public int updatePasswordById(SysUser user);
	
	/**
	 * 更新登录信息，如：登录IP、登录时间
	 * @param user
	 * @return
	 */
	public int updateLoginInfo(SysUser user);

	/**
	 * 删除用户角色关联数据
	 * @param user
	 * @return
	 */
	public int deleteUserRole(SysUser user);
	
	/**
	 * 插入用户角色关联数据
	 * @param user
	 * @return
	 */
	public int insertUserRole(SysUser user);
	
	/**
	 * 根据用户id,角色id查询校验位
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public String findCheckFlag(String userId, String roleId);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int updateUserInfo(SysUser user);

}
