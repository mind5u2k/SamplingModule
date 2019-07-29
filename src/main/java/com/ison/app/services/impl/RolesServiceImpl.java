package com.ison.app.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ison.app.dao.RolesDAO;
import com.ison.app.model.RoleName;
import com.ison.app.model.Roles;
import com.ison.app.services.RolesService;

@Service
public class RolesServiceImpl implements RolesService {

	@Autowired
	RolesDAO rolesDAO;
	
	@Override
	public Optional<Roles> findByName(RoleName roleName) {
		return rolesDAO.findByName(roleName);
	}

}
