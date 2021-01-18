package com.dista.cours.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParameterValueDTO {
	private String value;
	private String type;
	private String name;
	private Long userId;
	private Long parameterId;
	
}
