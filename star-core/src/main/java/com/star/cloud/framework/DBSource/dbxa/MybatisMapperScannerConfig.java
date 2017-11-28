package com.star.cloud.framework.DBSource.dbxa;

import com.star.cloud.framework.annotations.StarMyBatisDao;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @PACKAGE_NAME: com.star.cloud.framework.DBSource.dbxa
 * @CLASS_NAME: MybatisMapperScannerConfig
 * @Description:
 * @Author : yang qianli
 * @Date: 2017-11-26 5:32
 * @Modified by:
 * @Date:
 */
@Configuration
@AutoConfigureAfter(TransactionManagerConfig.class)
public class MybatisMapperScannerConfig {
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();

        //BasePackageScan
        mapperScannerConfigurer.setBasePackage("com.star.cloud.*.dao");
        //set sqlSessionFactory
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");

        mapperScannerConfigurer.setAnnotationClass(StarMyBatisDao.class);

        return mapperScannerConfigurer;
    }
}
