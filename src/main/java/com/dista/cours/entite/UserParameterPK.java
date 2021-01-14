package com.dista.cours.entite;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter

public class UserParameterPK implements Serializable {
	@Id
	@Column(name = "user_id", insertable = false, updatable = false)
	Long userId;
	@Id
	@Column(name = "parameter_id", insertable = false, updatable = false)
	
	Long parameterId;
}
