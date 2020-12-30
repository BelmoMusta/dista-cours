package com.dista.cours.controller;

import com.dista.cours.entite.dto.ParameterDTO;
import com.dista.cours.entite.dto.UserParamDTO;
import com.dista.cours.security.annotation.IsAdmin;
import com.dista.cours.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parameter")
@CrossOrigin
public class ParametersController {
	@Autowired
	private ParameterService parameterService;
	
	@GetMapping("/{id}")
	@IsAdmin
	public ResponseEntity<ParameterDTO> get(@PathVariable Long id) {
		return ResponseEntity.ok(parameterService.show(id));
	}
	
	@PostMapping("/create")
	@IsAdmin
	public void create(@RequestBody ParameterDTO parameterDTO) {
		parameterService.createParameter(parameterDTO);
	}
	@PutMapping("/{id}/enable")
	@IsAdmin
	public void enable(@PathVariable Long id) {
		parameterService.enable(id);
	}
	
	@PutMapping("/{id}/disable")
	@IsAdmin
	public void disable(@PathVariable Long id) {
		parameterService.disable(id);
	}
	
	@PutMapping("/{id}/edit")
	@IsAdmin
	public void edit(@PathVariable Long id, @RequestBody ParameterDTO parameterDTO ) {
		parameterService.edit(id, parameterDTO);
	}
	@PutMapping("/{id}/assign-value")
	@IsAdmin
	public void assignValue(@PathVariable Long id, @RequestBody UserParamDTO userParamDTO ) {
		parameterService.assignValue(id, userParamDTO);
	}
}
