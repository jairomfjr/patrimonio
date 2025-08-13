package com.manus.patrimonio.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class NotificacaoDTO {

    private Long id;

    @NotNull(message = "Usuário é obrigatório")
    private Long usuarioId;

    @NotBlank(message = "Título é obrigatório")
    @Size(min = 5, max = 200, message = "Título deve ter entre 5 e 200 caracteres")
    private String titulo;

    @NotBlank(message = "Mensagem é obrigatória")
    @Size(min = 10, max = 2000, message = "Mensagem deve ter entre 10 e 2000 caracteres")
    private String mensagem;

    @NotBlank(message = "Tipo é obrigatório")
    @Size(max = 50, message = "Tipo deve ter no máximo 50 caracteres")
    private String tipo;

    private Boolean lida = false;

    private LocalDateTime dataEnvio;

    private LocalDateTime dataLeitura;

    @Min(value = 1, message = "Prioridade deve ser pelo menos 1")
    @Max(value = 10, message = "Prioridade não pode exceder 10")
    private Integer prioridade = 1;

    @Size(max = 100, message = "Categoria deve ter no máximo 100 caracteres")
    private String categoria;

    @Size(max = 100, message = "Entidade relacionada deve ter no máximo 100 caracteres")
    private String entidadeRelacionada;

    private Long idEntidadeRelacionada;

    @Size(max = 200, message = "Ação requerida deve ter no máximo 200 caracteres")
    private String acaoRequerida;

    @Size(max = 500, message = "URL da ação deve ter no máximo 500 caracteres")
    private String urlAcao;

    private Boolean enviadaPorEmail = false;

    private Boolean enviadaPorPush = false;

    private Boolean enviadaPorSms = false;

    private Integer tentativasEnvio = 0;

    private LocalDateTime dataUltimaTentativa;

    @Size(max = 1000, message = "Erro de envio deve ter no máximo 1000 caracteres")
    private String erroEnvio;

    // Campos de relacionamento (para exibição)
    private String usuarioNome;
    private String usuarioUsername;
    private String statusDescricao;
    private String prioridadeDescricao;
    private String tipoDescricao;

    // Constructors
    public NotificacaoDTO() {
        this.dataEnvio = LocalDateTime.now();
    }

    public NotificacaoDTO(Long usuarioId, String titulo, String mensagem, String tipo) {
        this();
        this.usuarioId = usuarioId;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.tipo = tipo;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getLida() {
        return lida;
    }

    public void setLida(Boolean lida) {
        this.lida = lida;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public LocalDateTime getDataLeitura() {
        return dataLeitura;
    }

    public void setDataLeitura(LocalDateTime dataLeitura) {
        this.dataLeitura = dataLeitura;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEntidadeRelacionada() {
        return entidadeRelacionada;
    }

    public void setEntidadeRelacionada(String entidadeRelacionada) {
        this.entidadeRelacionada = entidadeRelacionada;
    }

    public Long getIdEntidadeRelacionada() {
        return idEntidadeRelacionada;
    }

    public void setIdEntidadeRelacionada(Long idEntidadeRelacionada) {
        this.idEntidadeRelacionada = idEntidadeRelacionada;
    }

    public String getAcaoRequerida() {
        return acaoRequerida;
    }

    public void setAcaoRequerida(String acaoRequerida) {
        this.acaoRequerida = acaoRequerida;
    }

    public String getUrlAcao() {
        return urlAcao;
    }

    public void setUrlAcao(String urlAcao) {
        this.urlAcao = urlAcao;
    }

    public Boolean getEnviadaPorEmail() {
        return enviadaPorEmail;
    }

    public void setEnviadaPorEmail(Boolean enviadaPorEmail) {
        this.enviadaPorEmail = enviadaPorEmail;
    }

    public Boolean getEnviadaPorPush() {
        return enviadaPorPush;
    }

    public void setEnviadaPorPush(Boolean enviadaPorPush) {
        this.enviadaPorPush = enviadaPorPush;
    }

    public Boolean getEnviadaPorSms() {
        return enviadaPorSms;
    }

    public void setEnviadaPorSms(Boolean enviadaPorSms) {
        this.enviadaPorSms = enviadaPorSms;
    }

    public Integer getTentativasEnvio() {
        return tentativasEnvio;
    }

    public void setTentativasEnvio(Integer tentativasEnvio) {
        this.tentativasEnvio = tentativasEnvio;
    }

    public LocalDateTime getDataUltimaTentativa() {
        return dataUltimaTentativa;
    }

    public void setDataUltimaTentativa(LocalDateTime dataUltimaTentativa) {
        this.dataUltimaTentativa = dataUltimaTentativa;
    }

    public String getErroEnvio() {
        return erroEnvio;
    }

    public void setErroEnvio(String erroEnvio) {
        this.erroEnvio = erroEnvio;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public String getUsuarioUsername() {
        return usuarioUsername;
    }

    public void setUsuarioUsername(String usuarioUsername) {
        this.usuarioUsername = usuarioUsername;
    }

    public String getStatusDescricao() {
        return statusDescricao;
    }

    public void setStatusDescricao(String statusDescricao) {
        this.statusDescricao = statusDescricao;
    }

    public String getPrioridadeDescricao() {
        return prioridadeDescricao;
    }

    public void setPrioridadeDescricao(String prioridadeDescricao) {
        this.prioridadeDescricao = prioridadeDescricao;
    }

    public String getTipoDescricao() {
        return tipoDescricao;
    }

    public void setTipoDescricao(String tipoDescricao) {
        this.tipoDescricao = tipoDescricao;
    }

    // Business methods
    public boolean isLida() {
        return lida != null && lida;
    }

    public boolean isNaoLida() {
        return !isLida();
    }

    public boolean isUrgente() {
        return prioridade != null && prioridade >= 5;
    }

    public boolean isImportante() {
        return prioridade != null && prioridade >= 3;
    }

    public boolean isNormal() {
        return prioridade != null && prioridade < 3;
    }

    public boolean isAlerta() {
        return "ALERTA".equals(tipo);
    }

    public boolean isInformacao() {
        return "INFORMACAO".equals(tipo);
    }

    public boolean isErro() {
        return "ERRO".equals(tipo);
    }

    public boolean isSucesso() {
        return "SUCESSO".equals(tipo);
    }

    public boolean isAviso() {
        return "AVISO".equals(tipo);
    }

    public boolean temAcaoRequerida() {
        return acaoRequerida != null && !acaoRequerida.isEmpty();
    }

    public boolean temUrlAcao() {
        return urlAcao != null && !urlAcao.isEmpty();
    }

    public boolean foiEnviadaPorTodosCanais() {
        return (enviadaPorEmail != null && enviadaPorEmail) &&
               (enviadaPorPush != null && enviadaPorPush) &&
               (enviadaPorSms != null && enviadaPorSms);
    }

    @Override
    public String toString() {
        return "NotificacaoDTO{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", titulo='" + titulo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", lida=" + lida +
                ", dataEnvio=" + dataEnvio +
                '}';
    }
}
