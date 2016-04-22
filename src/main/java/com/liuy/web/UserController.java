package com.liuy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuy.dao.UserDao;
import com.liuy.domain.User;

@Controller
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * 访问链接：
	 * 
	 * http://127.0.0.1:8080/get-by-email?email=liuy@gmail.com
	 * 
	 * SQL数据：
	 * 
	 * 	INSERT INTO users(id, email, name) VALUES (1, 'liuy@gmail.com', '大牛1');
	 * 	INSERT INTO users(id, email, name) VALUES (2, 'liuy2@gmail.com', '大牛2');
	 * 
	 * 	参考地址：
	 * 	{@link} http://www.tuicool.com/articles/zEz2QrY
	 * 
	 * 	Spring Boot:在Spring Boot中使用Mysql和JPA
	 * 	(备用地址:
	 * 		{@link} http://www.qiyadeng.com/post/spring-boot-mysql-jpa?utm_source=tuicool&utm_medium=referral
	 * 	)
	 * 
	 * 	源码参考地址：  
	 * 	{@link} https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-hibernate4
	 *  spring-boot-sample-hibernate4  源码
	 * 
	 * */
    @RequestMapping("/get-by-email")
    @ResponseBody
    public String getByEmail(String email) {
    	
    	String userId;
    	User user = userDao.findByEmail(email);
    	if (user != null) {
    		userId = String.valueOf(user.getId());
    		return "The user "+user.getName() +" 's id is: " + userId;
    	}
    	return "user " + email + " is not exist.";
    	
    }
  }