package com.dista.cours.security.jwt;

import com.dista.cours.dtos.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
	private final String jwttoken;
	private UserDTO user;
	
	public JwtResponse(String token) {
		jwttoken = token;
	}
}
