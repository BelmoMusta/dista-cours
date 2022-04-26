package com.dista.cours.entite;


import com.dista.cours.validation.annotation.MustEqual;
import com.dista.cours.validation.annotation.Validation;

//@Validation
//@Getter
//@Setter
public class ValidationExampleThree {

    @MustEqual("87")
    public short calculAge() {
        return 0;
    }

    @MustEqual("87")
    public double calculAgeAsDouble() {
        return 0;
    }

}
