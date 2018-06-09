package com.star.cloud.core.service;

import com.star.cloud.core.dao.JdbcTemplateDao;
import com.star.cloud.framework.annotations.DataBaseSourceTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@DataBaseSourceTarget(value = "dataSourceB")
@Transactional(rollbackFor = {java.lang.Exception.class, java.lang.RuntimeException.class })
public class BaseService {
	
	@Autowired
	JdbcTemplateDao jdbcTemplateDao;
	
	@DataBaseSourceTarget(value = "dataSourceA")
	public int insertA(String sql){
		return  jdbcTemplateDao.update(sql);
	}
	@DataBaseSourceTarget(value = "dataSourceB")
	public int insertB(String sql){
		return  jdbcTemplateDao.update(sql);
	}
}
