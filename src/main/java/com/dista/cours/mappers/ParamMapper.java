package com.dista.cours.mappers;

import com.dista.cours.dtos.ParameterValueDTO;
import com.dista.cours.entite.ParameterValue;
import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;

import java.util.List;


@Mapper(withIoC = IoC.SPRING, withIgnoreMissing = IgnoreMissing.ALL, withIgnoreNullValue = true)
public interface ParamMapper {
    List<ParameterValueDTO> toDTO(List<ParameterValue> in);
}