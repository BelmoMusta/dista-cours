package com.dista.cours.service;

import com.dista.cours.entite.Parameter;
import com.dista.cours.dtos.ParameterDTO;
import com.dista.cours.dtos.ParameterValueDTO;
import com.dista.cours.dtos.UserParamDTO;

import java.util.List;

public interface ParameterService {
	
	Parameter findById(Long id);
	
	Parameter findByName(String name);
	
	Parameter createParameter(String name);
	
	Parameter createParameter(ParameterDTO name);
	
	void edit(Long id, ParameterDTO parameterDTO);
	
	ParameterDTO show(Long id);
	
	void assignValue(UserParamDTO userParamDTO);
	
	void assignValueToAUser(UserParamDTO userParamDTO);
	
	List<ParameterValueDTO> getAll();
}
