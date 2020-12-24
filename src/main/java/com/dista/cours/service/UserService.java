package com.dista.cours.service;

import com.dista.cours.entite.User;
import com.dista.cours.entite.dto.CustomizedValueDTO;
import com.dista.cours.entite.dto.RoleDTO;
import com.dista.cours.entite.dto.UserDTO;
import com.dista.cours.entite.dto.UserRoleDTO;

import java.util.List;

public interface UserService {
	
	User loadUserByUsernameOrEmail(String username);
	
	void createUser(UserDTO userRequest);
	
	void activate(String token);
	
	void assignRole(UserRoleDTO userRoleDTO);
	
	void revokeRole(UserRoleDTO userRoleDTO);
	
	List<RoleDTO> roles(Long id);
	
	List<CustomizedValueDTO> customizedProperties(Long id);
	
	void assignCustomizedValue(Long id, CustomizedValueDTO customizedValueDTO);
	
	void assignCustomizedValueByPropertyId(Long id, Long propertyId, CustomizedValueDTO customizedValueDTO);
}
