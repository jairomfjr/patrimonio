package com.manus.patrimonio.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da RecursoNaoEncontradoException")
class RecursoNaoEncontradoExceptionTest {

    @Test
    @DisplayName("Deve criar exceção com mensagem")
    void deveCriarExcecaoComMensagem() {
        String mensagem = "Recurso não encontrado";
        RecursoNaoEncontradoException excecao = new RecursoNaoEncontradoException(mensagem);
        
        assertEquals(mensagem, excecao.getMessage());
    }

    @Test
    @DisplayName("Deve criar exceção com mensagem e causa")
    void deveCriarExcecaoComMensagemECausa() {
        String mensagem = "Recurso não encontrado";
        Throwable causa = new RuntimeException("Causa original");
        RecursoNaoEncontradoException excecao = new RecursoNaoEncontradoException(mensagem, causa);
        
        assertEquals(mensagem, excecao.getMessage());
        assertEquals(causa, excecao.getCause());
    }

    @Test
    @DisplayName("Deve criar exceção por ID usando método estático")
    void deveCriarExcecaoPorIdUsandoMetodoEstatico() {
        String entidade = "Bem";
        Long id = 1L;
        RecursoNaoEncontradoException excecao = RecursoNaoEncontradoException.porId(entidade, id);
        
        String mensagemEsperada = "Bem com ID 1 não encontrado(a)";
        assertEquals(mensagemEsperada, excecao.getMessage());
    }

    @Test
    @DisplayName("Deve criar exceção por campo usando método estático")
    void deveCriarExcecaoPorCampoUsandoMetodoEstatico() {
        String entidade = "Bem";
        String campo = "número de série";
        String valor = "SN123456789";
        RecursoNaoEncontradoException excecao = RecursoNaoEncontradoException.porCampo(entidade, campo, valor);
        
        String mensagemEsperada = "Bem com número de série 'SN123456789' não encontrado(a)";
        assertEquals(mensagemEsperada, excecao.getMessage());
    }

    @Test
    @DisplayName("Deve criar exceção por campo com valor null")
    void deveCriarExcecaoPorCampoComValorNull() {
        String entidade = "Bem";
        String campo = "categoria";
        Object valor = null;
        RecursoNaoEncontradoException excecao = RecursoNaoEncontradoException.porCampo(entidade, campo, valor);
        
        String mensagemEsperada = "Bem com categoria 'null' não encontrado(a)";
        assertEquals(mensagemEsperada, excecao.getMessage());
    }

    @Test
    @DisplayName("Deve criar exceção por campo com valor numérico")
    void deveCriarExcecaoPorCampoComValorNumerico() {
        String entidade = "Bem";
        String campo = "valor";
        Object valor = 1000;
        RecursoNaoEncontradoException excecao = RecursoNaoEncontradoException.porCampo(entidade, campo, valor);
        
        String mensagemEsperada = "Bem com valor '1000' não encontrado(a)";
        assertEquals(mensagemEsperada, excecao.getMessage());
    }

    @Test
    @DisplayName("Deve herdar de RuntimeException")
    void deveHerdarDeRuntimeException() {
        RecursoNaoEncontradoException excecao = new RecursoNaoEncontradoException("Teste");
        
        assertTrue(excecao instanceof RuntimeException);
    }

    @Test
    @DisplayName("Deve manter stack trace")
    void deveManterStackTrace() {
        RecursoNaoEncontradoException excecao = new RecursoNaoEncontradoException("Teste");
        
        StackTraceElement[] stackTrace = excecao.getStackTrace();
        assertNotNull(stackTrace);
        assertTrue(stackTrace.length > 0);
    }
}
