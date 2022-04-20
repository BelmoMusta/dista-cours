package com.dista.cours.entite;


import com.dista.cours.validation.annotation.NotNull;
import com.dista.cours.validation.annotation.Validation;
import lombok.Getter;
import lombok.Setter;

@Validation
@Getter
@Setter
public class ValidationExample {
    @NotNull
    private String toto;
}
