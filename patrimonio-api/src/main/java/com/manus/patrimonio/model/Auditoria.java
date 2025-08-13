package com.manus.patrimonio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "auditoria")
public class Auditoria extends EntidadeBase {

    @NotBlank(message = "Entidade é obrigatória")
    @Size(max = 100, message = "Entidade deve ter no máximo 100 caracteres")
    @Column(name = "entidade", nullable = false, length = 100)
    private String entidade;

    @NotNull(message = "ID da entidade é obrigatório")
    @Column(name = "entidade_id", nullable = false)
    private Long entidadeId;

    @NotBlank(message = "Ação é obrigatória")
    @Size(max = 50, message = "Ação deve ter no máximo 50 caracteres")
    @Column(name = "acao", nullable = false, length = 50)
    private String acao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "dados_anteriores", columnDefinition = "JSONB")
    private String dadosAnteriores;

    @Column(name = "dados_novos", columnDefinition = "JSONB")
    private String dadosNovos;

    @Size(max = 45, message = "Endereço IP deve ter no máximo 45 caracteres")
    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Column(name = "user_agent")
    private String userAgent;

    @NotNull(message = "Data da ação é obrigatória")
    @Column(name = "data_acao", nullable = false)
    private LocalDateTime dataAcao;

    @Column(name = "sessao_id")
    private String sessaoId;

    @Column(name = "metodo_http")
    private String metodoHttp;

    @Column(name = "url_requisicao")
    private String urlRequisicao;

    @Column(name = "parametros_requisicao")
    private String parametrosRequisicao;

    @Column(name = "resultado_acao")
    private String resultadoAcao;

    @Column(name = "tempo_execucao_ms")
    private Long tempoExecucaoMs;

    @Column(name = "erro_ocorrido")
    private String erroOcorrido;

    // Constructors
    public Auditoria() {
        this.dataAcao = LocalDateTime.now();
    }

    public Auditoria(String entidade, Long entidadeId, String acao, Usuario usuario) {
        this();
        this.entidade = entidade;
        this.entidadeId = entidadeId;
        this.acao = acao;
        this.usuario = usuario;
    }

    // Getters and Setters
    public String getEntidade() {
        return entidade;
    }

    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }

    public Long getEntidadeId() {
        return entidadeId;
    }

    public void setEntidadeId(Long entidadeId) {
        this.entidadeId = entidadeId;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDadosAnteriores() {
        return dadosAnteriores;
    }

    public void setDadosAnteriores(String dadosAnteriores) {
        this.dadosAnteriores = dadosAnteriores;
    }

    public String getDadosNovos() {
        return dadosNovos;
    }

    public void setDadosNovos(String dadosNovos) {
        this.dadosNovos = dadosNovos;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public LocalDateTime getDataAcao() {
        return dataAcao;
    }

    public void setDataAcao(LocalDateTime dataAcao) {
        this.dataAcao = dataAcao;
    }

    public String getSessaoId() {
        return sessaoId;
    }

    public void setSessaoId(String sessaoId) {
        this.sessaoId = sessaoId;
    }

    public String getMetodoHttp() {
        return metodoHttp;
    }

    public void setMetodoHttp(String metodoHttp) {
        this.metodoHttp = metodoHttp;
    }

    public String getUrlRequisicao() {
        return urlRequisicao;
    }

    public void setUrlRequisicao(String urlRequisicao) {
        this.urlRequisicao = urlRequisicao;
    }

    public String getParametrosRequisicao() {
        return parametrosRequisicao;
    }

    public void setParametrosRequisicao(String parametrosRequisicao) {
        this.parametrosRequisicao = parametrosRequisicao;
    }

    public String getResultadoAcao() {
        return resultadoAcao;
    }

    public void setResultadoAcao(String resultadoAcao) {
        this.resultadoAcao = resultadoAcao;
    }

    public Long getTempoExecucaoMs() {
        return tempoExecucaoMs;
    }

    public void setTempoExecucaoMs(Long tempoExecucaoMs) {
        this.tempoExecucaoMs = tempoExecucaoMs;
    }

    public String getErroOcorrido() {
        return erroOcorrido;
    }

    public void setErroOcorrido(String erroOcorrido) {
        this.erroOcorrido = erroOcorrido;
    }

    // Business methods
    public boolean isCriacao() {
        return "CREATE".equals(acao);
    }

    public boolean isAtualizacao() {
        return "UPDATE".equals(acao);
    }

    public boolean isExclusao() {
        return "DELETE".equals(acao);
    }

    public boolean isConsulta() {
        return "READ".equals(acao);
    }

    public boolean isLogin() {
        return "LOGIN".equals(acao);
    }

    public boolean isLogout() {
        return "LOGOUT".equals(acao);
    }

    public boolean isAcaoSistema() {
        return "SYSTEM".equals(acao);
    }

    public boolean temDadosAnteriores() {
        return dadosAnteriores != null && !dadosAnteriores.isEmpty();
    }

    public boolean temDadosNovos() {
        return dadosNovos != null && !dadosNovos.isEmpty();
    }

    public boolean temErro() {
        return erroOcorrido != null && !erroOcorrido.isEmpty();
    }

    public void registrarErro(String erro) {
        this.erroOcorrido = erro;
        this.resultadoAcao = "ERRO";
    }

    public void registrarSucesso() {
        this.resultadoAcao = "SUCESSO";
    }

    public void registrarFalha() {
        this.resultadoAcao = "FALHA";
    }

    public void definirTempoExecucao(Long tempoMs) {
        this.tempoExecucaoMs = tempoMs;
    }

    @Override
    public String toString() {
        return "Auditoria{" +
                "id=" + getId() +
                ", entidade='" + entidade + '\'' +
                ", entidadeId=" + entidadeId +
                ", acao='" + acao + '\'' +
                ", usuario=" + (usuario != null ? usuario.getUsername() : "null") +
                ", dataAcao=" + dataAcao +
                ", resultado='" + resultadoAcao + '\'' +
                '}';
    }
}
