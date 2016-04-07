package com.liuy.dao;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import com.liuy.domain.User;

@Transactional
public interface UserDao extends CrudRepository<User, Long> {
	
	  public User findByEmail(String email);
} 

