package com.star.cloud.core.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public class JdbcTemplateDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * spring提供JdbcDaoSupport工具
	 * Object... args：args是个数组，此参数可传也可不传
	 */
	
	public int update(String sql){
		return jdbcTemplate.update(sql);
	}
	
	/**
	 * update t_sys_user set name = ?, age = ? where id = ?;
	 */
	public int update(String sql, Object... args){
		return jdbcTemplate.update(sql, args);
	}
	
	
	/**
	 * update t_sys_user set name = ?, age = ?
	 * argTypes：指定args参数的类型，例如： Types.DATE, Types.VARCHAR
	 * @param sql
	 * @param args
	 * @param argTypes
	 */
	public int update(String sql, Object[] args, int[] argTypes){
		return jdbcTemplate.update(sql, args, argTypes);
	}
	
	
	/**
	 * 批量处理
	 * update t_sys_user set name = ?, age = ? where id = ?;
	 * @param sql
	 */
	public int[] batchUpdate(String sql){
		return jdbcTemplate.batchUpdate(sql);
	}
	
	
	/**
	 * insert into t_sys_user (id, name, sex, age) values (?, ?, ?, ?)
	 * @param sql
	 * @param batchArgs
	 */
	public int[] batchUpdate(String sql, List<Object[]> batchArgs){
		return jdbcTemplate.batchUpdate(sql, batchArgs);
	}
	
	
	/**
	 * insert into t_sys_user (id, name, sex, age) values (?, ?, ?, ?)
	 * argTypes：指定batchArgs参数的类型，例如： Types.DATE, Types.VARCHAR
	 * @param sql
	 * @param batchArgs
	 * @param argTypes
	 */
	public int[] batchUpdate(String sql, List<Object[]> batchArgs, int[] argTypes){
		return jdbcTemplate.batchUpdate(sql, batchArgs, argTypes);
	}
	
	
	
	/**
	 * 返回list集合，list里存放的是实体对象，不是map集合
	 *  select * from t_sys_user where name = ""
	 * @param sql
	 * @param elementType
	 * @param <T>
	 * @return
	 */
	public <T> List<T> queryForList(String sql, Class<T> elementType){
		return jdbcTemplate.queryForList(sql, elementType);
	}
	
	
	/**
	 * select * from t_sys_user where name = ?;
	 * @param sql
	 * @param args
	 * @param elementType
	 * @param <T>
	 * @return
	 */
	public <T> List<T> queryForList(String sql, Object[] args, Class<T> elementType){
		return jdbcTemplate.queryForList(sql, args, elementType);
	}
	
	
	/**
	 * select * from t_sys_user where name = ?;
	 * @param sql
	 * @param elementType
	 * @param args
	 * @param <T>
	 * @return
	 */
	public <T> List<T> queryForList(String sql, Class<T> elementType, Object... args){
		return jdbcTemplate.queryForList(sql, elementType, args);
	}
	
 
	
	/**
	 * select * from t_sys_user where name = ?;
	 * argTypes：指定args参数的类型，例如： Types.DATE, Types.VARCHAR
	 * @param sql
	 * @param args
	 * @param argTypes
	 * @param elementType
	 * @param <T>
	 * @return
	 */
	public <T> List<T> queryForList(String sql, Object[] args, int[] argTypes, Class<T> elementType){
		return jdbcTemplate.queryForList(sql, args, argTypes, elementType);
	}
	
	
	
	/**
	 * 返回list集合，list里存放的是map集合，不是实体对象
	 * select * from t_sys_user where name = ""
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> queryForList(String sql){
		return jdbcTemplate.queryForList(sql);
	}
	
	
	/**
	 * select * from t_sys_user where name = ?
	 * @param sql
	 * @param args
	 * @return
	 */
	public List<Map<String, Object>> queryForList(String sql, Object... args){
		return jdbcTemplate.queryForList(sql, args);
	}
	
	
	/**
	 * select * from t_sys_user where name = ?
	 * argTypes：指定args参数的类型，例如： Types.DATE, Types.VARCHAR
	 * @param sql
	 * @param args
	 * @param argTypes
	 * @return
	 */
	public List<Map<String, Object>> queryForList(String sql, Object[] args, int[] argTypes){
		return jdbcTemplate.queryForList(sql, args, argTypes);
	}
	
	
	/**
	 * select * from t_sys_user where id = "";
	 * 返回map集合，不是实体对象
	 * @param sql
	 * @return
	 */
	public Map<String, Object> queryMap(String sql){
		return jdbcTemplate.queryForMap(sql);
	}
	
	
	/**
	 * select * from t_sys_user where id = ?;
	 * @param sql
	 * @param args
	 * @return
	 */
	public Map<String, Object> queryForMap(String sql, Object... args){
		return jdbcTemplate.queryForMap(sql, args);
	}
	

	
	/**
	 * 	select * from t_sys_user where id = ?;
	 *  argTypes：指定传入参数的类型，例如： Types.DATE, Types.VARCHAR
	 * @param sql
	 * @param args
	 * @param argTypes
	 * @return
	 */
	public Map<String, Object> queryMap(String sql, Object[] args, int[] argTypes){
		return jdbcTemplate.queryForMap(sql, args, argTypes);
	}
	
	
	
	/**
	 * 返回基本数据类型，如：String、Integer、Long
	 * select count(id) from t_sys_user sex = ''
	 * @param sql
	 * @param requiredType
	 * @param <T>
	 * @return
	 */
	public <T> T queryObject(String sql, Class<T> requiredType){
		return jdbcTemplate.queryForObject(sql, requiredType);
	}
	
	
	/**
	 * select count(id) from t_sys_user sex = ?
	 * @param sql
	 * @param requiredType
	 * @param args
	 * @param <T>
	 * @return
	 */
	public <T> T queryForObject(String sql, Class<T> requiredType, Object... args){
		return jdbcTemplate.queryForObject(sql, requiredType, args);
	}
	
	
	/**
	 * select count(id) from t_sys_user sex = ?
	 * @param sql
	 * @param args
	 * @param requiredType
	 * @param <T>
	 * @return
	 */
	public <T> T queryObject(String sql, Object[] args, Class<T> requiredType){
		return jdbcTemplate.queryForObject(sql, args, requiredType);
	}
	

	
	/**
	 * select count(id) from t_sys_user sex = ?
	 * argTypes：指定args参数的类型，例如： Types.DATE, Types.VARCHAR
	 * @param sql
	 * @param args
	 * @param argTypes
	 * @param requiredType
	 * @param <T>
	 * @return
	 */
	public <T> T queryObject(String sql, Object[] args, int[] argTypes, Class<T> requiredType){
		return jdbcTemplate.queryForObject(sql, args, argTypes, requiredType);
	}
	
	
	
	/**
	 * 返回实体对象,不是map集合
	   select * from t_sys_user where loginname = ''
	   RowMapper的实现类是：new BeanPropertyRowMapper<User>(User.class)
	 * @param sql
	 * @param rowMapper
	 * @param <T>
	 * @return
	 */
	public <T> T queryObject(String sql, RowMapper<T> rowMapper){
		return jdbcTemplate.queryForObject(sql, rowMapper);
	}
	
	
	/**
	 * select * from t_sys_user where loginname = ?
	 * @param sql
	 * @param args
	 * @param rowMapper
	 * @param <T>
	 * @return
	 */
	public <T> T queryObject(String sql, Object[] args, RowMapper<T> rowMapper){
		return jdbcTemplate.queryForObject(sql, args, rowMapper);
	}
	
	
	/**
	 * select * from t_sys_user where loginname = ?
	 * @param sql
	 * @param rowMapper
	 * @param args
	 * @param <T>
	 * @return
	 */
	public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args){
		return jdbcTemplate.queryForObject(sql, rowMapper, args);
	}
	
	
	
	/**
	 * select * from t_sys_user where loginname = ?
	 * argTypes：指定args参数的类型，例如： Types.DATE, Types.VARCHAR
	 * @param sql
	 * @param args
	 * @param argTypes
	 * @param rowMapper
	 * @param <T>
	 * @return
	 */
	public <T> T queryForObject(String sql, Object[] args, int[] argTypes, RowMapper<T> rowMapper){
		return jdbcTemplate.queryForObject(sql, args, argTypes, rowMapper);
	}
	
}
