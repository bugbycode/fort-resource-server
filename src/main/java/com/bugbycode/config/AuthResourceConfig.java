package com.bugbycode.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
public class AuthResourceConfig extends ResourceServerConfigurerAdapter {
	
	@Value("${spring.resource.oauth.clientId}")
	private String clientId;
	
	@Value("${spring.resource.oauth.clientSecret}")
	private String clientSecret;
	
	@Value("${spring.resource.oauth.checkTokenUrl}")
	private String checkTokenUrl;
	
	@Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setClientId(clientId);
        tokenService.setClientSecret(clientSecret);
        tokenService.setCheckTokenEndpointUrl(checkTokenUrl);
        resources.tokenServices(tokenService);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        //资产管理
        .antMatchers("/resource/update").hasRole("RESOURCE_UPDATE") //修改资产权限
        .antMatchers("/resource/insert").hasRole("RESOURCE_INSERT") //添加资产权限
        .antMatchers("/resource/delete").hasRole("RESOURCE_DELETE")  //删除资产权限
        
        .antMatchers("/resource/query","/resource/queryById","/resource/queryByName",
        		"/resource/queryByIp","/resource/queryNetWork","/account/query")
        	.hasAnyRole("RESOURCE_QUERY","RESOURCE_UPDATE",
        		"RESOURCE_INSERT","RESOURCE_DELETE")
        	
        .antMatchers("/account/insert",
        		"/account/update","/account/delete").hasAnyRole("RESOURCE_UPDATE",
        		"RESOURCE_INSERT","RESOURCE_DELETE")
        
        //网络管理
        .antMatchers("/network/update").hasRole("NETWORK_UPDATE") //修改网络权限
        .antMatchers("/network/insert").hasRole("NETWORK_INSERT") //添加网络权限
        .antMatchers("/network/delete").hasRole("NETWORK_DELETE")  //删除网络权限
        
        .antMatchers("/network/query","/network/queryByName","/network/queryById")
        	.hasAnyRole("NETWORK_QUERY","NETWORK_UPDATE","NETWORK_INSERT","NETWORK_DELETE")
        ;
    }
}
