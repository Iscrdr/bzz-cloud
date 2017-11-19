package com.star.cloud.core.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * class_name: $
 * package: com.star.cloud.core.web$
 * desc: TODO
 * author : yang qian li
 * creat_time: 2017-11-19 22:09
 */
@RestController
public class TestController {

    @GetMapping(value = "/hello")
    public String getHello(){
        return "hello xiaoxingxing";
    }
}
