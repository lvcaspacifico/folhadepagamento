package com.lvcaspacifico.folhadepagamento.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmpregadoNaoEncontradoAdvice {

    @ExceptionHandler(EmpregadoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String empregadoNaoEncontradoHandler(EmpregadoNaoEncontradoException exception){
        return exception.getMessage();
    }
}
