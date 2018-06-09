package com.star.common.entity;

import com.star.common.annotation.BeanColumn;
import com.star.common.annotation.BeanTable;
import com.star.common.annotation.OrmEnum;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

@BeanTable(name="sys_dept")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SysDept extends EntityBase{
	private String deptNo;//部门编号
	private String deptName;//部门名称
	
	@BeanColumn(relation=OrmEnum.Many2Many,threeTable = "sys_user_dept")
	private List<SysUser> sysUserList;
	
	
}
