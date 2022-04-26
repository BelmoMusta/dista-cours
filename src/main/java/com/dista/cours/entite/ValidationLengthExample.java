package com.dista.cours.entite;


import com.dista.cours.validation.annotation.Length;
import com.dista.cours.validation.annotation.NotNull;
import com.dista.cours.validation.annotation.Validation;

@Validation
//@Getter
//@Setter
public class ValidationLengthExample {

    @NotNull
    @Length(min = 10, max = 30)
    public String forced;

    public String getForced() {
        return forced;
    }

    public void setForced(String forced) {
        this.forced = forced;
    }
}
