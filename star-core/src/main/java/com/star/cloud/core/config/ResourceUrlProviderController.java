package com.star.cloud.core.config;



import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import javax.annotation.Resource;

/**
 * @PACKAGE_NAME: com.star.cloud.core.config
 * @CLASS_NAME: ResourceUrlProviderController
 * @Author : yang qianli 
 * @Date: 2017-12-06 0:07
 * @Modified by: 
 * @Date:
 * @Description:
 */

@ControllerAdvice
public class ResourceUrlProviderController {

    @Resource
    private ResourceUrlProvider resourceUrlProvider;

    @ModelAttribute("urls")
    public ResourceUrlProvider urls() {
        return this.resourceUrlProvider;
    }
}
