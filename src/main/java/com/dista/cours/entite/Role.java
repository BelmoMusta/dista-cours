package com.dista.cours.entite;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(uniqueConstraints={
		@UniqueConstraint(columnNames = {"authority"})
})
public class Role extends AbstractEntity implements GrantedAuthority {
	@NaturalId(mutable = true)
	private String authority;
	private boolean enabled;
	
	@Override
	public boolean equals(Object another) {
		final boolean areEqual;
		if (this == another) {
			areEqual = true;
		} else if (another == null || getClass() != another.getClass()) {
			areEqual = false;
		} else if (!super.equals(another)) {
			areEqual = false;
		} else {
			final Role otherRole = (Role) another;
			areEqual = Objects.equals(getId(), otherRole.getId())
					|| Objects.equals(authority, otherRole.authority);
		}
		return areEqual;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getId(), authority);
	}
}
