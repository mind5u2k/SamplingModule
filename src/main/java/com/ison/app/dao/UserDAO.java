package com.ison.app.dao;

import java.util.Optional;

import com.ison.app.model.User;

public interface UserDAO {
	
	Optional<User> findByUsername(String username) throws Exception;
	
	Boolean existsByUsername(String username) throws Exception;
	
    Boolean existsByEmail(String email) throws Exception;
    
    public User save(User user) throws Exception;
}
