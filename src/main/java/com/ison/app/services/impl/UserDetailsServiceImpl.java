package com.ison.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ison.app.dao.UserDAO;
import com.ison.app.model.User;
 
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    UserDAO userDAO;
 
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
    	 User user = null;
		try {
			user = userDAO.findByUsername(username).orElseThrow(() -> 
			                new UsernameNotFoundException("User Not Found with -> username or email : " + username)
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return UserPrinciple.build(user);
    }
    
    public Boolean existsByUsername(String username) throws Exception {
    	return userDAO.existsByUsername(username);
    }
    
    public Boolean existsByEmail(String email) throws Exception {
    	return userDAO.existsByEmail(email);
    }
    
}
