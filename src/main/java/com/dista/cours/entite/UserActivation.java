package com.dista.cours.entite;

import com.dista.cours.annotation.DTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Getter
@Setter
@DTO
public class UserActivation extends AbstractEntity {
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	private String token;
	private Date expiresAt;
	private boolean consumed;
	
}
