package com.star.cloud.framework.DBSource;

/**
 * @CLASS_NAME: $
 * @PACKAGE_NAME: com.star.cloud.core.DBSource$
 * @Author : yang qian li
 * @Date: 2017-11-24 0:59
 * @Modified by:
 * @Date:
 * @Description:
 */

public enum DataSourceEnum {

    MASTER("master", "master-database"), // master-database

    SLAVE("slave", "slave-database");// slave-database


    private DataSourceEnum(String key, String description) {
        this.key = key;
        this.description = description;
    }

    /** The key for the data source (for key use in the data source Map in the Spring configuration file)*/
    private String key;

    /** description */
    private String description;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
