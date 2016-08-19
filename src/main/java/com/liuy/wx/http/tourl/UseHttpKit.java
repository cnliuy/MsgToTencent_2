package com.liuy.wx.http.tourl;

import org.springframework.stereotype.Component;

import com.liuy.wx.tools.HttpKit;

@Component
public class UseHttpKit {
	
	
	public String post(String url, String data) {
		return HttpKit.post(url, data);
	}

	public static void main__bak(String[] args) {
		String access_token = "uuyTcGsQPP5Rzg3V-5vo14FhmjCpoBaMgS8NcmTb4SYHhHZF_ratvbLr-o9xq-gHJTRYrFzLFo-ziDm-nUMapiNODla6rp44mlA54ssZit96mp11WzdbE0RnldPwoFSfVLPbAGARHL";
		String url= "https://api.weixin.qq.com/customservice/kfaccount/add?access_token="+access_token ;
		String data  = 
				"{"
						+ "\"kf_account\" : \"闫旭\","
						+ "\"nickname\" : \"客服6\","
						+ "\"password\" : \"passwd6\","
				+ "}";
		System.out.println(data);
		System.out.println("-----------------------------------------------------");
		String repson = HttpKit.post(url, data);
		System.out.println(repson);
	}

}
