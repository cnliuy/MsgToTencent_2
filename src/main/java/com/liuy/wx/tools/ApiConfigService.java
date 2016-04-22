package com.liuy.wx.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="wx", ignoreUnknownFields = false)
public class ApiConfigService {
	
	private String appId = null;
	private String appSecret = null;
	private String token = null;
	private String encodingAesKey = null;
	private boolean messageEncrypt = false;	// 消息加密与否
	@Autowired
	private ApiConfig apiconfig ;	
	

	public ApiConfig getApiconfig() {
		return apiconfig;
	}
	
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEncodingAesKey() {
		return encodingAesKey;
	}

	public void setEncodingAesKey(String encodingAesKey) {
		this.encodingAesKey = encodingAesKey;
	}

	public boolean isMessageEncrypt() {
		return messageEncrypt;
	}

	public void setMessageEncrypt(boolean messageEncrypt) {
		this.messageEncrypt = messageEncrypt;
	}
	

	public void setApiconfig(ApiConfig apiconfig) {
		this.apiconfig = apiconfig;
	}
	
	public ApiConfig gogetApiConfig( ) {		
		apiconfig.setAppid(appId); 
		apiconfig.setAppsecret(appSecret);
		apiconfig.setEncodingaeskey(encodingAesKey);
		apiconfig.setMessageencrypt(messageEncrypt);
		apiconfig.setToken(token);		
		return apiconfig;
	}
}













