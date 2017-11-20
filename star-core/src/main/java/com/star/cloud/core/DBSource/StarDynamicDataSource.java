package com.star.cloud.core.DBSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @PACKAGE_NAME: com.star.cloud.core.DBSource
 * @CLASS_NAME: StarDynamicDataSource
 * @Description:
 * @Author : yang qianli 
 * @Date: 2017-11-21 1:15
 * @Modified by: 
 * @Date:
 */

public class StarDynamicDataSource  extends AbstractRoutingDataSource {

    
    @Override
    protected Object determineCurrentLookupKey() {
        return StarDynamicDataSourceHolder.getDataSourceKey();
    }
}
