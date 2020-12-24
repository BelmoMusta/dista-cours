package com.dista.cours.service.impl;

import com.dista.cours.entite.Role;
import com.dista.cours.entite.User;
import com.dista.cours.entite.UserActivation;
import com.dista.cours.entite.dto.CustomizedValueDTO;
import com.dista.cours.entite.dto.RoleDTO;
import com.dista.cours.entite.dto.UserDTO;
import com.dista.cours.entite.dto.UserRoleDTO;
import com.dista.cours.exception.NotFoundException;
import com.dista.cours.repository.UserRepository;
import com.dista.cours.service.CustomizedValueService;
import com.dista.cours.service.RoleService;
import com.dista.cours.service.UserActivationService;
import com.dista.cours.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserActivationService userActivationService;
	
	@Autowired
	private CustomizedValueService customizedValueService;
	
	@Override
	public User loadUserByUsernameOrEmail(String username) {
		final EqualsSpecification<User> usernameSpec = new EqualsSpecification<>("username", username);
		final EqualsSpecification<User> emailSpec = new EqualsSpecification<>("email", username);
		return userRepository.findOne(usernameSpec.or(emailSpec))
				.orElse(null);
	}
	
	@Override
	public void createUser(UserDTO userRequest) {
		final User user = new User();
		user.setName(userRequest.getName());
		user.setLastName(userRequest.getLastName());
		user.setUsername(userRequest.getUsername());
		user.setEmail(userRequest.getEmail());
		user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setEnabled(false); // TO be activated
		user.setCredentialsNonExpired(true);
		final Role member = roleService.findByName("member");
		if (member != null) {
			user.setAuthorities(Collections.singleton(member));
		}
		userRepository.save(user);
		userActivationService.createActivationForUser(user);
	}
	
	@Override
	public void activate(String token) {
		UserActivation activation = userActivationService.findbyToken(token);
		if (activation != null) {
			User user = activation.getUser();
			user.setEnabled(true);
			userRepository.saveAndFlush(user);
			activation.setConsumed(true);
			userActivationService.delete(activation);
		}
	}
	
	@Override
	public void assignRole(UserRoleDTO userRoleDTO) {
		Role role = roleService.findByName(userRoleDTO.getRole());
		if (role == null) {
			role = roleService.createRole(userRoleDTO.getRole());
		}
		User user = userRepository.findById(userRoleDTO.getUserId())
				.orElseThrow(() -> new NotFoundException("User", userRoleDTO.getUserId()));
		Set<Role> authorities = user.getAuthorities();
		if (authorities == null) {
			authorities = new HashSet<>();
			user.setAuthorities(authorities);
		}
		if (!authorities.contains(role)) {
			authorities.add(role);
		}
		userRepository.saveAndFlush(user);
		
	}
	
	@Override
	public void revokeRole(UserRoleDTO userRoleDTO) {
		final Role role = roleService.findByName(userRoleDTO.getRole());
		if (role != null) {
			User user = userRepository.findById(userRoleDTO.getUserId())
					.orElse(null);
			if (user == null) {
				return;
			}
			Collection<Role> authorities = user.getAuthorities();
			if (authorities == null) {
				return;
			}
			authorities.remove(role);
			userRepository.saveAndFlush(user);
		}
	}
	@Override
	public List<RoleDTO> roles(Long id) {
		Set<Role> roles = userRepository.findById(id)
				.map(User::getAuthorities)
				.orElseThrow(() -> new NotFoundException("User", id));
		
		return roles.stream()
				.map(role -> {
					RoleDTO roleDTO = new RoleDTO();
					roleDTO.setAuthority(role.getAuthority());
					roleDTO.setEnabled(role.isEnabled());
					
					return roleDTO;
				})
				.collect(Collectors.toList());
	}
	
	@Override
	public List<CustomizedValueDTO> customizedProperties(Long id) {
		return customizedValueService.findFor("user", id);
	}
	
	@Override
	public void assignCustomizedValue(Long id, CustomizedValueDTO customizedValueDTO) {
		
		customizedValueService.createFor(id, "user", customizedValueDTO);
	}
	
	@Override
	public void assignCustomizedValueByPropertyId(Long id, Long propertyId, CustomizedValueDTO customizedValueDTO) {
		customizedValueService.createFor(id, propertyId, "user", customizedValueDTO);
		
	}
}
