package com.dista.cours.service.impl;
import com.dista.cours.entite.ParameterType;

import com.dista.cours.entite.Parameter;
import com.dista.cours.entite.dto.ParameterDTO;
import com.dista.cours.entite.dto.UserParamDTO;
import com.dista.cours.exception.NotFoundException;
import com.dista.cours.repository.ParameterRepository;
import com.dista.cours.service.ParameterService;
import com.dista.cours.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParameterServiceImpl implements ParameterService {
	@Autowired
	private ParameterRepository parameterRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Parameter findById(Long id) {
		return parameterRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Parameter", id));
	}
	
	@Override
	public Parameter findByName(String name) {
		return parameterRepository.findByName(name)
				.orElseThrow(() -> new NotFoundException("Parameter", name));
	}
	
	@Override
	public Parameter createParameter(String name) {
		return null;
	}
	
	@Override
	public Parameter createParameter(ParameterDTO parameterDTO) {
		Parameter parameter = new Parameter();
		parameter.setName(parameterDTO.getName());
		parameter.setType(parameterDTO.getType());
		return parameterRepository.save(parameter);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void enable(Long id) {
		changeState(findById(id), true);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void enable(String name) {
		changeState(findByName(name), true);
	}
	
	private void changeState(Parameter parameter, boolean enabled) {
		parameter.setEnabled(enabled);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void disable(String name) {
		changeState(findByName(name), false);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void disable(Long id) {
		changeState(findById(id), false);
		
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void edit(Long id, ParameterDTO parameterDTO) {
		Parameter parameter = findById(id);
		parameter.setType(parameterDTO.getType());
		parameter.setName(parameterDTO.getName());
	}
	
	@Override
	public ParameterDTO show(Long id) {
		Parameter parameter = findById(id);
		ParameterDTO parameterDTO = new ParameterDTO();
		parameterDTO.setName(parameter.getName());
		parameterDTO.setType(parameter.getType());
		parameterDTO.setId(parameter.getId());
		return parameterDTO;
	}
	
	@Override
	public void assignValue(Long id, UserParamDTO userParamDTO) {
		final Parameter parameter = findById(id);
		
	}
}
