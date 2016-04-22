package com.liuy.wx.tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.web.SpringBootServletInitializer;


public class ApiConfigKitbb {	
	
	@Autowired
	private ApiConfigService apiConfigService;
	
	
	private static String appId ;
	
	public  static	ApiConfig getApiConfig(){
		ApiConfig a = new ApiConfig();
		return a ;
	}
	
	public  static	void setApiConfig(String s){
		appId =s ;
		 
	}

}