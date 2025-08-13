package com.manus.patrimonio.model;

import com.manus.patrimonio.enums.StatusManutencao;
import com.manus.patrimonio.enums.TipoManutencao;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "manutencoes")
public class Manutencao extends EntidadeBase {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bem_id", nullable = false)
    @NotNull(message = "Bem é obrigatório")
    private Bem bem;

    @NotNull(message = "Tipo de manutenção é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_manutencao", nullable = false)
    private TipoManutencao tipoManutencao;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(min = 10, max = 1000, message = "Descrição deve ter entre 10 e 1000 caracteres")
    @Column(name = "descricao", nullable = false, length = 1000)
    private String descricao;

    @NotNull(message = "Data de início é obrigatória")
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @DecimalMin(value = "0.0", inclusive = true, message = "Custo não pode ser negativo")
    @Digits(integer = 10, fraction = 2, message = "Custo deve ter no máximo 10 dígitos inteiros e 2 decimais")
    @Column(name = "custo", precision = 12, scale = 2)
    private BigDecimal custo;

    @Size(max = 150, message = "Fornecedor deve ter no máximo 150 caracteres")
    @Column(name = "fornecedor", length = 150)
    private String fornecedor;

    @Size(max = 150, message = "Responsável deve ter no máximo 150 caracteres")
    @Column(name = "responsavel", length = 150)
    private String responsavel;

    @NotNull(message = "Status é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusManutencao status;

    @Size(max = 1000, message = "Observações devem ter no máximo 1000 caracteres")
    @Column(name = "observacoes", length = 1000)
    private String observacoes;

    @Column(name = "data_agendamento")
    private LocalDateTime dataAgendamento;

    @Column(name = "prioridade")
    private Integer prioridade;

    @Column(name = "tempo_estimado_horas")
    private Integer tempoEstimadoHoras;

    @Column(name = "causa_raiz")
    private String causaRaiz;

    @Column(name = "solucao_aplicada")
    private String solucaoAplicada;

    @Column(name = "recomendacoes")
    private String recomendacoes;

    // Constructors
    public Manutencao() {}

    public Manutencao(Bem bem, TipoManutencao tipoManutencao, String descricao, LocalDate dataInicio, 
                      StatusManutencao status, String responsavel) {
        this.bem = bem;
        this.tipoManutencao = tipoManutencao;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.status = status;
        this.responsavel = responsavel;
    }

    // Getters and Setters
    public Bem getBem() {
        return bem;
    }

    public void setBem(Bem bem) {
        this.bem = bem;
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

    // Business methods
    public boolean isAtiva() {
        return status.isAtiva();
    }

    public boolean isFinalizada() {
        return status.isFinalizada();
    }

    public boolean isAguardando() {
        return status.isAguardando();
    }

    public boolean isProgramada() {
        return tipoManutencao.isProgramada();
    }

    public boolean isCorretiva() {
        return tipoManutencao.isCorretiva();
    }

    public boolean isEspecializada() {
        return tipoManutencao.isEspecializada();
    }

    public void finalizar(LocalDate dataFim, String solucaoAplicada, String recomendacoes) {
        this.dataFim = dataFim;
        this.solucaoAplicada = solucaoAplicada;
        this.recomendacoes = recomendacoes;
        this.status = StatusManutencao.CONCLUIDA;
    }

    public void cancelar(String motivo) {
        this.status = StatusManutencao.CANCELADA;
        this.observacoes = (this.observacoes != null ? this.observacoes + "\n" : "") + "Cancelada: " + motivo;
    }

    @Override
    public String toString() {
        return "Manutencao{" +
                "id=" + getId() +
                ", bem=" + (bem != null ? bem.getNome() : "null") +
                ", tipoManutencao=" + tipoManutencao +
                ", status=" + status +
                ", dataInicio=" + dataInicio +
                ", responsavel='" + responsavel + '\'' +
                '}';
    }
}
