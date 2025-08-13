package com.manus.patrimonio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "baixas")
public class Baixa extends EntidadeBase {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bem_id", nullable = false)
    @NotNull(message = "Bem é obrigatório")
    private Bem bem;

    @NotBlank(message = "Motivo da baixa é obrigatório")
    @Size(min = 5, max = 100, message = "Motivo deve ter entre 5 e 100 caracteres")
    @Column(name = "motivo", nullable = false, length = 100)
    private String motivo;

    @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
    @Column(name = "descricao", length = 1000)
    private String descricao;

    @NotNull(message = "Data da baixa é obrigatória")
    @Column(name = "data_baixa", nullable = false)
    private LocalDate dataBaixa;

    @DecimalMin(value = "0.0", inclusive = true, message = "Valor residual não pode ser negativo")
    @Digits(integer = 10, fraction = 2, message = "Valor residual deve ter no máximo 10 dígitos inteiros e 2 decimais")
    @Column(name = "valor_residual", precision = 12, scale = 2)
    private BigDecimal valorResidual;

    @Size(max = 100, message = "Processo administrativo deve ter no máximo 100 caracteres")
    @Column(name = "processo_administrativo", length = 100)
    private String processoAdministrativo;

    @Size(max = 150, message = "Responsável deve ter no máximo 150 caracteres")
    @Column(name = "responsavel", length = 150)
    private String responsavel;

    @Size(max = 1000, message = "Observações devem ter no máximo 1000 caracteres")
    @Column(name = "observacoes", length = 1000)
    private String observacoes;

    @Column(name = "data_aprovacao")
    private LocalDate dataAprovacao;

    @Column(name = "aprovado_por")
    private String aprovadoPor;

    @Column(name = "justificativa_tecnica")
    private String justificativaTecnica;

    @Column(name = "documentacao_anexada")
    private String documentacaoAnexada;

    @Column(name = "destino_final")
    private String destinoFinal;

    @Column(name = "valor_venda")
    private BigDecimal valorVenda;

    @Column(name = "data_venda")
    private LocalDate dataVenda;

    @Column(name = "comprador")
    private String comprador;

    // Constructors
    public Baixa() {}

    public Baixa(Bem bem, String motivo, LocalDate dataBaixa, String responsavel) {
        this.bem = bem;
        this.motivo = motivo;
        this.dataBaixa = dataBaixa;
        this.responsavel = responsavel;
    }

    // Getters and Setters
    public Bem getBem() {
        return bem;
    }

    public void setBem(Bem bem) {
        this.bem = bem;
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

    // Business methods
    public void aprovar(String aprovadoPor, LocalDate dataAprovacao) {
        this.aprovadoPor = aprovadoPor;
        this.dataAprovacao = dataAprovacao;
    }

    public void registrarVenda(String comprador, BigDecimal valorVenda, LocalDate dataVenda) {
        this.comprador = comprador;
        this.valorVenda = valorVenda;
        this.dataVenda = dataVenda;
    }

    public boolean isAprovada() {
        return dataAprovacao != null && aprovadoPor != null;
    }

    public boolean isVendida() {
        return valorVenda != null && dataVenda != null && comprador != null;
    }

    public BigDecimal getPerdaFinanceira() {
        if (bem != null && bem.getValorAquisicao() != null && valorResidual != null) {
            return bem.getValorAquisicao().subtract(valorResidual);
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getGanhoFinanceiro() {
        if (valorVenda != null && valorResidual != null) {
            return valorVenda.subtract(valorResidual);
        }
        return BigDecimal.ZERO;
    }

    @Override
    public String toString() {
        return "Baixa{" +
                "id=" + getId() +
                ", bem=" + (bem != null ? bem.getNome() : "null") +
                ", motivo='" + motivo + '\'' +
                ", dataBaixa=" + dataBaixa +
                ", responsavel='" + responsavel + '\'' +
                ", aprovada=" + isAprovada() +
                '}';
    }
}
