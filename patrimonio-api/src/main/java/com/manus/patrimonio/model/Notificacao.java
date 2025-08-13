package com.manus.patrimonio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notificacoes")
public class Notificacao extends EntidadeBase {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    @NotNull(message = "Usuário é obrigatório")
    private Usuario usuario;

    @NotBlank(message = "Título é obrigatório")
    @Size(min = 5, max = 200, message = "Título deve ter entre 5 e 200 caracteres")
    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @NotBlank(message = "Mensagem é obrigatória")
    @Size(min = 10, max = 2000, message = "Mensagem deve ter entre 10 e 2000 caracteres")
    @Column(name = "mensagem", nullable = false, length = 2000)
    private String mensagem;

    @NotBlank(message = "Tipo é obrigatório")
    @Size(max = 50, message = "Tipo deve ter no máximo 50 caracteres")
    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "lida")
    private Boolean lida = false;

    @Column(name = "data_envio", nullable = false)
    private LocalDateTime dataEnvio;

    @Column(name = "data_leitura")
    private LocalDateTime dataLeitura;

    @Column(name = "prioridade")
    private Integer prioridade = 1;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "entidade_relacionada")
    private String entidadeRelacionada;

    @Column(name = "id_entidade_relacionada")
    private Long idEntidadeRelacionada;

    @Column(name = "acao_requerida")
    private String acaoRequerida;

    @Column(name = "url_acao")
    private String urlAcao;

    @Column(name = "enviada_por_email")
    private Boolean enviadaPorEmail = false;

    @Column(name = "enviada_por_push")
    private Boolean enviadaPorPush = false;

    @Column(name = "enviada_por_sms")
    private Boolean enviadaPorSms = false;

    @Column(name = "tentativas_envio")
    private Integer tentativasEnvio = 0;

    @Column(name = "data_ultima_tentativa")
    private LocalDateTime dataUltimaTentativa;

    @Column(name = "erro_envio")
    private String erroEnvio;

    // Constructors
    public Notificacao() {
        this.dataEnvio = LocalDateTime.now();
    }

    public Notificacao(Usuario usuario, String titulo, String mensagem, String tipo) {
        this();
        this.usuario = usuario;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.tipo = tipo;
    }

    // Getters and Setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public void marcarComoLida() {
        this.lida = true;
        this.dataLeitura = LocalDateTime.now();
    }

    public void marcarComoNaoLida() {
        this.lida = false;
        this.dataLeitura = null;
    }

    public void registrarTentativaEnvio() {
        this.tentativasEnvio++;
        this.dataUltimaTentativa = LocalDateTime.now();
    }

    public void registrarErroEnvio(String erro) {
        this.erroEnvio = erro;
        this.dataUltimaTentativa = LocalDateTime.now();
    }

    public void marcarEnviadaPorEmail() {
        this.enviadaPorEmail = true;
    }

    public void marcarEnviadaPorPush() {
        this.enviadaPorPush = true;
    }

    public void marcarEnviadaPorSms() {
        this.enviadaPorSms = true;
    }

    public boolean foiEnviadaPorTodosCanais() {
        return (enviadaPorEmail != null && enviadaPorEmail) &&
               (enviadaPorPush != null && enviadaPorPush) &&
               (enviadaPorSms != null && enviadaPorSms);
    }

    public boolean temAcaoRequerida() {
        return acaoRequerida != null && !acaoRequerida.isEmpty();
    }

    public boolean temUrlAcao() {
        return urlAcao != null && !urlAcao.isEmpty();
    }

    @Override
    public String toString() {
        return "Notificacao{" +
                "id=" + getId() +
                ", usuario=" + (usuario != null ? usuario.getUsername() : "null") +
                ", titulo='" + titulo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", lida=" + lida +
                ", dataEnvio=" + dataEnvio +
                '}';
    }
}
