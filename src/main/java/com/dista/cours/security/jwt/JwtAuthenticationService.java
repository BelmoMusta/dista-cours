package com.dista.cours.security.jwt;

import com.dista.cours.entite.User;
import com.dista.cours.entite.dto.UserDTO;
import com.dista.cours.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthenticationService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	public JwtResponse authenticate(UserDTO authenticationRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			final User userDetails = (User) userDetailsService
					.loadUserByUsername(authenticationRequest.getUsername());
			
			final String token = jwtTokenUtil.generateToken(userDetails);
			final JwtResponse jwtResponse = new JwtResponse(token);
			final UserDTO authUserDTO = new UserDTO();
			authUserDTO.setEmail(userDetails.getEmail());
			authUserDTO.setName(userDetails.getName());
			authUserDTO.setLastName(userDetails.getLastName());
			authUserDTO.setUsername(userDetails.getUsername()); // use a mapper
			jwtResponse.setUser(authUserDTO);
			return jwtResponse;
			
		} catch (DisabledException e) {
			throw new AuthenticationException(String.format("the user %s is disabled",
					authenticationRequest.getUsername()));
		} catch (BadCredentialsException e) {
			throw new AuthenticationException("password and/or username are incorrect");
		}
	}
}