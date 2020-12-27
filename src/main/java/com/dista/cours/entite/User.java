package com.dista.cours.entite;

import com.dista.cours.annotation.DTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@DTO
public class User extends AbstractEntity implements UserDetails {
	private String name;
	private String lastName;
	private String username;
	private String email;
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id")})
	private Set<Role> authorities;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "entry_id",updatable = false, insertable = false)
	@Where(clause = "table_name = 'user'")
	Set<CustomizedValue> customizedValues;
}
