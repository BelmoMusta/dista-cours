package com.dista.cours.entite;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class CustomizedProperty extends AbstractEntity {
	@NaturalId(mutable = true)
	private String name;
	private String type;
}
