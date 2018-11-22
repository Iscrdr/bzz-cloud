package com.bzz.common.jdbc.hikaricp.connect;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class HikariPoolManager {
	
	private static Logger logger = LogManager.getLogger(HikariPoolManager.class);
	
	/**
	 * 默认属性
	 */
	
	public static HikariDataSource getHikariDataSource(HikariConfig hikariConfig){
		return new  HikariDataSource(hikariConfig);
	}
	
	/**
	 * 取得数据库连接
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection(HikariDataSource hikariDataSource) throws Exception{
		
		Connection connection = null;
		try {
			connection = hikariDataSource.getConnection();
		} catch (Exception e) {
			logger.error("获取数据库连接时发生异常!"+ e);
			throw  e;
		}
		return connection;
	}
	
	/**
	 * 释放数据库连接
	 * @param connection
	 * @throws Exception
	 */
	public static void releaseConnection(Connection connection){
		if (connection != null){
			try {
				connection.close();
			}catch(Exception e){
				logger.error("释放数据库连接时发生异常!"+ e.getMessage());
			}
		}
	}
}
