package com.dista.cours.service.impl;

import com.dista.cours.dtos.ParameterDTO;
import com.dista.cours.dtos.ParameterValueDTO;
import com.dista.cours.dtos.UserParamDTO;
import com.dista.cours.entite.Parameter;
import com.dista.cours.entite.ParameterValue;
import com.dista.cours.entite.User;
import com.dista.cours.entite.UserParameter;



import com.dista.cours.exception.NotFoundException;
import com.dista.cours.mappers.ParamMapper;
import com.dista.cours.repository.ParameterRepository;
import com.dista.cours.repository.ParameterValueRepository;
import com.dista.cours.repository.UserParameterRepository;
import com.dista.cours.service.ParameterService;
import com.dista.cours.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParameterServiceImpl implements ParameterService {
	@Autowired
	private ParameterRepository parameterRepository;
	@Autowired
	private UserParameterRepository userParameterRepository;
	@Autowired
	ParameterValueRepository parameterValueRepository;
	@Autowired
	private UserService userService;
	
	@Autowired
	private ParamMapper paramMapper;
	
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
	@Transactional(propagation = Propagation.REQUIRED)
	public void assignValue(UserParamDTO userParamDTO) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		assignParamValueToAUser(userParamDTO, user);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void assignValueToAUser(UserParamDTO userParamDTO) {
		User user = userService.findById(userParamDTO.getUserId());
		assignParamValueToAUser(userParamDTO, user);
	}
	
	@Override
	public List<ParameterValueDTO> getAll() {
		List<ParameterValue> all = parameterValueRepository.findAll();
		return paramMapper.toDTO(all);
	}
	
	private void assignParamValueToAUser(UserParamDTO userParamDTO, User user) {
		final Parameter parameter = findById(userParamDTO.getParameterId());
		if (parameter != null && user != null) {
			Optional<UserParameter> userParameter =
					userParameterRepository.findByUserIdAndParameterId(parameter.getId(), user.getId());
			if (userParameter.isPresent()) {
				userParameter.get().setValue(userParamDTO.getValue());
			} else {
				UserParameter uParam = new UserParameter();
				uParam.setUserId(user.getId());
				uParam.setParameterId(parameter.getId());
				uParam.setValue(userParamDTO.getValue());
				userParameterRepository.save(uParam);
				
				
			}
		}
	}
}
