package com.star.cloud.core.util;

import java.util.ArrayList;

public class DynamicSqlCondition {
	
	
	private String templet = " AND %s %s ?";
	private ArrayList<SqlOperator> sqlOperators = new ArrayList<SqlOperator>();
	
	public DynamicSqlCondition() {
	
	}
	
	public void addSqlOperator(SqlOperator sqlOperator) {
		sqlOperators.add(sqlOperator);
	}
	
	public String generateSql(String sql) {
		StringBuffer buffer = new StringBuffer(sql);
		for (SqlOperator sqlo : sqlOperators) {
			buffer.append(String.format(templet, sqlo.getSqlField(), sqlo.getOperator()));
		}
		return buffer.toString();
	}
	
}
