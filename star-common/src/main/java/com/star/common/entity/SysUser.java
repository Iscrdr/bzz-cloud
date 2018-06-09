package com.star.common.entity;

import com.star.common.annotation.BeanColumn;
import com.star.common.annotation.BeanTable;
import com.star.common.annotation.OrmEnum;
import lombok.*;

import java.util.Date;

@BeanTable(name="sys_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SysUser extends EntityBase{
	
	@BeanColumn(name="id")
	private String id;
	@BeanColumn(name="name")
	private String name;
	@BeanColumn(name="age")
	private int age;
	@BeanColumn(name="birth_day")
	private Date birthDay;
	
	@BeanColumn(relation = {OrmEnum.Many2one},foreignKey = "dept_id")
	private SysDept sysDept;
	
}
