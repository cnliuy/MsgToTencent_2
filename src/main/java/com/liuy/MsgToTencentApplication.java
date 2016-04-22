package com.liuy;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.liuy.wx.tools.ApiConfig;
import com.liuy.wx.tools.ApiConfigKitbb;
import com.liuy.wx.tools.ApiConfigService;


/**
 * 参考
 * spring-boot/spring-boot-samples/spring-boot-sample-integration 项目
 * 
 * 	https://github.com/spring-projects/spring-boot/blob/master/spring-boot-samples%2Fspring-boot-sample-integration%2Fsrc%2Fmain%2Fjava%2Fsample%2Fintegration%2FSampleIntegrationApplication.java
 * 
 * spring-boot/spring-boot-samples/spring-boot-sample-metrics-opentsdb
 *  5* 有用
 * 
 * 	https://github.com/spring-projects/spring-boot/blob/master/spring-boot-samples%2Fspring-boot-sample-metrics-opentsdb%2Fsrc%2Fmain%2Fjava%2Fsample%2Fmetrics%2Fopentsdb%2FHelloWorldService.java
 * 
 * */

//@EnableAutoConfiguration
//@ComponentScan
//@PropertySource("cp0.properties")
//@EnableConfigurationProperties
//@EnableConfigurationProperties({ApiConfig.class}) extends SpringBootServletInitializer
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan

@SpringBootApplication
//@EnableConfigurationProperties({WiselySettings.class,Wisely2Settings.class})  
//@EnableConfigurationProperties({ApiConfigService.class})  
public class MsgToTencentApplication{	
	
	
	
	public static void main(String[] args) {		
		//SpringApplication.run(MsgToTencentApplication.class, args);
		
		//liuy add
		ApplicationContext ctx = SpringApplication.run(MsgToTencentApplication.class, args);
		//System.out.println("检查一下 Spring Boot 提供的  bean: ");		
		//ctx.getBean(ApiConfigKit.class).setApiConfig("123"); // <-- here		
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			//System.out.println(beanName);
		}
		
	}
	

	
	
}
