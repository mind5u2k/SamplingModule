package com.ison.app.dao;

import java.util.Optional;

import com.ison.app.model.RoleName;
import com.ison.app.model.Roles;

public interface RolesDAO {
	
	Optional<Roles> findByName(RoleName roleName);
}
