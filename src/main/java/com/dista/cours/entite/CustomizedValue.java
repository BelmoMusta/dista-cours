package com.dista.cours.entite;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

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
	@Transient
	public String getName(){
		return property.getName();
	}
}
