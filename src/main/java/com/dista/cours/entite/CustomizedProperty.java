package com.dista.cours.entite;

import com.dista.cours.annotation.DTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DTO
public class CustomizedProperty extends AbstractEntity {
	private String name;
	private String type;
}
