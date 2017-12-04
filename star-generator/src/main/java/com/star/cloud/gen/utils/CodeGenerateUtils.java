package com.star.cloud.gen.utils;

import com.star.cloud.gen.entity.ColumnGen;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.star.cloud.gen.utils
 * @CLASS_NAME: CodeGenerateUtils
 * @Author : yang qianli 
 * @Date: 2017-12-04 23:23
 * @Modified by:
 * @Date:
 * @Description:
 */
public class CodeGenerateUtils {

        private final String AUTHOR = "Ay";
        private final String CURRENT_DATE = "2017/05/03";
        private final String tableName = "sys_user";
        private final String packageName = "com.star.cloud.rbac";
        private final String tableAnnotation = "权限管理";
        private final String URL = "jdbc:mysql://192.168.132.99:3306/star_master";
        private final String USER = "root";
        private final String PASSWORD = "root";
        private final String DRIVER = "com.mysql.jdbc.Driver";
        private final String diskPath = "D:\\xss\\star-cloud\\star-generator\\src\\main\\resources\\";
        private final String changeTableName = replaceUnderLineAndUpperCase(tableName);

        public Connection getConnection() throws Exception{
            Class.forName(DRIVER);
            Connection connection= DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        }

        public static void main(String[] args) throws Exception{
            CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils();
            codeGenerateUtils.generate();
        }

        public void generate() throws Exception{
            try {
                Connection connection = getConnection();
                DatabaseMetaData databaseMetaData = connection.getMetaData();
                ResultSet resultSet = databaseMetaData.getColumns(null,"%", tableName,"%");
                //生成entity文件
                generateModelFile(resultSet);
                //生成dao
                //生成service
                //生成crontroller
                //生成ftl或jsp


                //生成Mapper文件
                //generateMapperFile(resultSet);
                //生成Dao文件
                generateDaoFile(resultSet);
                //生成Repository文件
               // generateRepositoryFile(resultSet);
                //生成服务层接口文件
                //generateServiceInterfaceFile(resultSet);
                //生成服务实现层文件
                generateServiceImplFile(resultSet);
                //生成Controller层文件
                generateControllerFile(resultSet);
                //生成DTO文件
                //generateDTOFile(resultSet);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }finally{

            }
        }

        private void generateModelFile(ResultSet resultSet) throws Exception{

            final String suffix = ".java";
            final String path = diskPath + changeTableName + suffix;
            final String templateName = "entity.ftl";
            File mapperFile = new File(path);
            List<ColumnGen> columnClassList = new ArrayList<ColumnGen>();
            ColumnGen columnGen = null;
            while(resultSet.next()){
                //id字段略过,create_by,create_date,update_by,update_date,remarks,del_flag
                if(resultSet.getString("COLUMN_NAME").equals("id")) continue;
                if(resultSet.getString("COLUMN_NAME").equals("update_by")) continue;
                if(resultSet.getString("COLUMN_NAME").equals("update_date")) continue;
                if(resultSet.getString("COLUMN_NAME").equals("create_by")) continue;
                if(resultSet.getString("COLUMN_NAME").equals("create_date")) continue;
                if(resultSet.getString("COLUMN_NAME").equals("remarks")) continue;
                if(resultSet.getString("COLUMN_NAME").equals("del_flag")) continue;
                columnGen = new ColumnGen();
                //获取字段名称
                columnGen.setColumnName(resultSet.getString("COLUMN_NAME"));
                //获取字段类型
                columnGen.setColumnType(resultSet.getString("TYPE_NAME"));
                //转换字段名称，如 sys_name 变成 SysName
                columnGen.setReflectedColumnName(replaceUnderLineAndUpperCase(resultSet.getString("COLUMN_NAME")));
                //字段在数据库的注释
                columnGen.setColumnComment(resultSet.getString("REMARKS"));


                columnClassList.add(columnGen);
            }
            Map<String,Object> dataMap = new HashMap<String,Object>();
            dataMap.put("entity_column",columnClassList);
            generateFileByTemplate(templateName,mapperFile,dataMap);

        }

        private void generateDTOFile(ResultSet resultSet) throws Exception{
            final String suffix = "Dao.java";
            final String path = "D://" + changeTableName + suffix;
            final String templateName = "dao.ftl";
            File mapperFile = new File(path);
            Map<String,Object> dataMap = new HashMap<String,Object>();
            generateFileByTemplate(templateName,mapperFile,dataMap);
        }

