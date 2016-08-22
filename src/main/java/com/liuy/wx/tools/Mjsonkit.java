package com.liuy.wx.tools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Mjsonkit {
	/**
     * 将Json对象转换成Map
     * 
     * @param jsonObject
     *            json对象
     * @return Map对象
     * @throws JSONException
     */
    public static Map toMap(String jsonString) throws JSONException {

        JSONObject jsonObject = new JSONObject(jsonString);        
        Map result = new HashMap();
        Iterator iterator = jsonObject.keys();
        String key = null;
        String value = null;
        
        while (iterator.hasNext()) {
            key = (String) iterator.next();
            value = jsonObject.getString(key);
            result.put(key, value);
        }
        return result;

    }
    
    
    public static String GogetAccesstokenJSONContent(String JSONText){  
        JSONTokener jsonTokener = new JSONTokener(JSONText);   
        JSONObject JSONObject;  
        String access_token = null;  
        int expires_in = 0;  
        String phone = null;  
        try {  
        	JSONObject = (JSONObject) jsonTokener.nextValue();  
        	access_token = JSONObject.getString("access_token");  
        	expires_in = JSONObject.getInt("expires_in");  
        } catch (JSONException e) {  
            e.printStackTrace();  
        }  
        return access_token;  
    }  
}
