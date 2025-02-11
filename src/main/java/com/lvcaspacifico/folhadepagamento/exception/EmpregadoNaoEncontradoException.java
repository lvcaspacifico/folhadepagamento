package com.lvcaspacifico.folhadepagamento.exception;

public class EmpregadoNaoEncontradoException extends  RuntimeException {

    public EmpregadoNaoEncontradoException(Long id){
        super("Empregado não encontrado. ID fornecido = " + id + ". Ele pode ter sido deletado ou então não existe.");
    }

}
