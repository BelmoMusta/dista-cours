package com.dista.cours.service;

import com.dista.cours.entite.Role;

public interface RoleService {
	
	Role findByName(String authority);
	
	Role createRole(String authority);
	
	void enable(String authority);
	
	void disable(String authority);
}
