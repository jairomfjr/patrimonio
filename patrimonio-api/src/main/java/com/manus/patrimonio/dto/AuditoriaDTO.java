package com.manus.patrimonio.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class AuditoriaDTO {

    private Long id;

    @NotBlank(message = "Entidade é obrigatória")
    @Size(min = 2, max = 100, message = "Entidade deve ter entre 2 e 100 caracteres")
    private String entidade;

    @NotNull(message = "ID da entidade é obrigatório")
    private Long entidadeId;

    @NotBlank(message = "Ação é obrigatória")
    @Size(min = 2, max = 50, message = "Ação deve ter entre 2 e 50 caracteres")
    private String acao;

    private Long usuarioId;
    private String dadosAnteriores;
    private String dadosNovos;
    private String ipAddress;
    private String userAgent;
    private LocalDateTime dataAcao;

    // Constructors
    public AuditoriaDTO() {}

    public AuditoriaDTO(String entidade, Long entidadeId, String acao) {
        this.entidade = entidade;
        this.entidadeId = entidadeId;
        this.acao = acao;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
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

    // Helper methods
    public boolean isCriacao() {
        return "CREATE".equalsIgnoreCase(acao);
    }

    public boolean isAtualizacao() {
        return "UPDATE".equalsIgnoreCase(acao);
    }

    public boolean isExclusao() {
        return "DELETE".equalsIgnoreCase(acao);
    }

    public boolean isConsulta() {
        return "READ".equalsIgnoreCase(acao);
    }

    public boolean temDadosAnteriores() {
        return dadosAnteriores != null && !dadosAnteriores.trim().isEmpty();
    }

    public boolean temDadosNovos() {
        return dadosNovos != null && !dadosNovos.trim().isEmpty();
    }

    public boolean temUsuario() {
        return usuarioId != null;
    }

    public boolean temInformacoesRede() {
        return ipAddress != null || userAgent != null;
    }

    @Override
    public String toString() {
        return "AuditoriaDTO{" +
                "id=" + id +
                ", entidade='" + entidade + '\'' +
                ", entidadeId=" + entidadeId +
                ", acao='" + acao + '\'' +
                ", usuarioId=" + usuarioId +
                ", dataAcao=" + dataAcao +
                '}';
    }
}
