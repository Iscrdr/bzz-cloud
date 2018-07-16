package com.bzz.cloud.ribbon.config;

import com.google.common.base.Strings;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.StaticServerList;
import org.springframework.context.annotation.Bean;


@RibbonClient(name = "rbacRibbonConfig",configuration = RbacRibbonConfig.class)
public class RbacRibbonConfig {
	String listOfServers = "http://127.0.0.1:8761,http://127.0.0.1:8762";
	
	@Bean
	public ServerList<Server> ribbonServerList() {
		Server[] servers = null;
		if (!Strings.isNullOrEmpty(listOfServers)) {
			servers = new Server[2];
			String[] split = listOfServers.split(",");
			for (int i = 0;i<split.length;i++){
				servers[i] = new Server(split[i].trim());
			}
		}
		return new StaticServerList<Server>(servers);
	}
}
