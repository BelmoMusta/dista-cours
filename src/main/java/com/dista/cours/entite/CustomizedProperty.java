package com.dista.cours.entite;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class CustomizedProperty extends AbstractEntity {
	private String name;
	private String type;
}
