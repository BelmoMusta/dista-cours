package com.dista.cours.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cours")
@CrossOrigin
public class CoursController {
	
	
	@GetMapping()
	public ResponseEntity<?> all() {
		return ResponseEntity.ok().build();
	}
}

