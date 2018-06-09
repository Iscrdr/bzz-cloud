package com.star.common.Utils;


import com.star.common.annotation.BeanColumn;
import com.star.common.annotation.BeanTable;
import com.star.common.annotation.OrmEnum;

import java.lang.reflect.Field;

public class Bean2SqlUtil2 {
	
	
	
	public static void main(String[] args) {
	
	}
	
	public String getSelectPart(Object obj){
		Class<?> clz = obj.getClass();
		BeanTable beanTable = clz.getAnnotation(BeanTable.class);
		String tableName = "";//表名
		String tableAlias = "";//表的别名
		if(null != beanTable){
			tableName = beanTable.name();
			tableAlias = beanTable.alias();
		}
		
		Field[] strs = clz.getDeclaredFields();
		StringBuffer selectSql = new StringBuffer("SELECT ");
		StringBuffer fromSql = new StringBuffer("FROM ");
		StringBuffer wherSql = new StringBuffer("WHER 1=1 ");
		if(null != strs && strs.length >0){
			for (int i = 0; i < strs.length; i++) {
				BeanColumn annotation = strs[i].getAnnotation(BeanColumn.class);
				//映射该字段，将该字段添加到SELECT中
				if(null != annotation && annotation.no2Field()){
					OrmEnum[] relation = annotation.relation();
					//普通字段
					if(null == relation || relation.length<=0 ){
						String name = annotation.name();
						selectSql.append(tableAlias+"."+name+" AS "+strs[i].getName()+",");
						try {
							Object value = strs[i].get(obj);
							if( null != value ){
								//Integer 或者 int
								if(strs[i].getType().getName().equals("java.lang.Integer")
										|| strs[i].getType().getName().equals("int")){
									wherSql.append("AND "+tableAlias+"."+name + " = :"+strs[i].getName());
								}
								//Double 或者 double
								if(strs[i].getType().getName().equals("java.lang.Double")
										|| strs[i].getType().getName().equals("double")){
									wherSql.append("AND "+tableAlias+"."+name + " = :"+strs[i].getName());
								}
								//Float 或者 float
								if(strs[i].getType().getName().equals("java.lang.Float")
										|| strs[i].getType().getName().equals("float")){
									wherSql.append("AND "+tableAlias+"."+name + " = :"+strs[i].getName());
								}
								//Integer 或者 int
								if(strs[i].getType().getName().equals("java.lang.Long")
										|| strs[i].getType().getName().equals("long")){
									wherSql.append("AND "+tableAlias+"."+name + " = :"+strs[i].getName());
								}
								//Integer 或者 int
								if(strs[i].getType().getName().equals("java.lang.Short")
										|| strs[i].getType().getName().equals("short")){
									wherSql.append("AND "+tableAlias+"."+name + " = :"+strs[i].getName());
								}
								//Integer 或者 int
								if(strs[i].getType().getName().equals("java.lang.Integer")
										|| strs[i].getType().getName().equals("int")){
									wherSql.append("AND "+tableAlias+"."+name + " = :"+strs[i].getName());
								}
								//Integer 或者 int
								if(strs[i].getType().getName().equals("java.lang.Integer")
										|| strs[i].getType().getName().equals("int")){
									wherSql.append("AND "+tableAlias+"."+name + " = :"+strs[i].getName());
								}
								//Integer 或者 int
								if(strs[i].getType().getName().equals("java.lang.Integer")
										|| strs[i].getType().getName().equals("int")){
									wherSql.append("AND "+tableAlias+"."+name + " = :"+strs[i].getName());
								}
								//Integer 或者 int
								if(strs[i].getType().getName().equals("java.lang.Integer")
										|| strs[i].getType().getName().equals("int")){
									wherSql.append("AND "+tableAlias+"."+name + " = :"+strs[i].getName());
								}
							}
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}else{
						//关联字段处理，一对一，一对多，
						
						
						/*for(int j = 0; j<relation.length;j++){
							if(relation[j].equals("One2one") || relation[j].equals("One2many") || relation[j].equals("Many2one")){
								String foreignKey = annotation.foreignKey();
								Class<?> type = strs[i].getType();
								String simpleName = type.getSimpleName();
								char[] chars = simpleName.toCharArray();
								if (chars[0] >= 'a' && chars[0] <= 'z') {
									chars[0] = (char) (chars[0] - 32);
								}
								simpleName = new String(chars);//首字母小写
								selectSql.append(foreignKey+" AS "+simpleName+".id,");
							}
							
						}*/
					}
				}
			}
			selectSql.deleteCharAt(selectSql.toString().lastIndexOf(","));
		}
		
		
		
		return  "";
	}
	
	public String getFromPart(Object obj){
		
		return  "";
	}
	public String getWherePart(Object obj){
		
		return  "";
	}
	
	/**
	 *
	 * @param
	 */
	public String bean2Sql(Object obj) {
		//1.生成 SELECT ... FROM 部分
		//2.生成 FROM 后面的部分
		//3.生成 WHERE 后面的条件
		
		return  "";
	}
}
