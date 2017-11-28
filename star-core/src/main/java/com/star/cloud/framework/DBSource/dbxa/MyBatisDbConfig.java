package com.star.cloud.framework.DBSource.dbxa;


import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;


import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @PACKAGE_NAME: com.star.cloud.framework.DBSource.dbxa
 * @CLASS_NAME: MyBatisDbConfig
 * @Description:
 * @Author : yang qianli
 * @Date: 2017-11-26 21:49
 * @Modified by:
 * @Date:
 */
@Configuration
public class MyBatisDbConfig implements EnvironmentAware {

    private Logger logger = LoggerFactory.getLogger(MyBatisDbConfig.class);

    private RelaxedPropertyResolver propertyResolver;

    private String driveClassName;
    private String url;
    private String userName;
    private String password;
    private String xmlLocation;
    private String typeAliasesPackage;
    /*
     * JDBC POOL PARAMS
     */
    private String filters;
    private String maxActive;
    private String initialSize;
    private String maxWait;
    private String minIdle;
    private String timeBetweenEvictionRunsMillis;
    private String minEvictableIdleTimeMillis;
    private String validationQuery;
    private String testWhileIdle;
    private String testOnBorrow;
    private String testOnReturn;
    private String poolPreparedStatements;
    private String maxOpenPreparedStatements;

    /* @author: yang qianli
     * @date: 2017-11-28 21:07
     * @param:  Environment environment
     * @return:   void
     * @description: database.properties load
     */
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, null);
        this.url = propertyResolver.getProperty("jdbc.pool.mysql.url");
        this.userName= propertyResolver.getProperty("jdbc.pool.mysql.username");
        this.password = propertyResolver.getProperty("jdbc.pool.mysql.password");
        this.driveClassName = propertyResolver.getProperty("jdbc.pool.mysql.driver-class-name");
        this.filters = propertyResolver.getProperty("jdbc.pool.mysql.filters");
        this.maxActive = propertyResolver.getProperty("jdbc.pool.mysql.maxActive");
        this.initialSize = propertyResolver.getProperty("jdbc.pool.mysql.initialSize");
        this.maxWait = propertyResolver.getProperty("jdbc.pool.mysql.maxWait");
        this.minIdle = propertyResolver.getProperty("jdbc.pool.mysql.minIdle");
        this.timeBetweenEvictionRunsMillis = propertyResolver.getProperty("jdbc.pool.mysql.timeBetweenEvictionRunsMillis");
        this.minEvictableIdleTimeMillis = propertyResolver.getProperty("jdbc.pool.mysql.minEvictableIdleTimeMillis");
        this.validationQuery = propertyResolver.getProperty("jdbc.pool.mysql.validationQuery");
        this.testWhileIdle = propertyResolver.getProperty("jdbc.pool.mysql.testWhileIdle");
        this.testOnBorrow = propertyResolver.getProperty("jdbc.pool.mysql.testOnBorrow");
        this.testOnReturn = propertyResolver.getProperty("jdbc.pool.mysql.testOnReturn");
        this.poolPreparedStatements = propertyResolver.getProperty("jdbc.pool.mysql.poolPreparedStatements");
        this.maxOpenPreparedStatements = propertyResolver.getProperty("jdbc.pool.mysql.maxOpenPreparedStatements");
       this.typeAliasesPackage = propertyResolver.getProperty("mybatis.basepackage");
        this.xmlLocation = propertyResolver.getProperty("mybatis.xmlLocation");
    }


    @Bean(value="dataSource")
    @Primary
    public DataSource druidDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName(StringUtils.isNotBlank(driveClassName)?driveClassName:"com.mysql.jdbc.Driver");
        druidDataSource.setMaxActive(StringUtils.isNotBlank(maxActive)? Integer.parseInt(maxActive):10);
        druidDataSource.setInitialSize(StringUtils.isNotBlank(initialSize)? Integer.parseInt(initialSize):1);
        druidDataSource.setMaxWait(StringUtils.isNotBlank(maxWait)? Integer.parseInt(maxWait):60000);
        druidDataSource.setMinIdle(StringUtils.isNotBlank(minIdle)? Integer.parseInt(minIdle):3);
        druidDataSource.setTimeBetweenEvictionRunsMillis(StringUtils.isNotBlank(timeBetweenEvictionRunsMillis)?
                Integer.parseInt(timeBetweenEvictionRunsMillis):60000);
        druidDataSource.setMinEvictableIdleTimeMillis(StringUtils.isNotBlank(minEvictableIdleTimeMillis)?
                Integer.parseInt(minEvictableIdleTimeMillis):300000);
        druidDataSource.setValidationQuery(StringUtils.isNotBlank(validationQuery)?validationQuery:"select 'x'");
        druidDataSource.setTestWhileIdle(!StringUtils.isNotBlank(testWhileIdle) || Boolean.parseBoolean(testWhileIdle));
        druidDataSource.setTestOnBorrow(StringUtils.isNotBlank(testOnBorrow) && Boolean.parseBoolean(testOnBorrow));
        druidDataSource.setTestOnReturn(StringUtils.isNotBlank(testOnReturn) && Boolean.parseBoolean(testOnReturn));
        druidDataSource.setPoolPreparedStatements(!StringUtils.isNotBlank(poolPreparedStatements) || Boolean.parseBoolean(poolPreparedStatements));
        druidDataSource.setMaxOpenPreparedStatements(StringUtils.isNotBlank(maxOpenPreparedStatements)? Integer.parseInt(maxOpenPreparedStatements):20);

        try {
            druidDataSource.setFilters(StringUtils.isNotBlank(filters)?filters:"stat, wall");
        } catch (SQLException e) {
            logger.info("==============================Load DataSource failed ==============================");
            e.printStackTrace();
        }
        logger.info("==============================Load DataSource success ==============================");
        return druidDataSource;
    }


    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(druidDataSource());
       if(StringUtils.isNotBlank(typeAliasesPackage)){
            bean.setTypeAliasesPackage(typeAliasesPackage);

        }
        bean.setTypeAliasesPackage("com.star.cloud.*.dao");
        //page plugin
        PageInterceptor pageInterceptor = new PageInterceptor();

        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageInterceptor.setProperties(properties);
        //add xml path
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Interceptor[] plugins =  new Interceptor[]{pageInterceptor};
        bean.setPlugins(plugins);
        logger.info("============================== SqlSessionFactoryBean Create  success ==============================");

        logger.info("============================== SqlSessionFactoryBean Create  success ==============================");
        logger.info("============================== SqlSessionFactoryBean Create  success =============================="); logger.info("============================== SqlSessionFactoryBean Create  success =============================="); logger.info("============================== SqlSessionFactoryBean Create  success =============================="); logger.info("============================== SqlSessionFactoryBean Create  success =============================="); logger.info("============================== SqlSessionFactoryBean Create  success ==============================");






       System.out.println(xmlLocation);

        System.out.println(typeAliasesPackage);
        try {
            bean.setMapperLocations(resolver.getResources(xmlLocation));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        logger.info("============================== SqlSessionTemplate Create  success ==============================");
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
