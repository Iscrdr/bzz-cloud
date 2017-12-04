
package com.star.cloud.core.dao;

import java.util.List;

/**
 * DAO支持类实现
 * @author yang-ql
 * @version 2014-05-16
 * @param <T>
 */
public interface CrudDao<T> extends BaseDao {

	/*
	 * get a record by entity's id
	 * @param id
	 * @return
	 */
	public T get(String id);
	
	/*
	 * get a record by entity
	 * @param entity
	 * @return
	 */
	public T get(T entity);
	
	/* get list or pageList by entity
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity);
	
	/**
	 * get all record by entity
	 * @param entity
	 * @return
	 */
	public List<T> findAllList(T entity);
	
	/**
	 * get all record
	 * @see public List<T> findAllList(T entity)
	 * @return
	 */
	@Deprecated
	public List<T> findAllList();
	
	/**
	 * insert into table
	 * @param entity
	 * @return
	 */
	public int insert(T entity);
	
	/**
	 * update
	 * @param entity
	 * @return
	 */
	public int update(T entity);
	
	/**
	 * delete a record : set del_flag = 0
	 * @param id
	 * @see public int delete(T entity)
	 * @return
	 */
	@Deprecated
	public int delete(String id);
	
	/*
	 * delete records : set del_flag = 0
	 * @param entity
	 * @return
	 */
	public int delete(T entity);

	/*
	 * batch insert
	 * @param entity
	 * @return
	 */
	public int batchSave(List<T> list);

	/*
	 * batch update
	 * @param entity
	 * @return
	 */
	public int batchUpdate(List<T> list);
	
}