package com.dista.cours.entite;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@DynamicUpdate
@IdClass(UserParameterPK.class)
public class UserParameter {
	@ManyToOne
	@Id
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	User user;
	@ManyToOne
	@Id
	@JoinColumn(name = "parameter_id", insertable = false, updatable = false)
	Parameter parameter;
	@Column(name = "user_id")
	Long userId;
	@Column(name = "parameter_id")
	Long parameterId;
	
	String value;
	
	
}
