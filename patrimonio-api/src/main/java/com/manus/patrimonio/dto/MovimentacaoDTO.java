package com.manus.patrimonio.dto;

import com.manus.patrimonio.enums.TipoMovimentacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class MovimentacaoDTO {

    private Long id;

    @NotNull(message = "Bem é obrigatório")
    private Long bemId;
    private String bemNome;
    private String bemNumeroSerie;

    @NotNull(message = "Data da movimentação é obrigatória")
    private LocalDateTime dataMovimentacao;

    @NotNull(message = "Tipo de movimentação é obrigatório")
    private TipoMovimentacao tipoMovimentacao;

    private Long localizacaoOrigemId;
    private String localizacaoOrigemNome;

    private Long localizacaoDestinoId;
    private String localizacaoDestinoNome;

    @NotBlank(message = "Responsável pela movimentação é obrigatório")
    @Size(min = 2, max = 100, message = "Nome do responsável deve ter entre 2 e 100 caracteres")
    private String responsavelMovimentacao;

    @Size(max = 1000, message = "Observações devem ter no máximo 1000 caracteres")
    private String observacoes;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    // Constructors
    public MovimentacaoDTO() {}

    public MovimentacaoDTO(Long bemId, LocalDateTime dataMovimentacao, TipoMovimentacao tipoMovimentacao,
                          Long localizacaoOrigemId, Long localizacaoDestinoId, String responsavelMovimentacao) {
        this.bemId = bemId;
        this.dataMovimentacao = dataMovimentacao;
        this.tipoMovimentacao = tipoMovimentacao;
        this.localizacaoOrigemId = localizacaoOrigemId;
        this.localizacaoDestinoId = localizacaoDestinoId;
        this.responsavelMovimentacao = responsavelMovimentacao;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBemId() {
        return bemId;
    }

    public void setBemId(Long bemId) {
        this.bemId = bemId;
    }

    public String getBemNome() {
        return bemNome;
    }

    public void setBemNome(String bemNome) {
        this.bemNome = bemNome;
    }

    public String getBemNumeroSerie() {
        return bemNumeroSerie;
    }

    public void setBemNumeroSerie(String bemNumeroSerie) {
        this.bemNumeroSerie = bemNumeroSerie;
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public Long getLocalizacaoOrigemId() {
        return localizacaoOrigemId;
    }

    public void setLocalizacaoOrigemId(Long localizacaoOrigemId) {
        this.localizacaoOrigemId = localizacaoOrigemId;
    }

    public String getLocalizacaoOrigemNome() {
        return localizacaoOrigemNome;
    }

    public void setLocalizacaoOrigemNome(String localizacaoOrigemNome) {
        this.localizacaoOrigemNome = localizacaoOrigemNome;
    }

    public Long getLocalizacaoDestinoId() {
        return localizacaoDestinoId;
    }

    public void setLocalizacaoDestinoId(Long localizacaoDestinoId) {
        this.localizacaoDestinoId = localizacaoDestinoId;
    }

    public String getLocalizacaoDestinoNome() {
        return localizacaoDestinoNome;
    }

    public void setLocalizacaoDestinoNome(String localizacaoDestinoNome) {
        this.localizacaoDestinoNome = localizacaoDestinoNome;
    }

    public String getResponsavelMovimentacao() {
        return responsavelMovimentacao;
    }

    public void setResponsavelMovimentacao(String responsavelMovimentacao) {
        this.responsavelMovimentacao = responsavelMovimentacao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @Override
    public String toString() {
        return "MovimentacaoDTO{" +
                "id=" + id +
                ", bemNome='" + bemNome + '\'' +
                ", dataMovimentacao=" + dataMovimentacao +
                ", tipoMovimentacao=" + tipoMovimentacao +
                ", localizacaoOrigemNome='" + localizacaoOrigemNome + '\'' +
                ", localizacaoDestinoNome='" + localizacaoDestinoNome + '\'' +
                ", responsavelMovimentacao='" + responsavelMovimentacao + '\'' +
                '}';
    }
}

