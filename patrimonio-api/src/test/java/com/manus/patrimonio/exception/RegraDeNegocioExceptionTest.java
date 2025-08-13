package com.manus.patrimonio.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da RegraDeNegocioException")
class RegraDeNegocioExceptionTest {

    @Test
    @DisplayName("Deve criar exceção com mensagem")
    void deveCriarExcecaoComMensagem() {
        String mensagem = "Regra de negócio violada";
        RegraDeNegocioException excecao = new RegraDeNegocioException(mensagem);
        
        assertEquals(mensagem, excecao.getMessage());
    }

    @Test
    @DisplayName("Deve criar exceção com mensagem e causa")
    void deveCriarExcecaoComMensagemECausa() {
        String mensagem = "Regra de negócio violada";
        Throwable causa = new RuntimeException("Causa original");
        RegraDeNegocioException excecao = new RegraDeNegocioException(mensagem, causa);
        
        assertEquals(mensagem, excecao.getMessage());
        assertEquals(causa, excecao.getCause());
    }

    @Test
    @DisplayName("Deve criar exceção de duplicação usando método estático")
    void deveCriarExcecaoDeDuplicacaoUsandoMetodoEstatico() {
        String entidade = "Bem";
        String campo = "número de série";
        String valor = "SN123456789";
        RegraDeNegocioException excecao = RegraDeNegocioException.duplicacao(entidade, campo, valor);
        
        String mensagemEsperada = "Já existe Bem com número de série 'SN123456789'";
        assertEquals(mensagemEsperada, excecao.getMessage());
    }

    @Test
    @DisplayName("Deve criar exceção de duplicação com valor null")
    void deveCriarExcecaoDeDuplicacaoComValorNull() {
        String entidade = "Bem";
        String campo = "categoria";
        Object valor = null;
        RegraDeNegocioException excecao = RegraDeNegocioException.duplicacao(entidade, campo, valor);
        
        String mensagemEsperada = "Já existe Bem com categoria 'null'";
        assertEquals(mensagemEsperada, excecao.getMessage());
    }

    @Test
    @DisplayName("Deve criar exceção de duplicação com valor numérico")
    void deveCriarExcecaoDeDuplicacaoComValorNumerico() {
        String entidade = "Bem";
        String campo = "valor";
        Object valor = 1000;
        RegraDeNegocioException excecao = RegraDeNegocioException.duplicacao(entidade, campo, valor);
        
        String mensagemEsperada = "Já existe Bem com valor '1000'";
        assertEquals(mensagemEsperada, excecao.getMessage());
    }

    @Test
    @DisplayName("Deve criar exceção de operação inválida usando método estático")
    void deveCriarExcecaoDeOperacaoInvalidaUsandoMetodoEstatico() {
        String operacao = "excluir bem";
        String motivo = "o bem está em uso";
        RegraDeNegocioException excecao = RegraDeNegocioException.operacaoInvalida(operacao, motivo);
        
        String mensagemEsperada = "Não é possível excluir bem: o bem está em uso";
        assertEquals(mensagemEsperada, excecao.getMessage());
    }

    @Test
    @DisplayName("Deve criar exceção de operação inválida com motivo null")
    void deveCriarExcecaoDeOperacaoInvalidaComMotivoNull() {
        String operacao = "excluir bem";
        String motivo = null;
        RegraDeNegocioException excecao = RegraDeNegocioException.operacaoInvalida(operacao, motivo);
        
        String mensagemEsperada = "Não é possível excluir bem: null";
        assertEquals(mensagemEsperada, excecao.getMessage());
    }

    @Test
    @DisplayName("Deve criar exceção de operação inválida com operação null")
    void deveCriarExcecaoDeOperacaoInvalidaComOperacaoNull() {
        String operacao = null;
        String motivo = "motivo qualquer";
        RegraDeNegocioException excecao = RegraDeNegocioException.operacaoInvalida(operacao, motivo);
        
        String mensagemEsperada = "Não é possível null: motivo qualquer";
        assertEquals(mensagemEsperada, excecao.getMessage());
    }

    @Test
    @DisplayName("Deve herdar de RuntimeException")
    void deveHerdarDeRuntimeException() {
        RegraDeNegocioException excecao = new RegraDeNegocioException("Teste");
        
        assertTrue(excecao instanceof RuntimeException);
    }

    @Test
    @DisplayName("Deve manter stack trace")
    void deveManterStackTrace() {
        RegraDeNegocioException excecao = new RegraDeNegocioException("Teste");
        
        StackTraceElement[] stackTrace = excecao.getStackTrace();
        assertNotNull(stackTrace);
        assertTrue(stackTrace.length > 0);
    }

    @Test
    @DisplayName("Deve criar exceção com mensagem vazia")
    void deveCriarExcecaoComMensagemVazia() {
        String mensagem = "";
        RegraDeNegocioException excecao = new RegraDeNegocioException(mensagem);
        
        assertEquals(mensagem, excecao.getMessage());
    }

    @Test
    @DisplayName("Deve criar exceção com mensagem null")
    void deveCriarExcecaoComMensagemNull() {
        String mensagem = null;
        RegraDeNegocioException excecao = new RegraDeNegocioException(mensagem);
        
        assertNull(excecao.getMessage());
    }

    @Test
    @DisplayName("Deve criar exceção com causa null")
    void deveCriarExcecaoComCausaNull() {
        String mensagem = "Teste";
        Throwable causa = null;
        RegraDeNegocioException excecao = new RegraDeNegocioException(mensagem, causa);
        
        assertEquals(mensagem, excecao.getMessage());
        assertNull(excecao.getCause());
    }
}
