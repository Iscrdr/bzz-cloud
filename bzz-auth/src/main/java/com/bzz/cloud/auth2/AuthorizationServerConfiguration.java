package com.bzz.cloud.auth2;

import com.bzz.cloud.service.MyUserDetailsService;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;


@Configuration
@EnableAuthorizationServer
@ConfigurationProperties(prefix = "spring.datasource")
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private AuthenticationManager authenticationManager;
 
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	
	@Bean
	RedisTokenStore redisTokenStore(){
		return new RedisTokenStore(redisConnectionFactory);
	}
	
	
	@Bean(name = "dataSource")
	public DataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/zuul-auth?useUnicode=true&characterEncoding=utf-8&useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
	
	//token存储数据库
//    @Bean
//    public JdbcTokenStore jdbcTokenStore(){
//        return new JdbcTokenStore(dataSource);
//    }
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(redisTokenStore());
		endpoints.userDetailsService(userDetailsService);
		endpoints.authenticationManager(this.authenticationManager);
		endpoints.tokenServices(defaultTokenServices());
	}
	
	/**
	 * <p>注意，自定义TokenServices的时候，需要设置@Primary，否则报错，</p>
	 * @return
	 */
	@Primary
	@Bean
	public DefaultTokenServices defaultTokenServices(){
		DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setTokenStore(redisTokenStore());
		tokenServices.setSupportRefreshToken(true);
		tokenServices.setClientDetailsService(clientDetails());
		tokenServices.setAccessTokenValiditySeconds(60*60*12); // token有效期自定义设置，默认12小时
		tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);//默认30天，这里修改
		return tokenServices;
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()");
		security .checkTokenAccess("isAuthenticated()");
		security.allowFormAuthenticationForClients();
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(clientDetails());
	}
	
	@Bean
	public ClientDetailsService clientDetails() {
		return new JdbcClientDetailsService(dataSource());
	}
	
	
	
}
