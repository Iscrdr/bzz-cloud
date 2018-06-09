package com.star.common.annotation;


public enum SqlCondition {
	/*
	 * EQ ---> "="
	 * GT ---> ">"
	 * GTEQ ---> ">="
	 * LT ---> "<"
	 * LTEQ ---> "<="
	 * NOEQ ---> "!=" or "<>"
	 * LIKE ---> "LIKE"
	 * NOTLIKE ---> "NOT LIKE"
	 * ISNULL ---> "IS NULL"
	 * ISNOTNULL ---> "IS NOT NULL"
	 * IN ---> "IN"
	 * NOTIN ---> "NOT IN"
	 * EXISTS ---> "EXISTS"
	 * NOTEXISTS ---> "NOT EXISTS"
	 *
	 */
	EQ,GT,GTEQ,LT,LTEQ,NOEQ,LIKE,NOTLIKE,ISNULL,ISNOTNULL,IN,NOTIN,EXISTS,NOTEXISTS,NULL
}
