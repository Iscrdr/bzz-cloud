package com.bzz.cloud.framework.dynamicdatasource;

public class DataSourceContextHolder {
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	
	public static void setDataSource(String dataSource) {
		contextHolder.remove();
		contextHolder.set(dataSource);
	}
	
	public static String getDataSource() {
		return contextHolder.get();
	}
	
	public static void removeDataSource() {
		contextHolder.remove();
	}
	
}