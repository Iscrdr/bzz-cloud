
package com.star.cloud.core.service;

import java.util.List;

import com.star.cloud.core.dao.TreeDao;
import com.star.cloud.core.entity.TreeEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;



/**
 * Service基类
 * @author yang-ql
 * @version 2014-05-16
 */
@Transactional(readOnly = true)
public abstract class TreeService<D extends TreeDao<T>, T extends TreeEntity<T>> extends CrudService<D, T> {
	
	@Transactional(readOnly = false)
	public void save(T entity) {
		// 保存或更新实体
		super.save(entity);
	}
	/**
	 * 预留接口，用户更新子节前调用
	 * @param childEntity
	 */
	protected void preUpdateChild(T entity, T childEntity) {
	}

}
