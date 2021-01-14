package com.dista.cours.service;

import com.dista.cours.entite.Parameter;
import com.dista.cours.entite.dto.ParameterDTO;
import com.dista.cours.entite.dto.UserParamDTO;

public interface ParameterService {
	
	Parameter findById(Long id);
	
	Parameter findByName(String name);
	
	Parameter createParameter(String name);
	
	Parameter createParameter(ParameterDTO name);
	
	void edit(Long id, ParameterDTO parameterDTO);
	
	ParameterDTO show(Long id);
	
	void assignValue(UserParamDTO userParamDTO);
	
	void assignValueToAUser(UserParamDTO userParamDTO);
}
