package com.star.cloud.core.DBSource;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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


        if (isReaderData(methodName)) {
            // set reader datasource mark
            StarDynamicDataSourceHolder.readerMark();
        } else {
            // set writer datasource mark
            StarDynamicDataSourceHolder.writerMark();
        }
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
