package com.liuy.config;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.liuy.wx.tools.ApiConfig;
import com.liuy.wx.tools.ApiConfigService;
import com.liuy.wx.tools.HttpKit;
import com.liuy.wx.tools.Mjsonkit;

@Component
public class ScheduledTasks {

	

		@Autowired
		private ApiConfigService apiConfigService;
		
		/**
		 * 基础支持: 获取access_token接口
		 * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=
		 *  &secret=
		 *
		 * 每隔 7000秒 执行一次
		 * 
		 * */
	 	@Scheduled(fixedRate = 7000000)
	    public void GetAccessToken() {
	 		System.out.println("定时任务类开始执行。。。。at "+ new Date());
	     	String url ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=";
			ApiConfig apiConfig = apiConfigService.gogetApiConfig() ;
			String token = apiConfig.getToken();
			//--调测时打开下面四行
			//--System.out.println("----appid=="+apiConfig.getAppid() );	
			//--System.out.println("----appsecret=="+apiConfig.getAppsecret() );
			//--System.out.println("----encodingaeskey=="+apiConfig.getEncodingaeskey() );		
			//--System.out.println("----token=="+token );		ApiConfig apiConfig = apiConfigService.gogetApiConfig() ;
			url = url+apiConfig.getAppid()+"&secret="+apiConfig.getAppsecret() ;
			String resp = HttpKit.get(url);
			ConfigConstant.accesstoken = Mjsonkit.GogetAccesstokenJSONContent(resp);
		   	System.out.println(ConfigConstant.accesstoken);
	    
	    	
	    }
	    
}
