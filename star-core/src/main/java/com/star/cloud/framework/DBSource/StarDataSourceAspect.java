package com.star.cloud.framework.DBSource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
/**
 * @CLASS_NAME: $
 * @PACKAGE_NAME: com.star.cloud.core.DBSource$
 * @Author : yang qian li
 * @Date: 2017-11-21 1:27
 * @Modified by:
 * @Date:
 * @Description:
 */

public class StarDataSourceAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /* @author: yang qianli
     * @date: 2017-11-21 1:30
     * @param:  * @param null
     * @return:   void
     * @description: Face the serivice section and execute before entering the serivice method
     */
    public  void before(JoinPoint point) {

        // Gets the current method of execution
        //String methodName = point.getSignature().getName();

        // target class
        Object target = point.getTarget();
        // The Class bytecode
        Class<?> targetClass = target.getClass();
        // The signature of the target method
        Signature signature = point.getSignature();
        // Target method name
        String methodName = signature.getName();
        // Array of parameter types for the target method
        Class<?>[] parameterTypes = ((MethodSignature) signature).getMethod().getParameterTypes();


        logger.info("Before the data source switch,DataSourceKey:"
                + StarDynamicDataSourceHolder.getDataSourceKey()
                + ",targetClass.getName():"
                + targetClass.getName()+","
                + methodName);
        try {
            DataSource dataSource;
            dataSource = null;
            Method method = targetClass.getMethod(methodName, parameterTypes);

            if (method != null && method.isAnnotationPresent(DataSource.class)) {
                // Get datasource conf by method
                dataSource = method.getAnnotation(DataSource.class);
            }else if (targetClass.isAnnotationPresent(DataSource.class)) {
                // Get datasource conf by class
                dataSource = targetClass.getAnnotation(DataSource.class);
            }
            if (dataSource != null) {
                // set default datasource
                DataSourceEnum dataSourceName = dataSource.value();
                String key = dataSourceName.getKey();
                StarDynamicDataSourceHolder.clear();//clear
                StarDynamicDataSourceHolder.putDataSourceKey(key);
            }
        } catch (Exception e) {

            logger.error("dataSource swith,failed: the data source switch,DataSourceKey:"
                    + StarDynamicDataSourceHolder.getDataSourceKey()
                    + ",targetClass.getName():"
                    + targetClass.getName()+","
                    + methodName);
            logger.error("dataSource swith,failed,",e);
        }
        logger.info("After the data source switch,DataSourceKey:"
                + StarDynamicDataSourceHolder.getDataSourceKey()
                + ",targetClass.getName():"
                + targetClass.getName()+","
                + methodName);
    }



}
