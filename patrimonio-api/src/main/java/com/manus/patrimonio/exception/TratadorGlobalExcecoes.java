package com.manus.patrimonio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TratadorGlobalExcecoes {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<RespostaErro> tratarRecursoNaoEncontrado(
            RecursoNaoEncontradoException ex, WebRequest request) {
        
        RespostaErro erro = new RespostaErro(
            HttpStatus.NOT_FOUND.value(),
            "Recurso não encontrado",
            ex.getMessage(),
            request.getDescription(false),
            LocalDateTime.now()
        );
        
        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<RespostaErro> tratarRegraDeNegocio(
            RegraDeNegocioException ex, WebRequest request) {
        
        RespostaErro erro = new RespostaErro(
            HttpStatus.BAD_REQUEST.value(),
            "Violação de regra de negócio",
            ex.getMessage(),
            request.getDescription(false),
            LocalDateTime.now()
        );
        
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespostaErroValidacao> tratarErrosValidacao(
            MethodArgumentNotValidException ex, WebRequest request) {
        
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nomeCampo = ((FieldError) error).getField();
            String mensagemErro = error.getDefaultMessage();
            erros.put(nomeCampo, mensagemErro);
        });

        RespostaErroValidacao erro = new RespostaErroValidacao(
            HttpStatus.BAD_REQUEST.value(),
            "Erro de validação",
            "Dados inválidos fornecidos",
            request.getDescription(false),
            LocalDateTime.now(),
            erros
        );
        
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<RespostaErro> tratarArgumentoIlegal(
            IllegalArgumentException ex, WebRequest request) {
        
        RespostaErro erro = new RespostaErro(
            HttpStatus.BAD_REQUEST.value(),
            "Argumento inválido",
            ex.getMessage(),
            request.getDescription(false),
            LocalDateTime.now()
        );
        
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespostaErro> tratarErroGenerico(
            Exception ex, WebRequest request) {
        
        RespostaErro erro = new RespostaErro(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Erro interno do servidor",
            "Ocorreu um erro inesperado. Tente novamente mais tarde.",
            request.getDescription(false),
            LocalDateTime.now()
        );
        
        return new ResponseEntity<>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Classes internas para resposta de erro
    public static class RespostaErro {
        private int status;
        private String erro;
        private String mensagem;
        private String caminho;
        private LocalDateTime timestamp;

        public RespostaErro(int status, String erro, String mensagem, String caminho, LocalDateTime timestamp) {
            this.status = status;
            this.erro = erro;
            this.mensagem = mensagem;
            this.caminho = caminho;
            this.timestamp = timestamp;
        }

        // Getters
        public int getStatus() { return status; }
        public String getErro() { return erro; }
        public String getMensagem() { return mensagem; }
        public String getCaminho() { return caminho; }
        public LocalDateTime getTimestamp() { return timestamp; }
    }

    public static class RespostaErroValidacao extends RespostaErro {
        private Map<String, String> errosValidacao;

        public RespostaErroValidacao(int status, String erro, String mensagem, String caminho, 
                                   LocalDateTime timestamp, Map<String, String> errosValidacao) {
            super(status, erro, mensagem, caminho, timestamp);
            this.errosValidacao = errosValidacao;
        }

        public Map<String, String> getErrosValidacao() { return errosValidacao; }
    }
}

