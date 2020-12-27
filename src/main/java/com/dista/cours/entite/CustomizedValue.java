package com.dista.cours.entite;

import com.dista.cours.annotation.DTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class CustomizedValue extends AbstractEntity {
	private String tableName;
	@Column(name = "entry_id")
	private Long entryId;
	@ManyToOne
	@JoinColumn(name = "property_id")
	private CustomizedProperty property;
	
	private String value;
	
}
