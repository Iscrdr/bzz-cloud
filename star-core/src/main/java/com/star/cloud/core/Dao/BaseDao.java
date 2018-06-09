package com.star.cloud.core.dao;

import org.springframework.stereotype.Repository;

@Repository
public abstract class BaseDao<T> extends JdbcTemplateDao{

}