        private void generateControllerFile(ResultSet resultSet) throws Exception{
            final String suffix = "Controller.java";
            final String path = diskPath + changeTableName + suffix;
            final String templateName = "controller.ftl";
            File mapperFile = new File(path);
            Map<String,Object> dataMap = new HashMap<String,Object>();
            generateFileByTemplate(templateName,mapperFile,dataMap);
        }

        private void generateServiceImplFile(ResultSet resultSet) throws Exception{
            final String suffix = "Service.java";
            final String path = diskPath + changeTableName + suffix;
            final String templateName = "service.ftl";
            File mapperFile = new File(path);
            Map<String,Object> dataMap = new HashMap<String,Object>();
            generateFileByTemplate(templateName,mapperFile,dataMap);
        }

        /*private void generateServiceInterfaceFile(ResultSet resultSet) throws Exception{
            final String prefix = "I";
            final String suffix = "Service.java";
            final String path = diskPath + prefix + changeTableName + suffix;
            final String templateName = "ServiceInterface.ftl";
            File mapperFile = new File(path);
            Map<String,Object> dataMap = new HashMap<String,Object>();
            generateFileByTemplate(templateName,mapperFile,dataMap);
        }*/
/*
        private void generateRepositoryFile(ResultSet resultSet) throws Exception{
            final String suffix = "Repository.java";
            final String path = diskPath + changeTableName + suffix;
            final String templateName = "Repository.ftl";
            File mapperFile = new File(path);
            Map<String,Object> dataMap = new HashMap<String,Object>();
            generateFileByTemplate(templateName,mapperFile,dataMap);
        }*/

        private void generateDaoFile(ResultSet resultSet) throws Exception{
            final String suffix = "DAO.java";
            final String path = diskPath + changeTableName + suffix;
            final String templateName = "dao.ftl";
            File mapperFile = new File(path);
            Map<String,Object> dataMap = new HashMap<String,Object>();
            generateFileByTemplate(templateName,mapperFile,dataMap);

        }

        private void generateMapperFile(ResultSet resultSet) throws Exception{
            final String suffix = "Mapper.xml";
            final String path = diskPath + changeTableName + suffix;
            final String templateName = "mybatisMapper.ftl";
            File mapperFile = new File(path);
            Map<String,Object> dataMap = new HashMap<String,Object>();
            generateFileByTemplate(templateName,mapperFile,dataMap);

        }

        private void generateFileByTemplate(final String templateName,File file,Map<String,Object> dataMap) throws Exception{
            Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
            FileOutputStream fos = new FileOutputStream(file);
            dataMap.put("table_first_lower_name",replaceUnderLineAndLowerCase(tableName));//first char lowerCase
            dataMap.put("table_first_upper_name",changeTableName);//first char upperCase
            dataMap.put("table_lower_case_name",replaceLowerCase(tableName));//all char lowerCase
            dataMap.put("author",AUTHOR);
            dataMap.put("date",CURRENT_DATE);
            dataMap.put("package_name",packageName);
            //dataMap.put("table_annotation",tableAnnotation);
            Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
            template.process(dataMap,out);
        }

        public String replaceUnderLineAndUpperCase(String str){
            return StringUtils.capitalize(getStr(str));
        }

        public String replaceUnderLineAndLowerCase(String str){
            return  getStr(str);
        }
     /* @author: yang qianli
      * @date: 2017-12-04 23:21
      * @param:  * @param null
      * @return:
      * @description: Capitalize the underlined letters and remove the underline
      */
     public String getStr(String str){
         StringBuffer sb = new StringBuffer();
         sb.append(str);
         int count = sb.indexOf("_");
         while(count!=0){
             int num = sb.indexOf("_",count);
             count = num + 1;
             if(num != -1){
                 char ss = sb.charAt(count);
                 char ia = (char) (ss - 32);
                 sb.replace(count , count + 1,ia + "");
             }
         }
         String result = sb.toString().replaceAll("_","");
         return result ;
     }
     
    /* @author: yang qianli
     * @date: 2017-12-04 23:17
     * @param:  String str
     * @return: str
     * @description:  remove "_"
     */
    public String replaceLowerCase(String str){
        String result = null;
        if(StringUtils.isNotBlank(str)){
            result = str.toString().replaceAll("_","");
        }
        return result;
    }

}
