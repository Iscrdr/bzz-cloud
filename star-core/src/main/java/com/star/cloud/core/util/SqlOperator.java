package com.star.cloud.core.util;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SqlOperator {
	private String sqlField; //字段
	private Object[] value;   //值
	private String operator; //操作：=、<、>、<=、>=、<>、!=、like、or、between...and
}
