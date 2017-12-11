package com.star.cloud.core.entity.po;

import lombok.Getter;
import lombok.Setter;

/**
 * @PACKAGE_NAME: com.star.cloud.core.entity.po
 * @CLASS_NAME: TreeIdPidPo
 * @Description: The tree data structure is constructed by means of id，parentId,parentIds
 * @Author : yang qianli
 * @Date: 2017-11-21 0:12
 * @Modified by:
 * @Date:
 */
@Getter
@Setter
public class TreeIdPidPo<T> extends CommonPo<T>{


    private static final long serialVersionUID = -1546529493562609397L;

    protected T parent;	// parent id
    protected String parentIds; // parent ids
    protected String name; 	// name
    protected Integer sort;		// sort



}
