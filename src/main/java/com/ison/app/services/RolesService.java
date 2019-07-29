package com.ison.app.services;

import java.util.Optional;

import com.ison.app.model.RoleName;
import com.ison.app.model.Roles;

public interface RolesService {
	
	Optional<Roles> findByName(RoleName roleName);

}
