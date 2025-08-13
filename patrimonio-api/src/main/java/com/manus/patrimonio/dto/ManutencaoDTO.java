package com.manus.patrimonio.dto;

import com.manus.patrimonio.enums.StatusManutencao;
import com.manus.patrimonio.enums.TipoManutencao;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ManutencaoDTO {

    private Long id;

    @NotNull(message = "Bem é obrigatório")
    private Long bemId;

    @NotNull(message = "Tipo de manutenção é obrigatório")
    private TipoManutencao tipoManutencao;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(min = 10, max = 1000, message = "Descrição deve ter entre 10 e 1000 caracteres")
    private String descricao;

    @NotNull(message = "Data de início é obrigatória")
    private LocalDate dataInicio;

    private LocalDate dataFim;

    @DecimalMin(value = "0.0", inclusive = true, message = "Custo não pode ser negativo")
    @Digits(integer = 10, fraction = 2, message = "Custo deve ter no máximo 10 dígitos inteiros e 2 decimais")
    private BigDecimal custo;

    @Size(max = 150, message = "Fornecedor deve ter no máximo 150 caracteres")
    private String fornecedor;

    @Size(max = 150, message = "Responsável deve ter no máximo 150 caracteres")
    private String responsavel;

    @NotNull(message = "Status é obrigatório")
    private StatusManutencao status;

    @Size(max = 1000, message = "Observações devem ter no máximo 1000 caracteres")
    private String observacoes;

    private LocalDateTime dataAgendamento;

    @Min(value = 1, message = "Prioridade deve ser pelo menos 1")
    @Max(value = 10, message = "Prioridade não pode exceder 10")
    private Integer prioridade;

    @Min(value = 1, message = "Tempo estimado deve ser pelo menos 1 hora")
    @Max(value = 8760, message = "Tempo estimado não pode exceder 1 ano")
    private Integer tempoEstimadoHoras;

    @Size(max = 500, message = "Causa raiz deve ter no máximo 500 caracteres")
    private String causaRaiz;

    @Size(max = 1000, message = "Solução aplicada deve ter no máximo 1000 caracteres")
    private String solucaoAplicada;

    @Size(max = 1000, message = "Recomendações devem ter no máximo 1000 caracteres")
    private String recomendacoes;

    // Campos de relacionamento (para exibição)
    private String bemNome;
    private String bemNumeroPatrimonio;
    private String categoriaNome;
    private String localizacaoNome;

    // Constructors
    public ManutencaoDTO() {}

    public ManutencaoDTO(Long bemId, TipoManutencao tipoManutencao, String descricao, 
                        LocalDate dataInicio, StatusManutencao status, String responsavel) {
        this.bemId = bemId;
        this.tipoManutencao = tipoManutencao;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.status = status;
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

    public TipoManutencao getTipoManutencao() {
        return tipoManutencao;
    }

    public void setTipoManutencao(TipoManutencao tipoManutencao) {
        this.tipoManutencao = tipoManutencao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public BigDecimal getCusto() {
        return custo;
    }

    public void setCusto(BigDecimal custo) {
        this.custo = custo;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public StatusManutencao getStatus() {
        return status;
    }

    public void setStatus(StatusManutencao status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public LocalDateTime getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDateTime dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public Integer getTempoEstimadoHoras() {
        return tempoEstimadoHoras;
    }

    public void setTempoEstimadoHoras(Integer tempoEstimadoHoras) {
        this.tempoEstimadoHoras = tempoEstimadoHoras;
    }

    public String getCausaRaiz() {
        return causaRaiz;
    }

    public void setCausaRaiz(String causaRaiz) {
        this.causaRaiz = causaRaiz;
    }

    public String getSolucaoAplicada() {
        return solucaoAplicada;
    }

    public void setSolucaoAplicada(String solucaoAplicada) {
        this.solucaoAplicada = solucaoAplicada;
    }

    public String getRecomendacoes() {
        return recomendacoes;
    }

    public void setRecomendacoes(String recomendacoes) {
        this.recomendacoes = recomendacoes;
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

    @Override
    public String toString() {
        return "ManutencaoDTO{" +
                "id=" + id +
                ", bemId=" + bemId +
                ", tipoManutencao=" + tipoManutencao +
                ", status=" + status +
                ", dataInicio=" + dataInicio +
                ", responsavel='" + responsavel + '\'' +
                '}';
    }
}
