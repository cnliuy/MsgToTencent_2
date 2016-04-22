package com.liuy.wx.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
public class ApiConfigKit {
	
	@Autowired
	private ApiConfig apiconfig ;
	
	public ApiConfig getApiconfig() {
		return apiconfig;
	}
	
	public void setApiconfig(ApiConfig apiconfig) {
		this.apiconfig = apiconfig;
	}
	
	
	public ApiConfig gogetApiConfig(String appid ,String appsecret) {		
		apiconfig.setAppid(appid);		 
		apiconfig.setAppsecret(appsecret);		 
		return apiconfig;
	}


}
