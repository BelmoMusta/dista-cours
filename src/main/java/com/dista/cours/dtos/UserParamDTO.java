package com.dista.cours.dtos;

import com.dista.cours.entite.AbstractEntity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserParamDTO extends AbstractEntity {
	private Long userId;
	private Long parameterId;
	private String value;
}
