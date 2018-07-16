package com.bzz.cloud.rbac.entity;

import com.bzz.cloud.core.entity.DataEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SysUser extends DataEntity {
	
	private String loginName;// 登录名
	private String password;// 密码
	private String workNum;	// 工号
	private String name;	// 姓名
	private String email;	// 邮箱
	private String phone;	// 电话
	private String mobile;	// 手机
	private String userType;// 用户类型

	private String loginFlag;	// 是否允许登陆
	private String photo;	// 头像
	
	private String oldLoginName;// 原登录名
	private String newPassword;	// 新密码
	
	
	
	
}
