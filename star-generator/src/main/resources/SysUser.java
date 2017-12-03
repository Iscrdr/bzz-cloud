package com.star.cloud.rbac.entity;
import com.star.cloud.core.entity.po.CommonPo;
import lombok.*;



/**
 * @CLASS_NAME: SysUser
 * @PACKAGE_NAME: com.star.cloud.rbac.entity
 * @Author : Ay
 * @Date: 2017/05/03
 * @Modified by:
 * @Date:
 * @Description:
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SysUser extends CommonPo<SysUser> {


    /**
     *登录名
     */
    private String loginName;


    /**
     *密码
     */
    private String password;


    /**
     *姓名
     */
    private String name;


    /**
     *邮箱
     */
    private String email;


    /**
     *手机
     */
    private String mobile;




}