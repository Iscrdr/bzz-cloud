package com.star.cloud.core.DBSource;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @CLASS_NAME: $
 * @PACKAGE_NAME: com.star.cloud.core.DBSource$
 * @Author : yang qian li
 * @Date: 2017-11-21 1:27
 * @Modified by:
 * @Date:
 * @Description:
 */
@Aspect
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class StarDataSourceAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.star.cloud.core.service..*.*(..))")
    public void aspect() {
    }

    /* @author: yang qianli
     * @date: 2017-11-21 1:30
     * @param:  * @param null
     * @return:   void
     * @description: Face the serivice section and execute before entering the serivice method
     */
    @Before("aspect()")
    public  void before(JoinPoint point) {

        // Gets the current method of execution
        String methodName = point.getSignature().getName();

        logger.info("Before the data source switch,datasource:"+StarDynamicDataSourceHolder.getDataSourceKey());
        if (isReaderData(methodName)) {
            // set reader datasource mark
            StarDynamicDataSourceHolder.readerMark();
        } else {
            // set writer datasource mark
            StarDynamicDataSourceHolder.writerMark();
        }
        logger.info("After the data source switch,methodName:"+methodName+"datasource:"+StarDynamicDataSourceHolder.getDataSourceKey());
    }


    /* @author: yang qianli
     * @date: 2017-11-21 1:33
     * @param:  String methodName
     * @return: boolean
     * @description: Decide whether to read or not
     */

    private Boolean isReaderData(String methodName) {
        // 方法名以query、find、get开头的方法名走从库
        return StringUtils.startsWithAny(methodName, "query", "find", "get","select");
    }
    /* @author: yang qianli
     * @date: 2017-11-21 1:48
     * @param:  * @param null
     * @return:
     * @description: aspect() after clear StarDynamicDataSourceHolder
     */
    @After("aspect()")
    public void after(JoinPoint point) {
        StarDynamicDataSourceHolder.clear();
    }
}
