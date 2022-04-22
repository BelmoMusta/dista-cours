package com.dista.cours.entite;


import com.dista.cours.validation.annotation.MustEqual;
import com.dista.cours.validation.annotation.NotNull;
import com.dista.cours.validation.annotation.Validation;
import lombok.Getter;
import lombok.Setter;

@Validation
@Getter
@Setter
public class ValidationExample {
    @MustEqual("123")
    public String forced;
    @NotNull
    private String toto;
    @NotNull
    private String bar;

    private String foo;

    public String getToto() {
        return toto;
    }

    public void setToto(String toto) {
        this.toto = toto;
    }

    public String getBar() {
        return bar;
    }

    @NotNull
    public String getFoo() {
        return foo;
    }

    @NotNull
    public void lol() {
    }

    public String getForced() {
        return forced;
    }
}
