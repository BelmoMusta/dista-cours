package com.dista.cours.validation.annotation;

public @interface Length {
    int min() default Integer.MAX_VALUE;
    int max() default Integer.MAX_VALUE;
}