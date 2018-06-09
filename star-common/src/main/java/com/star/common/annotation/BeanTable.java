package com.star.common.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface BeanTable {
	String name() default ""; //表名称
	String alias() default "";//别名
}
