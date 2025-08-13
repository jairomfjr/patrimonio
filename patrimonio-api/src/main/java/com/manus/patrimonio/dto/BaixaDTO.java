package com.manus.patrimonio.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BaixaDTO {

    private Long id;

    @NotNull(message = "Bem é obrigatório")
    private Long bemId;

    @NotBlank(message = "Motivo da baixa é obrigatório")
    @Size(min = 5, max = 100, message = "Motivo deve ter entre 5 e 100 caracteres")
    private String motivo;

    @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
    private String descricao;

    @NotNull(message = "Data da baixa é obrigatória")
    private LocalDate dataBaixa;

    @DecimalMin(value = "0.0", inclusive = true, message = "Valor residual não pode ser negativo")
    @Digits(integer = 10, fraction = 2, message = "Valor residual deve ter no máximo 10 dígitos inteiros e 2 decimais")
    private BigDecimal valorResidual;

    @Size(max = 100, message = "Processo administrativo deve ter no máximo 100 caracteres")
    private String processoAdministrativo;

    @Size(max = 150, message = "Responsável deve ter no máximo 150 caracteres")
    private String responsavel;

    @Size(max = 1000, message = "Observações devem ter no máximo 1000 caracteres")
    private String observacoes;

    private LocalDate dataAprovacao;

    @Size(max = 150, message = "Aprovado por deve ter no máximo 150 caracteres")
    private String aprovadoPor;

    @Size(max = 1000, message = "Justificativa técnica deve ter no máximo 1000 caracteres")
    private String justificativaTecnica;

    @Size(max = 500, message = "Documentação anexada deve ter no máximo 500 caracteres")
    private String documentacaoAnexada;

    @Size(max = 200, message = "Destino final deve ter no máximo 200 caracteres")
    private String destinoFinal;

    @DecimalMin(value = "0.0", inclusive = true, message = "Valor de venda não pode ser negativo")
    @Digits(integer = 10, fraction = 2, message = "Valor de venda deve ter no máximo 10 dígitos inteiros e 2 decimais")
    private BigDecimal valorVenda;

    private LocalDate dataVenda;

    @Size(max = 150, message = "Comprador deve ter no máximo 150 caracteres")
    private String comprador;

    // Campos de relacionamento (para exibição)
    private String bemNome;
    private String bemNumeroPatrimonio;
    private String categoriaNome;
    private String localizacaoNome;
    private BigDecimal valorAquisicao;
    private BigDecimal perdaFinanceira;
    private BigDecimal ganhoFinanceiro;

    // Constructors
    public BaixaDTO() {}

    public BaixaDTO(Long bemId, String motivo, LocalDate dataBaixa, String responsavel) {
        this.bemId = bemId;
        this.motivo = motivo;
        this.dataBaixa = dataBaixa;
        this.responsavel = responsavel;
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(LocalDate dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public BigDecimal getValorResidual() {
        return valorResidual;
    }

    public void setValorResidual(BigDecimal valorResidual) {
        this.valorResidual = valorResidual;
    }

    public String getProcessoAdministrativo() {
        return processoAdministrativo;
    }

    public void setProcessoAdministrativo(String processoAdministrativo) {
        this.processoAdministrativo = processoAdministrativo;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public LocalDate getDataAprovacao() {
        return dataAprovacao;
    }

    public void setDataAprovacao(LocalDate dataAprovacao) {
        this.dataAprovacao = dataAprovacao;
    }

    public String getAprovadoPor() {
        return aprovadoPor;
    }

    public void setAprovadoPor(String aprovadoPor) {
        this.aprovadoPor = aprovadoPor;
    }

    public String getJustificativaTecnica() {
        return justificativaTecnica;
    }

    public void setJustificativaTecnica(String justificativaTecnica) {
        this.justificativaTecnica = justificativaTecnica;
    }

    public String getDocumentacaoAnexada() {
        return documentacaoAnexada;
    }

    public void setDocumentacaoAnexada(String documentacaoAnexada) {
        this.documentacaoAnexada = documentacaoAnexada;
    }

    public String getDestinoFinal() {
        return destinoFinal;
    }

    public void setDestinoFinal(String destinoFinal) {
        this.destinoFinal = destinoFinal;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public String getBemNome() {
        return bemNome;
    }

    public void setBemNome(String bemNome) {
        this.bemNome = bemNome;
    }

    public String getBemNumeroPatrimonio() {
        return bemNumeroPatrimonio;
    }

    public void setBemNumeroPatrimonio(String bemNumeroPatrimonio) {
        this.bemNumeroPatrimonio = bemNumeroPatrimonio;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }

    public String getLocalizacaoNome() {
        return localizacaoNome;
    }

    public void setLocalizacaoNome(String localizacaoNome) {
        this.localizacaoNome = localizacaoNome;
    }

    public BigDecimal getValorAquisicao() {
        return valorAquisicao;
    }

    public void setValorAquisicao(BigDecimal valorAquisicao) {
        this.valorAquisicao = valorAquisicao;
    }

    public BigDecimal getPerdaFinanceira() {
        return perdaFinanceira;
    }

    public void setPerdaFinanceira(BigDecimal perdaFinanceira) {
        this.perdaFinanceira = perdaFinanceira;
    }

    public BigDecimal getGanhoFinanceiro() {
        return ganhoFinanceiro;
    }

    public void setGanhoFinanceiro(BigDecimal ganhoFinanceiro) {
        this.ganhoFinanceiro = ganhoFinanceiro;
    }

    @Override
    public String toString() {
        return "BaixaDTO{" +
                "id=" + id +
                ", bemId=" + bemId +
                ", motivo='" + motivo + '\'' +
                ", dataBaixa=" + dataBaixa +
                ", responsavel='" + responsavel + '\'' +
                '}';
    }
}
