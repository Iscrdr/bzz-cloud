package com.star.cloud.excelsax;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class ValueUtils {
	public static void main(String[] args) {
		System.out.println(getDouble("0.00%"));
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
			if(str.contains("%")){
				str = str.replaceAll("%","");
			}
			if(StringUtils.isNotBlank(str) ){
				
				BigDecimal db = new BigDecimal(str);
				strDouble = db.setScale(8, BigDecimal.ROUND_HALF_UP).doubleValue();
			}else{
				strDouble = 0.00;
			}
		}
		return strDouble;
	}
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
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
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
