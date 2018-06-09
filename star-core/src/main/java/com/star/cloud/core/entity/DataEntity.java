package com.star.cloud.core.entity;

import java.util.Date;

public class DataEntity<T> extends BaseEntity<T> {
	
	private static final long serialVersionUID = -6539941424995082924L;
	
	protected Date createDate; //创建时间
	protected Date updateDate; //修改时间
	
	protected T createUser; //创建人
	protected T updateUser; //修改人
	
	protected Integer delFlag; //作废标记(逻辑删除)，0正常，1删除、作废
}
