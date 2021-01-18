package com.dista.cours.entite;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@Subselect("SELECT * FROM parameter INNER JOIN user_parameter up ON parameter.id = up.parameter_id")
public class ParameterValue extends AbstractEntity {
	private String value;
	private String type;
	private String name;
	private Long userId;
	private Long parameterId;
}
