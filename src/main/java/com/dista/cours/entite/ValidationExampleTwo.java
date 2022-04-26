package com.dista.cours.entite;


import com.dista.cours.validation.annotation.MustEqual;
import com.dista.cours.validation.annotation.Validation;

//@Validation
//@Getter
//@Setter
public class ValidationExampleTwo {
	@MustEqual("true")
	boolean enabled;

    @MustEqual("87")
    public int calculAge() {
        return 0;
    }
	
	public boolean isEnabled() {
		return enabled;
	}
	public boolean isTestEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
