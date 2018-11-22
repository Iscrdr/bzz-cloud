package com.bzz.cloud.rbac.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @program: bzz-cloud
 * @description: 分页
 * @author: Yang qianli
 * @create: 2018-10-15 23:28
 * @version: 1.0.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MyPage<T> extends Page<T> {
	private static final long serialVersionUID = 5194933845448697148L;
	
	private Integer selectInt;
	private String selectStr;
	
	public MyPage(long current, long size) {
		super(current, size);
	}
}
