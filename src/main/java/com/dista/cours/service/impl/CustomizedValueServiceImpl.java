package com.dista.cours.service.impl;

import com.dista.cours.exception.NotFoundException;
import com.dista.cours.repository.CustomizedValueRepository;
import com.dista.cours.service.CustomizePropertyService;
import com.dista.cours.entite.CustomizedProperty;
import com.dista.cours.entite.CustomizedValue;
import com.dista.cours.entite.dto.CustomizedValueDTO;
import com.dista.cours.service.CustomizedValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
			dto.setName(customizedValue.getName());
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
				.orElseThrow(() -> new NotFoundException("CustomizedProperty", name));
		
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
}
