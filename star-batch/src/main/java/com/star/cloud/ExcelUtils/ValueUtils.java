package com.star.cloud.ExcelUtils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ValueUtils {
	public static void main(String[] args) {
		System.out.println(getDouble("0.56-"));
	}
	
	public static Double  getDouble(String str){
		Double strDouble = 0.00;
		if(StringUtils.isNotBlank(str)){
			if(str.contains(",")){
				str = str.replaceAll(",","");
			}
			if(str.contains("*")){
				str = str.replaceAll("\\*","");
			}
			if(str.endsWith("-")){
				str = str.substring(0,str.length()-1);
			}
			
			BigDecimal db = new BigDecimal(str);
			
			strDouble = db.setScale(8, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		return strDouble;
	}
	
	/**
	 * 日期格式
	 * @param str 格式：yyyy-MM-dd
	 * @return
	 */
	public static Date getDate(String str){
		Double strDouble = 0.00;
		Date date = null;
		if(StringUtils.isNotBlank(str)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				java.util.Date parse = sdf.parse(str);
				date = new Date(parse.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}
}
