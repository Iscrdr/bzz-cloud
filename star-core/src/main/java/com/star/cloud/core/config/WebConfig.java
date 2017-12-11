package com.star.cloud.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @CLASS_NAME: $
 * @PACKAGE_NAME: com.star.cloud.core.webconfig$
 * @Author : yang qian li
 * @Date: 2017-12-05 23:28
 * @Modified by:
 * @Date:
 * @Description:
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/rbac/**").addResourceLocations("classpath:/rbac/");
    }
}
