package com.dista.cours.service;


import com.dista.cours.entite.dto.CustomizedValueDTO;
import com.dista.cours.entite.CustomizedValue;

import java.util.List;

public interface CustomizedValueService {
	void create(CustomizedValueDTO propertyDTO);
	
	void delete(String name);
	
	void delete(Long name);
	
	CustomizedValueDTO findByName(String name);
	
	List<CustomizedValueDTO> findFor(String tableName, Long id);
	
	CustomizedValue findFor(String valueName, String tableName, Long id);
	
	void createFor(Long id, String user, CustomizedValueDTO customizedValueDTO);
}
