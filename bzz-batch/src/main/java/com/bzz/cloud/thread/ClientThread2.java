package com.bzz.cloud.thread;

import com.bzz.cloud.DBUtils.DBUtil;
import com.bzz.cloud.excelsax.ValueUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

public class ClientThread2 implements Runnable {
	
	private List<List<String>> lists ; //数据
	private String fileName; //文件名
	private String errorFile; //错误记录文件路径
	
	public ClientThread2(List<List<String>> lists, String fileName, String errorFile) {
		this.lists = lists;
		this.fileName = fileName;
		this.errorFile = errorFile;
		
	}
	
	public void run() {
		Connection conn = null;
		PreparedStatement pstm =null;
		ResultSet rt = null;
		FileWriter fw = null;
		try {
			//创建错误数据记录文件
			
			if(StringUtils.isNotBlank(errorFile)){
				fw = new FileWriter(errorFile);
			}
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
//			String sql = "INSERT INTO c_cust_sale_1 (ID, gongchang, daqu, chengshi, yewuyuan\n" +
//					"\t, cust_no, cust_name, dapinleimiaoshu, yijipinleimiaoshu, erjipinleimiaoshu\n" +
//					"\t, sanjipinleimiaoshu, chanpinxianmiaoshu, wuliaobianma, wuliaomiaoshu, xiang\n" +
//					"\t, dun, xiaoshoushouru, jingzhi, shuie, zhanlvjine\n" +
//					"\t, zhekoujine, zhekoubili, shoudafangjiancheng, fapiaoshiqi, dingdanbianhao\n" +
//					"\t, danjuriqi, kucundidian, caigoubianhao, create_by, create_date\n" +
//					"\t, update_by, update_date, remarks, del_flag)\n" +
//					"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstm = conn.prepareStatement("");
			String sqlPre = "INSERT INTO c_cust_sale(ID, gongchang, daqu, chengshi, yewuyuan, cust_no, cust_name, dapinleimiaoshu, yijipinleimiaoshu, erjipinleimiaoshu, sanjipinleimiaoshu, chanpinxianmiaoshu, wuliaobianma, wuliaomiaoshu, xiang, dun, xiaoshoushouru, jingzhi, shuie, zhanlvjine, zhekoujine, zhekoubili, shoudafangjiancheng, fapiaoshiqi, dingdanbianhao, danjuriqi, kucundidian, caigoubianhao, create_by, create_date, update_by, update_date, remarks, del_flag) VALUES ";
			StringBuilder suffix =  new StringBuilder(100000);
			long startTime1 = System.currentTimeMillis();//每次提交事务的开始时间
			long startTime2 = System.currentTimeMillis();//所有事务的开始时间
			String sql = sqlPre + "";
			if(null != lists  && lists.size()>0){
				
				//行数据遍历
				for(int i=0;i<lists.size();i++){
					//列数据遍历
					List<String> rowList = lists.get(i);
					if(null!=rowList && rowList.size()>0){
						if(StringUtils.isBlank(rowList.get(5))){
							fw.write("第"+i+"行，数据错误："+rowList.toString()+"\r\n");
						}else if(StringUtils.isNotBlank(rowList.get(5)) && "客户名称".equals(rowList.get(5))){
							fw.write("第"+i+"行，数据错误,此行数据是表头"+rowList.toString()+"\r\n");
						}else {
							String xiang = rowList.get(13);
							Double xiangDouble = ValueUtils.getDouble(xiang);
							String dun = rowList.get(14);
							Double dunDouble = ValueUtils.getDouble(dun);
							String xiaoshoushouru = rowList.get(15);
							Double xiaoshoushouruDouble = ValueUtils.getDouble(xiaoshoushouru);
							String jingzhi = rowList.get(16);
							Double jingzhiDouble = ValueUtils.getDouble(jingzhi);
							String shuie = rowList.get(17);
							Double shuieDouble = ValueUtils.getDouble(shuie);
							
							String zhanlvjiajine = rowList.get(18);
							Double zhanlvjiajineDouble = ValueUtils.getDouble(zhanlvjiajine);
							
							String zhekoujine = rowList.get(19);
							Double zhekoujineDouble = ValueUtils.getDouble(zhekoujine);
							
							String zhekoubaifenbi = rowList.get(20);
							if(StringUtils.isNotBlank(zhekoubaifenbi) ){
								if(zhekoubaifenbi.endsWith("%")){
									zhekoubaifenbi = zhekoubaifenbi.replace("%", "");
								}
								if(zhekoubaifenbi.endsWith("-")){
									zhekoubaifenbi = zhekoubaifenbi.replace("-", "");
								}
								
							}
							Double zhekoubaifenbiDouble =ValueUtils.getDouble(zhekoubaifenbi);
							
							
							String fapiaoriqi = rowList.get(22);
							Date fapiaoriqiDate = ValueUtils.getDate(fapiaoriqi);
							
							
							String danjuriqi = rowList.get(24);
							Date danjuriqiDate = ValueUtils.getDate(danjuriqi);
							
							String caigoubianhao = rowList.get(26);
							if(StringUtils.isNotBlank(rowList.get(26)) && rowList.get(26).length()>100){
								System.out.println("此列数据过大："+i);
								caigoubianhao = caigoubianhao.substring(0,100);
							}
							
							
							//System.out.println("sql的长度为："+sql.length()+",行数："+i);
							if(i==(lists.size()-1)){
								sql +=  "(" +
									UUID.randomUUID().toString().replaceAll("-", "") + ","+
										rowList.get(0) + ","+
										rowList.get(1) + ","+
										rowList.get(2) + ","+
										rowList.get(3) + ","+
										rowList.get(4) + ","+
										rowList.get(5) + ","+
										rowList.get(6) + ","+
										rowList.get(7) + ","+
										rowList.get(8) + ","+
										rowList.get(9) + ","+
										rowList.get(10) + ","+
										rowList.get(11) + ","+
										rowList.get(12) + ","+
										xiangDouble + ","+
										dunDouble + ","+
										xiaoshoushouruDouble + ","+
										jingzhiDouble + ","+
										shuieDouble + ","+
										zhanlvjiajineDouble + ","+
										zhekoujineDouble + ","+
										zhekoubaifenbiDouble + ","+
										rowList.get(21) + ","+
										fapiaoriqiDate + ","+
										rowList.get(23) + ","+
										danjuriqiDate + ","+
										rowList.get(25) + ","+
										caigoubianhao + ","+
										"admin" + ","+
										new Date(new java.util.Date().getTime()) + ","+
										"admin" + ","+
										new Date(new java.util.Date().getTime()) + ","+
										"企业销售数据：手工使用jdbc导入" + ","+
										"0" + ");";
										
							}else{
								sql +=  sqlPre + "(" +
										UUID.randomUUID().toString().replaceAll("-", "") + ","+
										rowList.get(0) + ","+
										rowList.get(1) + ","+
										rowList.get(2) + ","+
										rowList.get(3) + ","+
										rowList.get(4) + ","+
										rowList.get(5) + ","+
										rowList.get(6) + ","+
										rowList.get(7) + ","+
										rowList.get(8) + ","+
										rowList.get(9) + ","+
										rowList.get(10) + ","+
										rowList.get(11) + ","+
										rowList.get(12) + ","+
										xiangDouble + ","+
										dunDouble + ","+
										xiaoshoushouruDouble + ","+
										jingzhiDouble + ","+
										shuieDouble + ","+
										zhanlvjiajineDouble + ","+
										zhekoujineDouble + ","+
										zhekoubaifenbiDouble + ","+
										rowList.get(21) + ","+
										fapiaoriqiDate + ","+
										rowList.get(23) + ","+
										danjuriqiDate + ","+
										rowList.get(25) + ","+
										caigoubianhao + ","+
										"admin" + ","+
										new Date(new java.util.Date().getTime()) + ","+
										"admin" + ","+
										new Date(new java.util.Date().getTime()) + ","+
										"企业销售数据：手工使用jdbc导入" + ","+
										"0" + "),";
							}
							
							
							pstm.addBatch(sql);
							if ((i % 2000) == 0){
								pstm.executeBatch();
								conn.commit();
								long endTime = System.currentTimeMillis();
								
								System.out.println("------->> 正在将文件"+fileName+".xlsx中第" + i + "条导入到数据库,耗时："+(endTime-startTime1));
								startTime1 = System.currentTimeMillis();
							}
						}
						
					}else {
						//System.out.println("第"+i+"行，是空行");
						fw.write("第"+i+"行，是空行"+"\r\n");
					}
					
				}
				pstm.addBatch(sql);
				pstm.executeBatch();
				conn.commit();
				long endTime2 = System.currentTimeMillis();
				System.out.println("=======>> 从文件"+fileName+".xlsx中导入数据库完毕，共有" + lists.size() + "条导入到了数据库,共耗时："+(endTime2-startTime2));
				fw.close();
			}
		}catch (Exception e){
			e.printStackTrace();
			
		}finally {
			if(null!=conn){
				DBUtil.closeConnection();
			}
			try {
				fw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
}
