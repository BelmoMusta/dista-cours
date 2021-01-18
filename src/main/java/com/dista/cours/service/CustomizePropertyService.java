package com.dista.cours.service;

import com.dista.cours.entite.CustomizedProperty;
import com.dista.cours.dtos.CustomizedPropertyDTO;

import java.util.Optional;

public interface CustomizePropertyService {
	void create(CustomizedPropertyDTO propertyDTO);
	
	void delete(String name);
	
	void delete(Long name);
	
	CustomizedPropertyDTO findByName(String name);
	
	Optional<CustomizedProperty> findEntityByName(String name);
	
	Optional<CustomizedProperty>  findById(Long propertyId);
}
