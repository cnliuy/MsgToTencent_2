package com.liuy.wx.tools;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


/**
 * 存放 Weixin 服务号需要用到的各个参数
 */
//@Configuration
//									  "classpath:config/wisely.properties"
//,locations = "classpath:cp0.properties"
//@ConfigurationProperties(prefix="cp")
@Component
public class ApiConfig {
	

	private String appid;	

	private String appsecret;

	private String token;	

	private String encodingaeskey;	

	private boolean messageencrypt;// 消息加密与否
	
	public ApiConfig() {
		super();
	}

	public String getAppid() {
		return appid;
	}	
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getAppsecret() {
		return appsecret;
	}
	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEncodingaeskey() {
		return encodingaeskey;
	}
	public void setEncodingaeskey(String encodingaeskey) {
		this.encodingaeskey = encodingaeskey;
	}
	public boolean isMessageencrypt() {
		return messageencrypt;
	}
	/**
	 *  是否对消息进行加密，对应于微信平台的消息加解密方式：
	 *  1：true进行加密且必须配置 encodingAesKey
	 *  2：false采用明文模式，同时也支持混合模式
	 */
	public void setMessageencrypt(boolean messageencrypt) {
		this.messageencrypt = messageencrypt;
	}
	
	

}


