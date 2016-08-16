package com.liuy.web;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

	//@Value("${application.message:Hello World}")
	//private String message = "Hello World";
	
	@Value("${application.message}")
	private String message ;

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		String msg2 = "lh";
		model.put("time", new Date());
		model.put("message", this.message);
		model.put("msg2", msg2);
		System.out.println(this.message);
		return "welcome";
	}

	@RequestMapping("/foo")
	public String foo(Map<String, Object> model) {
		throw new RuntimeException("Foo");
	}

}
