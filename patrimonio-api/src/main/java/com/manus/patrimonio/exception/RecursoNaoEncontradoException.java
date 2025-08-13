package com.manus.patrimonio.exception;

public class RecursoNaoEncontradoException extends RuntimeException {

    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public RecursoNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    public static RecursoNaoEncontradoException porId(String entidade, Long id) {
        return new RecursoNaoEncontradoException(
            String.format("%s com ID %d não encontrado(a)", entidade, id)
        );
    }

    public static RecursoNaoEncontradoException porCampo(String entidade, String campo, Object valor) {
        return new RecursoNaoEncontradoException(
            String.format("%s com %s '%s' não encontrado(a)", entidade, campo, valor)
        );
    }
}

