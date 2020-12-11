package com.dista.cours.controller;

import com.dista.cours.security.annotation.IsAdmin;
import com.dista.cours.service.CustomizePropertyService;
import com.dista.cours.entite.dto.CustomizedPropertyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin
@IsAdmin
public class CustomizedPropertiesController {
	
	@Autowired
	private CustomizePropertyService customizePropertyService;
	
	@PostMapping("/create")
	public void create(@RequestBody CustomizedPropertyDTO propertyDTO) {
		customizePropertyService.create(propertyDTO);
	}
	
	@DeleteMapping("/{name}/remove")
	public void delete(@PathVariable String name) {
		customizePropertyService.delete(name);
	}
	@GetMapping("/")
	public ResponseEntity<CustomizedPropertyDTO> create(String name) {
		return ResponseEntity.ok(customizePropertyService.findByName(name));
	}
}
