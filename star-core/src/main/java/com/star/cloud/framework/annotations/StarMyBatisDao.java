package com.star.cloud.framework.annotations;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @CLASS_NAME: $
 * @PACKAGE_NAME: com.star.cloud.framework.annotations$
 * @Author : yang qian li
 * @Date: 2017-11-28 23:09
 * @Modified by:
 * @Date:
 * @Description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface StarMyBatisDao {

    /**
     * The value may indicate a suggestion for a logical component name,
     * to be turned into a Spring bean in case of an autodetected component.
     * @return the suggested component name, if any
     */
    String value() default "";
}
