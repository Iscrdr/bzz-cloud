package com.bzz.cloud.service;

import org.slf4j.Logger;
		import org.slf4j.LoggerFactory;
		import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
		import org.springframework.security.core.userdetails.User;
		import org.springframework.security.core.userdetails.UserDetails;
		import org.springframework.security.core.userdetails.UserDetailsService;
		import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
		import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class MyUserDetailsService implements UserDetailsService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		logger.info("登录名为："+ username);
		
		return new User(username,new BCryptPasswordEncoder().encode("admin"), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}
}
