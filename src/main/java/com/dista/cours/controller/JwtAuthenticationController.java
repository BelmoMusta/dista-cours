package com.dista.cours.controller;

import com.dista.cours.entite.dto.UserDTO;
import com.dista.cours.security.jwt.JwtAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class JwtAuthenticationController {
	
	@Autowired
	private JwtAuthenticationService jwtAuthenticationService;
	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDTO authenticationRequest) {
		return ResponseEntity.ok(jwtAuthenticationService.authenticate(authenticationRequest));
	}
	
	@PostMapping(value = "/logout")
	public ResponseEntity<?> logout() {
		return ResponseEntity.ok().build();
	}
	
}