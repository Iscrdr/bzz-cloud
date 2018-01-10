package com.star.cloud.rbac.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * @PACKAGE_NAME: com.star.cloud.rbac.security
 * @CLASS_NAME: WebSecurityConfig
 * @Author : yang qianli 
 * @Date: 2017-12-09 15:00
 * @Modified by: 
 * @Date:
 * @Description:
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {





    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login") //login can be accessed
//                .failureUrl("/login?error") //login failed can be accessed
//                .permitAll()
//                .and()
//                .logout().permitAll(); //logout can be accessed

    }


}
