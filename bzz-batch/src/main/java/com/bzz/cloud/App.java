package com.bzz.cloud;


import com.bzz.cloud.excelsax.ExampleEventUserModel;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

/**
 * Hello world!
 *
 */
public class App {
    private static String url = "jdbc:mysql://127.0.0.1:3306/rcsjfx?useUnicode=true&characterEncoding=utf-8&rewriteBatchedStatements=true&useSSL=false";
    private static String user = "root";
    private static String password = "root";
    private static String sql = "INSERT INTO c_cust_sale_2018 (ID, gongchang, daqu, chengshi, yewuyuan\n" +
		    "\t, cust_no, cust_name, dapinleimiaoshu, yijipinleimiaoshu, erjipinleimiaoshu\n" +
		    "\t, sanjipinleimiaoshu, chanpinxianmiaoshu, wuliaobianma, wuliaomiaoshu, xiang\n" +
		    "\t, dun, xiaoshoushouru, jingzhi, shuie, zhanlvjine\n" +
		    "\t, zhekoujine, zhekoubili, shoudafangjiancheng, fapiaoshiqi, dingdanbianhao\n" +
		    "\t, danjuriqi, kucundidian, caigoubianhao, create_by, create_date\n" +
		    "\t, update_by, update_date, remarks, del_flag)\n" +
		    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static void main(String[] args) {
        File file = new File("D://app//");
    
        if (file.isDirectory()) {
            File[] files = file.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
	                return name.endsWith("xlsx");
                }
            });
            for(int i=0;i<files.length;i++){
                String path = files[i].getPath();
                insertBatch(path);
            }
            
        }
    }
    
    public static Double  getDouble(String str){
        Double strDouble = 0.00;
        if(StringUtils.isNotBlank(str)){
            if(str.contains(",")){
                str = str.replaceAll(",","");
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
    public static Date  getDate(String str){
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
    
    public static void insertBatch(String fileName){
        Connection conn = null;
        PreparedStatement pstm =null;
        ResultSet rt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            pstm = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            Long startTime = System.currentTimeMillis();
            ExampleEventUserModel example = new ExampleEventUserModel();
           // String fileName = "d://app//中粮Call201401-03销售明细.xlsx";
            List<List<String>> list = example.processOneSheet(fileName);
            System.out.println("数据导入开始，文件名："+fileName);
            for(int i=0;i<list.size();i++){
                List<String> rowList = list.get(i);
                if(null==rowList || rowList.size()<=0){
                    System.out.println("第"+i+"行，是空行");
                }
                if(StringUtils.isBlank(rowList.get(0)) || StringUtils.isBlank(rowList.get(5))){
                    System.out.println("第"+i+"行，数据错误"+rowList.toString());
                }else{
                    if(StringUtils.isNotBlank(rowList.get(5)) && "客户名称".equals(rowList.get(5))){
                        System.out.println("第"+i+"行，数据错误,此行数据是表头"+rowList.toString());
                    }else{
                        pstm.setString(1, UUID.randomUUID().toString().replaceAll("-", ""));
                        pstm.setString(2,rowList.get(0));
                        pstm.setString(3,rowList.get(1));
                        pstm.setString(4,rowList.get(2));
                        pstm.setString(5,rowList.get(3));
                        pstm.setString(6,rowList.get(4));
                        pstm.setString(7,rowList.get(5));
                        pstm.setString(8,rowList.get(6));
                        pstm.setString(9,rowList.get(7));
                        pstm.setString(10,rowList.get(8));
                        pstm.setString(11,rowList.get(9));
                        pstm.setString(12,rowList.get(10));
                        pstm.setString(13,rowList.get(11));
                        pstm.setString(14,rowList.get(12));
                    
                        String xiang = rowList.get(13);
                        Double xiangDouble = getDouble(xiang);
                        pstm.setDouble(15,xiangDouble);
                    
                        String dun = rowList.get(14);
                        Double dunDouble = getDouble(dun);
                        pstm.setDouble(16,dunDouble);
                    
                        String xiaoshoushouru = rowList.get(15);
                        Double xiaoshoushouruDouble = getDouble(xiaoshoushouru);
                        pstm.setDouble(17,xiaoshoushouruDouble);
                    
                        String jingzhi = rowList.get(16);
                        Double jingzhiDouble = getDouble(jingzhi);
                        pstm.setDouble(18,jingzhiDouble);
                    
                        String shuie = rowList.get(17);
                        Double shuieDouble = getDouble(shuie);
                        pstm.setDouble(19,shuieDouble);
                    
                        String zhanlvjiajine = rowList.get(18);
                        Double zhanlvjiajineDouble = getDouble(zhanlvjiajine);
                        pstm.setDouble(20,zhanlvjiajineDouble);
                    
                        String zhekoujine = rowList.get(19);
                        Double zhekoujineDouble = getDouble(zhekoujine);
                        pstm.setDouble(21,zhekoujineDouble);
                    
                        String zhekoubaifenbi = rowList.get(20);
                        if(StringUtils.isNotBlank(zhekoubaifenbi) ){
                            if(zhekoubaifenbi.endsWith("%")){
                                zhekoubaifenbi = zhekoubaifenbi.replace("%", "");
                            }
                            if(zhekoubaifenbi.endsWith("-")){
                                zhekoubaifenbi = zhekoubaifenbi.replace("-", "");
                            }
                        
                        }
                        Double zhekoubaifenbiDouble =getDouble(zhekoubaifenbi);
                        pstm.setDouble(22,zhekoubaifenbiDouble);
                    
                        pstm.setString(23,rowList.get(21));
                    
                        String fapiaoriqi = rowList.get(22);
                        Date fapiaoriqiDate = getDate(fapiaoriqi);
                        pstm.setDate(24,fapiaoriqiDate);
                    
                        pstm.setString(25,rowList.get(23));
                    
                        String danjuriqi = rowList.get(24);
                        Date danjuriqiDate = getDate(danjuriqi);
                        pstm.setDate(26,danjuriqiDate);
                    
                        pstm.setString(27,rowList.get(25));
                        pstm.setString(28,rowList.get(26));
                        pstm.setString(29,"admin");
                    
                        pstm.setDate(30,new Date(new java.util.Date().getTime()));
                        pstm.setString(31,"admin");
                        pstm.setDate(32,new Date(new java.util.Date().getTime()));
                        pstm.setString(33,"企业销售数据：手工使用jdbc导入");
                        pstm.setString(34,"0");
                    
                        pstm.addBatch();
                    
                        if ((i % 10000) == 0){
                            pstm.executeBatch();
                            conn.commit();
                            System.out.println("批量执行完毕===>" + i + "条");
                        }
                    }
                }
            }
            System.out.println("数据导入结束，文件名："+fileName);
            pstm.executeBatch();
            conn.commit();
            Long endTime = System.currentTimeMillis();
            System.out.println("OK,用时：" + (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
        
            try {
                if(pstm!=null){
                    pstm.close();
                }
                if(conn!=null){
                    conn.close();
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        
        
        }
    
    }
}
