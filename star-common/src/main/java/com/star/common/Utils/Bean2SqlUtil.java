package com.star.common.Utils;

import com.star.common.annotation.BeanColumn;
import com.star.common.annotation.BeanTable;
import com.star.common.annotation.OrmEnum;
import com.star.common.entity.SysDept;
import com.star.common.entity.SysUser;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bean2SqlUtil {
	public static final char UNDERLINE='_';
	
	/**
	 * SysUser ----> sys_user
	 * @param param
	 * @return
	 */
	public static String camelToUnderline(String param){
		if (StringUtils.isBlank(param)){
			return "";
		}
		int len = param.length();
		StringBuilder sb= new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)){
				sb.append(UNDERLINE);
				sb.append(Character.toLowerCase(c));
			}else{
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	/**
	 * sys_user ----> SysUser
	 * @param param
	 * @return
	 */
	public static String underlineToCamel(String param){
		if (StringUtils.isBlank(param)){
			return "";
		}
		int len=param.length();
		StringBuilder sb=new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == UNDERLINE){
				if (++i<len){
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			}else{
				sb.append(c);
			}
		}
		return sb.toString();
	}
	public static String underlineToCamel2(String param){
		if (param==null||"".equals(param.trim())){
			return "";
		}
		StringBuilder sb=new StringBuilder(param);
		Matcher mc= Pattern.compile("_").matcher(param);
		int i=0;
		while (mc.find()){
			int position=mc.end()-(i++);
			//String.valueOf(Character.toUpperCase(sb.charAt(position)));
			sb.replace(position-1,position+1,sb.substring(position,position+1).toUpperCase());
		}
		return sb.toString();
	}
	
	
	
	/**
	 * 根据实体类获取表名，例如：com.star.common.entity.SysUser,对应的表名为sys_user
	 *
	 * @param  clazz
	 * @return
	 */
	public static String getBeanName(Class clazz){
		String tableName = null;
		try {
			//获取注解，@BeanTable(name="");
			if(clazz.isAnnotationPresent(BeanTable.class)){
				BeanTable annotation = (BeanTable)clazz.getAnnotation(BeanTable.class);
				tableName = annotation.name();
			}else {
				//如果没有注解直接将实体类（驼峰方式命名）小写加下划线规则的表名，例如：SysUser ---> sys_user
				tableName = underlineToCamel(clazz.getSimpleName());
			}
			return tableName;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 获取字段名称和类型
	 * @param clz
	 * @return
	 */
	public static List<Map<String,String>> getBeanPropertyList(Class clz){
		try {
			Field[] strs = clz.getDeclaredFields();
			List<Map<String,String>> propertyList = new ArrayList<Map<String,String>>();
			for (int i = 0; i < strs.length; i++) {
				Map<String,String> map = new HashMap<String, String>();
				map.put(strs[i].getName(),strs[i].getType().toString());
				propertyList.add(map);
			}
			return propertyList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 通过注解上的字段名称（数据库中表示列名）和类型
	 * Map<String,Map<Class,String>> ==> Map<bean字段属性,Map<字段类型,数据库列名>>
	 * @param clz
	 * @return
	 */
	public static Map<String,Map<Class,String>> getBeanFieldList(Class clz){
		try {
			Field[] strs = clz.getDeclaredFields();
			Annotation[] annotations = clz.getAnnotations();
			//key:字段的名称:userName，value:为注解值，即：列名,user_name
			Map<String,Map<Class,String>> maps = new LinkedHashMap<String,Map<Class,String>>();
			for (int i = 0; i < strs.length; i++) {
				BeanColumn annotation = strs[i].getAnnotation(BeanColumn.class);
				//映射该字段
				if(null != annotation && annotation.no2Field()){
				
				}
				
				/*Map<Class,String> map = new LinkedHashMap<Class, String>();
				if(null != annotation && (false != annotation.no2Field()  &&  OrmEnum.class != annotation.ormName().getClass())){
					map.put(strs[i].getType(),annotation.name());//不加外键
					maps.put(strs[i].getName(),map);
				}else if(null != annotation &&  OrmEnum.class == annotation.ormName().getClass() ){
					String underLine = camelToUnderline(clz.getSimpleName()).substring(1,camelToUnderline(clz.getSimpleName()).length());
					if(StringUtils.isNotBlank(annotation.foreignKey()) &&
							StringUtils.isNotBlank(annotation.mainTable()) &&
							annotation.mainTable().equals(underLine)){
						map.put(strs[i].getType(),annotation.foreignKey());//加外键
						maps.put(underlineToCamel(annotation.foreignKey()),map);
					}else {
						map.put(strs[i].getType(),annotation.name());//不加外键
						maps.put(strs[i].getName(),map);
					}
				}else{
					map.put(strs[i].getType(),camelToUnderline(strs[i].getName()));
					maps.put(strs[i].getName(),map);
				}
				*/
				
			}
			return maps;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getBeanFilesList(String bean){
		try {
			Class clz = Class.forName(bean);
			Field[] strs = clz.getDeclaredFields();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < strs.length; i++) {
				String protype = strs[i].getType().toString();
				if (!strs[i].getName().equals("tableName")&&!strs[i].getType().equals("List")) {
					sb.append(strs[i].getName()+",");
				}
			}
			sb.deleteCharAt(sb.toString().lastIndexOf(","));
			return sb.toString();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 *
	 * @param clazz
	 * @param isBatch 是否批量插入
	 * @param size 批量插入多少条，
	 * @return
	 */
	public static String genInsert(Class clazz,Boolean isBatch,int size){
		Map<String, Map<Class, String>> beanFieldList = getBeanFieldList(clazz);
		StringBuffer sql = new StringBuffer("INSERT INTO  ");
		sql.append(getBeanName(clazz));
		sql.append(" ( ");
		beanFieldList.forEach((k,v)->{
			v.forEach((a,b)->{
				if(StringUtils.isNotBlank(b)){
					sql.append(b+",");
				}
			});
		});
		if(sql.toString().endsWith(",")){
			sql.deleteCharAt(sql.length()-1);
		}
		sql.append(" ) VALUES  ");
		if(isBatch && size > 1){
			for(int i = 0; i < size; i++){
				sql.append(" ( ");
				for(int j=0;j<beanFieldList.size();j++){
					sql.append("?,");
				}
				if(sql.toString().endsWith(",")){
					sql.deleteCharAt(sql.length()-1);
				}
				sql.append("),");
			}
			if(sql.toString().endsWith(",")){
				sql.deleteCharAt(sql.length()-1);
			}
		}else {
			sql.append("( ");
			for(int j=0;j<beanFieldList.size();j++){
				sql.append("?,");
			}
			if(sql.toString().endsWith(",")){
				sql.deleteCharAt(sql.length()-1);
			}
			sql.append(")");
		}
		return sql.toString();
	}
	
	public static String genInsert(Class clazz){
		Map<String, Map<Class, String>> beanFieldList = getBeanFieldList(clazz);
		StringBuffer sql = new StringBuffer("INSERT INTO  ");
		sql.append(getBeanName(clazz));
		sql.append(" ( ");
		beanFieldList.forEach((k,v)->{
			v.forEach((a,b)->{
				if(StringUtils.isNotBlank(b)){
					sql.append(b+",");
				}
			});
		});
		if(sql.toString().endsWith(",")){
			sql.deleteCharAt(sql.length()-1);
		}
		sql.append(" ) VALUES ( ");
		for(int i=0;i<beanFieldList.size();i++){
			sql.append("?,");
		}
		if(sql.toString().endsWith(",")){
			sql.deleteCharAt(sql.length()-1);
		}
		sql.append(")");
		return sql.toString();
	}
	
	public static List<Class> getClassByOrmEnum(Class clazz1){
		try {
			Field[] strs = clazz1.getDeclaredFields();
			Annotation[] annotations = clazz1.getAnnotations();
			//key:字段的名称:userName，value:为注解值，即：列名,user_name
			List<Class> clazzes = new ArrayList<Class>();
			for (int i = 0; i < strs.length; i++) {
				BeanColumn annotation = strs[i].getAnnotation(BeanColumn.class);
				/*if(null != annotation &&  OrmEnum.class == annotation.ormName().getClass() ){
					
					if(null != strs[i] && null != strs[i].getGenericType()){
						if(null != strs[i].getType().getSuperclass() && BeanBase.class == strs[i].getType().getSuperclass()){
							clazzes.add(strs[i].getType());
						}else if(ParameterizedType.class.isAssignableFrom(strs[i].getGenericType().getClass())){
							ParameterizedType pt = (ParameterizedType) strs[i].getGenericType();
							if (pt.getRawType().equals(List.class)) {
								clazzes.add((Class)pt.getActualTypeArguments()[0]);
							}
						}

					}
					
					
				}*/
			}
			return clazzes;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 批量生成插入语句（含映射关系）
	 * @param clazz1
	 * @return
	 */
	public static List<String> genInsertOrm(Class clazz1){
		List<Class> clazzes = getClassByOrmEnum(clazz1);
		List<String> sqlList = new ArrayList<String>();
		sqlList.add(genInsert(clazz1,false,1));
		clazzes.forEach(Class ->{
			sqlList.add(genInsert(Class,false,1));
		});
		
		return sqlList;
	}
	/**
	 * 批量生成修改语句（含映射关系）：update
	 * @param clazz1
	 * @return
	 */
	public static String genUpdate(Class clazz1){
		Map<String, Map<Class, String>> beanFieldList = getBeanFieldList(clazz1);
		StringBuffer sql = new StringBuffer("UPDATE FROM  ");
		sql.append(getBeanName(clazz1));
		sql.append(" SET  ");
		beanFieldList.forEach((k,v)->{
			v.forEach((a,b)->{
				if(StringUtils.isNotBlank(b)){
					sql.append(b+" = ? AND ");
				}
			});
		});
		if(sql.toString().trim().endsWith("AND")){
			sql.deleteCharAt(sql.length()-1);
			sql.deleteCharAt(sql.length()-1);
			sql.deleteCharAt(sql.length()-1);
			sql.deleteCharAt(sql.length()-1);
		}
		sql.append(" WHERE ");
		beanFieldList.forEach((k,v)->{
			v.forEach((a,b)->{
				if(StringUtils.isNotBlank(b)){
					sql.append(b+" = ? AND ");
				}
			});
		});
		if(sql.toString().trim().endsWith("AND")){
			sql.deleteCharAt(sql.length()-1);
			sql.deleteCharAt(sql.length()-1);
			sql.deleteCharAt(sql.length()-1);
			sql.deleteCharAt(sql.length()-1);
		}
		
		return sql.toString();
	}
	
	/**
	 * 生成 Delete
	 * @param clazz1
	 */
	public static String genDelete(Class clazz1){
		Map<String, Map<Class, String>> beanFieldList = getBeanFieldList(clazz1);
		StringBuffer sql = new StringBuffer("DELETE FROM  ");
		sql.append(getBeanName(clazz1));
		sql.append(" WHERE  ");
		beanFieldList.forEach((k,v)->{
			v.forEach((a,b)->{
				if(StringUtils.isNotBlank(b)){
					sql.append(b+" = ? AND ");
				}
			});
		});
		if(sql.toString().trim().endsWith("AND")){
			sql.deleteCharAt(sql.length()-1);
			sql.deleteCharAt(sql.length()-1);
			sql.deleteCharAt(sql.length()-1);
			sql.deleteCharAt(sql.length()-1);
		}
		
		return sql.toString();
	}
	
	/**
	 * 生成 SELECT
	 * @param
	 */
	public static String genSelect(Object obj){
		Map<String, Map<Class, String>> beanFieldList = getBeanFieldList(obj.getClass());
		StringBuffer sql = new StringBuffer("SELECT  ");
		beanFieldList.forEach((k,v)->{
			v.forEach((a,b)->{
				if(StringUtils.isNotBlank(b)){
					sql.append(b+" AS " + underlineToCamel(b)+",");
				}
			});
		});
		if(sql.toString().endsWith(",")){
			sql.deleteCharAt(sql.length()-1);
		}
		sql.append(" FROM  ");
		sql.append(getBeanName(obj.getClass()));
		sql.append(" WHERE  ");
		beanFieldList.forEach((k,v)->{
			v.forEach((a,b)->{
				if(StringUtils.isNotBlank(b)){
					sql.append(b+" = ? AND ");
				}
			});
		});
		if(sql.toString().trim().endsWith("AND")){
			sql.deleteCharAt(sql.length()-1);
			sql.deleteCharAt(sql.length()-1);
			sql.deleteCharAt(sql.length()-1);
			sql.deleteCharAt(sql.length()-1);
		}
		
		return sql.toString();
	}
	
	public static void main(String[] args) {
		//List<String> strings = genInsertOrm(SysUser.class);
//		List<String> strings = genInsertOrm(SysDept.class);
//		strings.forEach(string ->{
//			System.out.println(string);
//		});
		//System.out.println(genUpdate(SysUser.class));
		//System.out.println(genDelete(SysUser.class));
//		SysDept sysDept = new SysDept();
//		System.out.println(genSelect(sysDept));
		/*SysDept sysDept = new SysDept();
		Map<String,String > map = new HashMap<String,String>();
		String deptName = map.put("deptName", new HashMap<>().put("", new Object() {"1"}));
		sysDept.setSqlMap(map);*/
	}
	
}
