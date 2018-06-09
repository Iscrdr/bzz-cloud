package com.star.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class EntityBase  {
	/*
	 *供子类继承，sqlMap说明，
	 * key: field name
	 * value: Map<String,Object []>:
	 *          key为条件范围，例如：=，>,<,>=,<=,or,between...and like ,
	 *          value:object[]
	 */
	protected Map<String,Map<String,Object []>> sqlMap = new HashMap<String,Map<String,Object []>>();
}
