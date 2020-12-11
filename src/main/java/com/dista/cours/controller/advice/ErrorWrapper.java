package com.dista.cours.controller.advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorWrapper {
    private Integer code;
    private String  message;
    public ErrorWrapper withMessage(String message){
        setMessage(message);
        return this;
    }
}
