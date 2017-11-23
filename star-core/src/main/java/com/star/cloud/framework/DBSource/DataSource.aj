package com.star.cloud.framework.DBSource;

import java.lang.annotation.*;

/**
 * @CLASS_NAME: $CLASS_NAME$
 * @PACKAGE_NAME: com.star.cloud.core.DBSource$
 * @Author : yang qian li 
 * @Date: 2017-11-24 0:58
 * @Modified by: 
 * @Date:
 *
 * @Description:
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface DataSource {
    /** DataSource Name */
    DataSourceEnum value();
}