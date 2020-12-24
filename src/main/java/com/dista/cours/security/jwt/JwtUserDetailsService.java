package com.dista.cours.security.jwt;

import com.dista.cours.exception.AuthenticationException;
import com.dista.cours.i18n.MessagesKeys;
import com.dista.cours.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		UserDetails user = userService.loadUserByUsernameOrEmail(username);
		if (user != null) {
			return user;
		} else {
			throw new AuthenticationException(MessagesKeys.AUTH_USER_NOT_FOUND.getValue(), username);
		}
	}
}
