package com.star.cloud.rbac.entity;

import com.star.cloud.core.entity.po.TreeIdPidPo;
import lombok.*;

/**
 * @CLASS_NAME: $
 * @PACKAGE_NAME: com.star.cloud.rbac.entity$
 * @Author : yang qian li
 * @Date: 2017-12-09 19:02
 * @Modified by:
 * @Date:
 * @Description:
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SysArea extends TreeIdPidPo<SysArea> {


    private static final long serialVersionUID = -3236461701485283165L;

    private String code; 	// Area code

    private String type; 	// Area type （1：country；2：province；3：city；4：district）






}
