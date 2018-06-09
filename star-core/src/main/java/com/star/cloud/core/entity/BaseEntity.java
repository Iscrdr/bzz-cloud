package com.star.cloud.core.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class BaseEntity<T> implements Serializable {
	
	private static final long serialVersionUID = 5825343428711364453L;
	/**
	 *供子类继承，sqlMap说明，
	 * key: field name
	 * value: Map<String,Object []>:
	 *          key为条件范围，例如：=，>,<,>=,<=,or,between...and like ,
	 *          value:object[]
	 */
	protected Map<String,Map<String,Object []>> sqlMap = new HashMap<String,Map<String,Object []>>();
	
	/**
	 * 实体编号（唯一标识）
	 */
	protected String id;
}
