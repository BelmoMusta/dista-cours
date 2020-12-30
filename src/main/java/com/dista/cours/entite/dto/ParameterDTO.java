package com.dista.cours.entite.dto;

import com.dista.cours.entite.ParameterType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParameterDTO {
	private Long id;
	private String name;
	private ParameterType type;
}
