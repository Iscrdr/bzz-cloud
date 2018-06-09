package com.star.common.annotation;



import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Documented
@Target({ElementType.METHOD,ElementType.FIELD})
public @interface BeanColumn {
	
	boolean isPk() default false;//是否是主键
	
	String name() default "";//字段名称
	
	boolean no2Field() default true;//该字段是否映射

	
	OrmEnum[] relation() default {};//关联关系
	
	@BeanColumn(relation = {OrmEnum.One2many,OrmEnum.Many2one,OrmEnum.One2one})
	String foreignKey() default "";//一对一,一对多，多对一必须指定外键名称
	
	@BeanColumn(relation = {OrmEnum.Many2Many})
	String threeTable() default ""; //如果是多对多，则指定第三方表名(第三方表的外键为：默认为两个关联表的主键)
	
	
}
