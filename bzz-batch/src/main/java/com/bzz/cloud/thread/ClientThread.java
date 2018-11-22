package com.bzz.cloud.thread;

import com.bzz.cloud.DBUtils.DBUtil;
import com.bzz.cloud.SnowflakeIdFactory;
import com.bzz.cloud.SnowflakeIdWorker;
import com.bzz.cloud.excelsax.ValueUtils;
import com.bzz.common.jdbc.hikaricp.conf.JdbcProperties;
import com.bzz.common.jdbc.hikaricp.connect.HikariPoolManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class ClientThread implements Runnable {

	private List<String> list ; //数据
	private List<List<String>> lists ; //数据
	//private String fileName; //文件名
	private String errorFile = "d://test//1.csv"; //错误记录文件路径
	//private SnowflakeIdWorker idWorker ;
	private SnowflakeIdFactory idWorker ;
	private Set<Long> setAll ;
	private CountDownLatch cdLatch;
	public ClientThread(List<List<String>> lists,String fileName,String errorFile) {
		this.lists = lists;
	//	this.fileName = fileName;
		//this.errorFile = errorFile;

	}
	public ClientThread(List<List<String>> lists, Set<Long> setAll,SnowflakeIdFactory idWorker) {
		this.lists = lists;
		this.setAll = setAll;
		this.idWorker = idWorker;

	}

	public  void run() {
		Connection conn = null;
		PreparedStatement pstm =null;
		ResultSet rt = null;
		FileWriter fw =  null;

		Set<Long> setId = new HashSet<>();
		try {
			//创建错误数据记录文件
			
			if(StringUtils.isNotBlank(errorFile)){
				fw = new FileWriter(errorFile);
			}
			
			conn = DBUtil.getConnection();

			/*HikariConfig hikariConfig = new HikariConfig();
			hikariConfig.setDriverClassName(JdbcProperties.mysqlDriverClassName);
			hikariConfig.setJdbcUrl(JdbcProperties.mysqlJdbcUrl);
			hikariConfig.setUsername(JdbcProperties.mysqlUsername);
			hikariConfig.setPassword(JdbcProperties.mysqlPassword);
			hikariConfig.setAutoCommit(false);
			hikariConfig.setIdleTimeout(10);
			hikariConfig.setMaxLifetime(180000);
			hikariConfig.setMaximumPoolSize(50);
			hikariConfig.setMinimumIdle(1);
			hikariConfig.setPoolName("Hikari-pool");


			HikariDataSource hikariDataSource = HikariPoolManager.getHikariDataSource(hikariConfig);
			conn = hikariDataSource.getConnection();*/
			
			conn.setAutoCommit(false);
			String sql = "INSERT  INTO c_cust_sale_2018 (ID, gongchang, daqu, chengshi, yewuyuan\n" +
					"\t, cust_no, cust_name, dapinleimiaoshu, yijipinleimiaoshu, erjipinleimiaoshu\n" +
					"\t, sanjipinleimiaoshu, chanpinxianmiaoshu, wuliaobianma, wuliaomiaoshu, xiang\n" +
					"\t, dun, xiaoshoushouru, jingzhi, shuie, zhanlvjine\n" +
					"\t, zhekoujine, zhekoubili, shoudafangjiancheng, fapiaoshiqi, dingdanbianhao\n" +
					"\t, danjuriqi, kucundidian, caigoubianhao, create_by, create_date\n" +
					"\t, update_by, update_date, remarks, del_flag)\n" +
					"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			//String sqlPre = "INSERT INTO c_cust_sale(ID, gongchang, daqu, chengshi, yewuyuan, cust_no, cust_name, dapinleimiaoshu, yijipinleimiaoshu, erjipinleimiaoshu, sanjipinleimiaoshu, chanpinxianmiaoshu, wuliaobianma, wuliaomiaoshu, xiang, dun, xiaoshoushouru, jingzhi, shuie, zhanlvjine, zhekoujine, zhekoubili, shoudafangjiancheng, fapiaoshiqi, dingdanbianhao, danjuriqi, kucundidian, caigoubianhao, create_by, create_date, update_by, update_date, remarks, del_flag) VALUES ";
			//StringBuilder suffix =  new StringBuilder(100000);
			long startTime1 = System.currentTimeMillis();//每次提交事务的开始时间
			long startTime2 = System.currentTimeMillis();//所有事务的开始时间
			if(null != lists  && lists.size()>0){
				//行数据遍历
				for(int i=0;i<lists.size();i++){
					long id = idWorker.nextId();
					setId.add(id);
					//列数据遍历
					List<String> rowList = lists.get(i);
					if(null!=rowList && rowList.size()>0){
						if(StringUtils.isBlank(rowList.get(5))){
							//System.out.println("第"+i+"行，数据错误："+rowList.toString());
							fw.write("第"+i+"行，数据错误："+rowList.toString()+"\r\n");
						}else if(StringUtils.isNotBlank(rowList.get(5)) && "客户名称".equals(rowList.get(5))){
							//System.out.println("第"+i+"行，数据错误,此行数据是表头"+rowList.toString());
							fw.write("第"+i+"行，数据错误,此行数据是表头"+rowList.toString()+"\r\n");
						}else {

								//log.info("{}生产了{}个id,并成功加入到setAll中.",Thread.currentThread().getName(),n);

								pstm.setString(1, id + "");
								pstm.setString(2, rowList.get(0));
								pstm.setString(3, rowList.get(1));
								pstm.setString(4, rowList.get(2));
								pstm.setString(5, rowList.get(3));
								pstm.setString(6, rowList.get(4));
								pstm.setString(7, rowList.get(5));
								pstm.setString(8, rowList.get(6));
								pstm.setString(9, rowList.get(7));
								pstm.setString(10, rowList.get(8));
								pstm.setString(11, rowList.get(9));
								pstm.setString(12, rowList.get(10));
								pstm.setString(13, rowList.get(11));
								pstm.setString(14, rowList.get(12));

								String xiang = rowList.get(13);
								Double xiangDouble = ValueUtils.getDouble(xiang);
								pstm.setDouble(15, xiangDouble);

								String dun = rowList.get(14);
								Double dunDouble = ValueUtils.getDouble(dun);
								pstm.setDouble(16, dunDouble);

								String xiaoshoushouru = rowList.get(15);
								Double xiaoshoushouruDouble = ValueUtils.getDouble(xiaoshoushouru);
								pstm.setDouble(17, xiaoshoushouruDouble);

								String jingzhi = rowList.get(16);
								Double jingzhiDouble = ValueUtils.getDouble(jingzhi);
								pstm.setDouble(18, jingzhiDouble);

								String shuie = rowList.get(17);
								Double shuieDouble = ValueUtils.getDouble(shuie);
								pstm.setDouble(19, shuieDouble);

								String zhanlvjiajine = rowList.get(18);
								Double zhanlvjiajineDouble = ValueUtils.getDouble(zhanlvjiajine);
								pstm.setDouble(20, zhanlvjiajineDouble);

								String zhekoujine = rowList.get(19);
								//System.out.println(zhekoujine);
								Double zhekoujineDouble = ValueUtils.getDouble(zhekoujine);
								pstm.setDouble(21, zhekoujineDouble);

								String zhekoubaifenbi = rowList.get(20);
								if (StringUtils.isNotBlank(zhekoubaifenbi)) {
									if (zhekoubaifenbi.endsWith("%")) {
										zhekoubaifenbi = zhekoubaifenbi.replace("%", "");
									}
									if (zhekoubaifenbi.endsWith("-")) {
										zhekoubaifenbi = zhekoubaifenbi.replace("-", "");
									}

								}
								//System.out.println("行数："+i);
								Double zhekoubaifenbiDouble = ValueUtils.getDouble(zhekoubaifenbi);
								pstm.setDouble(22, zhekoubaifenbiDouble);

								pstm.setString(23, rowList.get(21));

								String fapiaoriqi = rowList.get(22);
								Date fapiaoriqiDate = ValueUtils.getDate(fapiaoriqi);
								pstm.setDate(24, fapiaoriqiDate);

								pstm.setString(25, rowList.get(23));

								String danjuriqi = rowList.get(24);
								Date danjuriqiDate = ValueUtils.getDate(danjuriqi);
								pstm.setDate(26, danjuriqiDate);

								pstm.setString(27, rowList.get(25));

								String caigoubianhao = "";
								if (rowList.size() > 26) {
									caigoubianhao = rowList.get(26);
									if (StringUtils.isNotBlank(rowList.get(26)) && rowList.get(26).length() > 100) {
										System.out.println("此列数据过大：" + i);
										caigoubianhao = caigoubianhao.substring(0, 100);
									}
								}
								pstm.setString(28, caigoubianhao);
								pstm.setString(29, "admin");
								pstm.setDate(30, new Date(new java.util.Date().getTime()));
								pstm.setString(31, "admin");
								pstm.setDate(32, new Date(new java.util.Date().getTime()));
								pstm.setString(33, "企业销售数据：手工使用jdbc导入");
								pstm.setString(34, "0");

								pstm.addBatch();

								if ((i % 10000) == 0) {
									pstm.executeBatch();
									conn.commit();
									pstm.clearBatch();
									long endTime = System.currentTimeMillis();

									//System.out.println("------->> 正在将文件"+fileName+".xlsx中第" + i + "条导入到数据库,耗时："+(endTime-startTime1));

									System.out.println("-------> " + i + "条导入数据库,耗时：" + (endTime - startTime1));

									startTime1 = System.currentTimeMillis();
								}
							}
					}else {
						//System.out.println("第"+i+"行，是空行");
						fw.write("第"+i+"行，是空行"+"\r\n");
					}
				}
				/*synchronized (setAll){
					setAll.addAll(setId);
					//log.info("{}生产了{}个id,并成功加入到setAll中.",Thread.currentThread().getName(),n);
				}*/


				pstm.executeBatch();
				conn.commit();
				long endTime2 = System.currentTimeMillis();
				//System.out.println("=======>> 从文件"+fileName+".xlsx中导入数据库完毕，共有" + lists.size() + "条导入到了数据库,共耗时："+(endTime2-startTime2));
				System.out.println("=======>>导入数据库完毕，共有" + lists.size() + "条导入到了数据库,共耗时："+(endTime2-startTime2));
				
				pstm.clearBatch();
			}
		}catch (Exception e  ){
			e.printStackTrace();
			
		}finally {
			DBUtil.closeConnection(conn);
			try {
				fw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			//cdLatch.countDown();
		}

		
	}
}
