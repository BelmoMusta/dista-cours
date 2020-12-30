package com.dista.cours.entite;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DynamicUpdate
public class Parameter extends AbstractEntity {
	@NaturalId(mutable = true)
	private String name;
	private String value;
	private ParameterType type;
	private boolean enabled;
}
