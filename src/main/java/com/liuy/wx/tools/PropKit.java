package com.liuy.wx.tools;

public  class PropKit {
	

	public static Boolean getBoolean(String key) {
		String value =  key ;
		return (value != null) ? Boolean.parseBoolean(value) : null;
	}
	
	public static Boolean getBoolean(String key, Boolean defaultValue) {
		String value =  key;
		return (value != null) ? Boolean.parseBoolean(value) : defaultValue;
	}
}
