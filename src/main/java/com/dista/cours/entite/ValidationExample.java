package com.dista.cours.entite;


import com.dista.cours.validation.annotation.MustEqual;
import com.dista.cours.validation.annotation.NotNull;
import com.dista.cours.validation.annotation.Validation;

//@Validation
//@Getter
//@Setter
public class ValidationExample {
    @MustEqual("true")
    boolean enabled;

    @MustEqual("88")
    private int age;

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

    public int getAge() {
        return age;
    }

    public String getForced() {
        return forced;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
