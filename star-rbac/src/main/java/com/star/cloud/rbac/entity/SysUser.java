package com.star.cloud.rbac.entity;

import com.star.cloud.core.entity.po.CommonPo;
import lombok.*;

/**
 * @CLASS_NAME: $
 * @PACKAGE_NAME: com.star.cloud.rbac.entity$
 * @Author : yang qian li
 * @Date: 2017-11-24 23:41
 * @Modified by:
 * @Date:
 * @Description:
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SysUser extends CommonPo<SysUser>{

        private String loginName;   //loginName
        private String password;  //password
        private String nickName; // nickName
        private String email; // email
        private String mobile;// mobile
        private String name; //real name


}
