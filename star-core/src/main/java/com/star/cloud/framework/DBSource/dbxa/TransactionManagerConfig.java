package com.star.cloud.framework.DBSource.dbxa;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @PACKAGE_NAME: com.star.cloud.framework.DBSource.dbxa
 * @CLASS_NAME: TransactionManagerConfig
 * @Description:
 * @Author : yang qianli
 * @Date: 2017-11-26 22:22
 * @Modified by:
 * @Date:
 */
@Configuration
@ComponentScan
@EnableTransactionManagement
public class TransactionManagerConfig implements TransactionManagementConfigurer   {

    @Resource
    private DataSource dataSource;


    @Bean(name = "transactionManager")
    public PlatformTransactionManager  annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }


}
