package com.star.cloud.core.entity;

import com.star.common.Utils.IdUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class UserBase extends DataEntity<UserBase> {
	
	
	@Override
	public void preInsert() {
		// 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
		if (!this.isNewRecord){
			if(StringUtils.isBlank(this.id)){
				setId(IdUtils.uuid());
			}
		}
		this.updateDate = new Date();
		this.createDate = this.updateDate;
	}
	
	@Override
	public void preUpdate() {
		this.updateDate = new Date();
	}
}
