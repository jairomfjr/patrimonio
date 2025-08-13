package com.manus.patrimonio.exception;

public class RegraDeNegocioException extends RuntimeException {

    public RegraDeNegocioException(String mensagem) {
        super(mensagem);
    }

    public RegraDeNegocioException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    public static RegraDeNegocioException duplicacao(String entidade, String campo, Object valor) {
        return new RegraDeNegocioException(
            String.format("Já existe %s com %s '%s'", entidade, campo, valor)
        );
    }

    public static RegraDeNegocioException operacaoInvalida(String operacao, String motivo) {
        return new RegraDeNegocioException(
            String.format("Não é possível %s: %s", operacao, motivo)
        );
    }
}

