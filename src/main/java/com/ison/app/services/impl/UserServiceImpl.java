package com.ison.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ison.app.dao.UserDAO;
import com.ison.app.model.User;
import com.ison.app.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDAO userDAO;
	
	@Override
	public User save(User user) throws Exception {
		return userDAO.save(user);
	}

}
