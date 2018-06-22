package com.star.cloud.core.entity;


import java.util.Date;

public abstract class DataEntity<T> extends BaseEntity<T> {
	
	private static final long serialVersionUID = -6539941424995082924L;
	
	protected Date createDate; //创建时间
	protected Date updateDate; //修改时间
	
	protected Object createUser; //创建人
	protected Object updateUser; //修改人
	
	protected String remarks;	// 备注
	protected String delFlag; //作废标记(逻辑删除)，0正常，1删除、作废
	
	
	
}
