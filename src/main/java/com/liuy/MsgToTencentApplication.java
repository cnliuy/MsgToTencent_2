package com.liuy;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MsgToTencentApplication {

	public static void main(String[] args) {
		//SpringApplication.run(MsgToTencentApplication.class, args);
		
		//liuy add
		ApplicationContext ctx = SpringApplication.run(MsgToTencentApplication.class, args);
		//System.out.println("检查一下 Spring Boot 提供的  bean: ");
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			//System.out.println(beanName);
		}
		
	}
}
