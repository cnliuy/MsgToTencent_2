package com.liuy.wx.tools;

import org.springframework.beans.factory.annotation.Value;

public class Singleton {
	
	//在自己内部定义自己的一个实例，只供内部调用 　　 
	private static Singleton instance = new Singleton();
	
	
	@Value("${wx.appId}")
	private String appid;	
	
	
	private Singleton() {
		//do something
		ApiConfig a = new ApiConfig();
		System.out.println("=="+appid+"--");
		a.setAppid(appid);
	}
	
	//这里提供了一个供外部访问本class的静态方法，可以直接访问 　　 
	public static Singleton getInstance(){ 
		return instance;
	}
}
