package com.dista.cours.service.impl;
import com.dista.cours.entite.CustomizedProperty;
import com.dista.cours.entite.CustomizedValue;
import com.dista.cours.dtos.CustomizedValueDTO;
import com.dista.cours.repository.CustomizedValueRepository;
import com.dista.cours.service.CustomizePropertyService;
import com.dista.cours.service.CustomizedValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomizedValueServiceImpl implements CustomizedValueService {
	@Autowired
	private CustomizedValueRepository customizedValueRepository;
	
	@Autowired
	private CustomizePropertyService customizePropertyService;
	
	
	@Override
	public void create(CustomizedValueDTO propertyDTO) {
	
	}
	
	@Override
	public void delete(String name) {
	
	}
	
	@Override
	public void delete(Long name) {
	
	}
	
	@Override
	public CustomizedValueDTO findByName(String name) {
		return null;
	}
	
	@Override
	public List<CustomizedValueDTO> findFor(String tableName, Long id) {
		final EqualsSpecification<CustomizedValue> tableNameSpec = EqualsSpecification.create("tableName",
				tableName);
		final EqualsSpecification<CustomizedValue> idSpec = EqualsSpecification.create("entryId",
				id);
		final List<CustomizedValue> all = customizedValueRepository.findAll(tableNameSpec.and(idSpec));
		List<CustomizedValueDTO> values = new ArrayList<>();
		for (CustomizedValue customizedValue : all) {
			CustomizedValueDTO dto = new CustomizedValueDTO();
			dto.setName(customizedValue.getProperty().getName());
			dto.setType(customizedValue.getProperty().getType());
			dto.setValue(customizedValue.getValue());
			values.add(dto);
		}
		return values;
	}
	
	@Override
	public CustomizedValue findFor(String valueName, String tableName, Long id) {
		final EqualsSpecification<CustomizedValue> tableNameSpec = EqualsSpecification.create("tableName",
				tableName);
		final EqualsSpecification<CustomizedValue> idSpec = EqualsSpecification.create("entryId",
				id);
		final EqualsSpecification<CustomizedValue> propertyNameSpec = EqualsSpecification.create("property.name", valueName);
		return customizedValueRepository.findOne(tableNameSpec.and(idSpec).and(propertyNameSpec))
				.orElse(null);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void createFor(Long id, String user, CustomizedValueDTO customizedValueDTO) {
		final String name = customizedValueDTO.getName();
		final CustomizedProperty customizedProperty = customizePropertyService.findEntityByName(name)
				.orElseThrow(() -> new RuntimeException("CustomizedProperty"+name));
		
		fffffffffff(id, user, customizedValueDTO, customizedProperty);
	}
	
	private void fffffffffff(Long id, String user, CustomizedValueDTO customizedValueDTO, CustomizedProperty customizedProperty) {
		CustomizedValue value = findFor(customizedValueDTO.getName(), user, id);
		final CustomizedValue customizedValue;
		
		if (value == null) {
			customizedValue = new CustomizedValue();
		} else {
			customizedValue = value;
		}
		customizedValue.setTableName(user);
		customizedValue.setEntryId(id);
		customizedValue.setProperty(customizedProperty);
		customizedValue.setValue(customizedValueDTO.getValue());
		customizedValueRepository.save(customizedValue);
	}
	
	@Override
	public void createFor(Long id, Long propertyId, String user, CustomizedValueDTO customizedValueDTO) {
		Optional<CustomizedProperty> property = customizePropertyService.findById(propertyId);
		if(property.isPresent()) {
			fffffffffff(id, user,customizedValueDTO,property.get());
		}
	}
}
