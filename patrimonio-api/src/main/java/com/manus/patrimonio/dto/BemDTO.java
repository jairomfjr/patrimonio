package com.manus.patrimonio.dto;

import com.manus.patrimonio.enums.CondicaoBem;
import com.manus.patrimonio.enums.StatusBem;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class BemDTO {

    private Long id;

    @NotBlank(message = "Nome do bem é obrigatório")
    @Size(min = 2, max = 150, message = "Nome deve ter entre 2 e 150 caracteres")
    private String nome;

    @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
    private String descricao;

    @Size(max = 100, message = "Número de série deve ter no máximo 100 caracteres")
    private String numeroSerie;

    @Size(max = 50, message = "Número de patrimônio deve ter no máximo 50 caracteres")
    private String numeroPatrimonio;

    @NotNull(message = "Data de aquisição é obrigatória")
    @PastOrPresent(message = "Data de aquisição não pode ser futura")
    private LocalDate dataAquisicao;

    @NotNull(message = "Valor de aquisição é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Valor deve ser maior que zero")
    @Digits(integer = 10, fraction = 2, message = "Valor deve ter no máximo 10 dígitos inteiros e 2 decimais")
    private BigDecimal valorAquisicao;

    @DecimalMin(value = "0.0", inclusive = true, message = "Valor atual não pode ser negativo")
    @Digits(integer = 10, fraction = 2, message = "Valor atual deve ter no máximo 10 dígitos inteiros e 2 decimais")
    private BigDecimal valorAtual;

    @NotNull(message = "Status é obrigatório")
    private StatusBem status;

    @NotNull(message = "Condição é obrigatória")
    private CondicaoBem condicao;

    @Size(max = 1000, message = "Observações devem ter no máximo 1000 caracteres")
    private String observacoes;

    @NotNull(message = "Categoria é obrigatória")
    private Long categoriaId;

    @NotNull(message = "Localização atual é obrigatória")
    private Long localizacaoAtualId;

    @Size(max = 150, message = "Fornecedor deve ter no máximo 150 caracteres")
    private String fornecedor;

    private LocalDate garantiaAte;

    private LocalDate dataInstalacao;

    private LocalDate dataUltimaManutencao;

    private LocalDate dataProximaManutencao;

    @Min(value = 1, message = "Ciclo de manutenção deve ser pelo menos 1 dia")
    @Max(value = 3650, message = "Ciclo de manutenção não pode exceder 10 anos")
    private Integer cicloManutencaoDias;

    @Size(max = 100, message = "Fabricante deve ter no máximo 100 caracteres")
    private String fabricante;

    @Size(max = 100, message = "Modelo deve ter no máximo 100 caracteres")
    private String modelo;

    @Min(value = 1900, message = "Ano de fabricação deve ser a partir de 1900")
    @Max(value = 2100, message = "Ano de fabricação não pode ser posterior a 2100")
    private Integer anoFabricacao;

    @Size(max = 50, message = "Cor deve ter no máximo 50 caracteres")
    private String cor;

    @Size(max = 200, message = "Dimensões devem ter no máximo 200 caracteres")
    private String dimensoes;

    @DecimalMin(value = "0.0", inclusive = true, message = "Peso não pode ser negativo")
    @Digits(integer = 5, fraction = 2, message = "Peso deve ter no máximo 5 dígitos inteiros e 2 decimais")
    private BigDecimal pesoKg;

    @Min(value = 0, message = "Potência não pode ser negativa")
    @Max(value = 1000000, message = "Potência não pode exceder 1MW")
    private Integer potenciaWatts;

    @Min(value = 0, message = "Tensão não pode ser negativa")
    @Max(value = 1000000, message = "Tensão não pode exceder 1MV")
    private Integer tensaoVolts;

    @Size(max = 200, message = "Consumo de energia deve ter no máximo 200 caracteres")
    private String consumoEnergia;

    @Size(max = 500, message = "Certificações devem ter no máximo 500 caracteres")
    private String certificacoes;

    @Size(max = 500, message = "Manual de instruções deve ter no máximo 500 caracteres")
    private String manualInstrucoes;

    @Size(max = 1000, message = "Fotos devem ter no máximo 1000 caracteres")
    private String fotos;

    @Size(max = 1000, message = "Documentos devem ter no máximo 1000 caracteres")
    private String documentos;

    @Size(max = 150, message = "Responsável atual deve ter no máximo 150 caracteres")
    private String responsavelAtual;

    @Size(max = 100, message = "Departamento responsável deve ter no máximo 100 caracteres")
    private String departamentoResponsavel;

    @Size(max = 100, message = "Projeto associado deve ter no máximo 100 caracteres")
    private String projetoAssociado;

    @Size(max = 100, message = "Centro de custo deve ter no máximo 100 caracteres")
    private String centroCusto;

    @Size(max = 100, message = "Conta contábil deve ter no máximo 100 caracteres")
    private String contaContabil;

    @Size(max = 500, message = "Rateio de departamentos deve ter no máximo 500 caracteres")
    private String rateioDepartamentos;

    @Size(max = 1000, message = "Observações técnicas devem ter no máximo 1000 caracteres")
    private String observacoesTecnicas;

    @Size(max = 1000, message = "Histórico de alterações deve ter no máximo 1000 caracteres")
    private String historicoAlteracoes;

    @Size(max = 500, message = "Tags devem ter no máximo 500 caracteres")
    private String tags;

    @Min(value = 1, message = "Prioridade de manutenção deve ser pelo menos 1")
    @Max(value = 10, message = "Prioridade de manutenção não pode exceder 10")
    private Integer prioridadeManutencao = 1;

    @Size(max = 50, message = "Criticidade deve ter no máximo 50 caracteres")
    private String criticidade;

    @Size(max = 100, message = "Disponibilidade requerida deve ter no máximo 100 caracteres")
    private String disponibilidadeRequerida;

    @Min(value = 0, message = "Tempo médio de reparo não pode ser negativo")
    @Max(value = 8760, message = "Tempo médio de reparo não pode exceder 1 ano")
    private Integer tempoMedioReparoHoras;

    @DecimalMin(value = "0.0", inclusive = true, message = "Custo médio de manutenção não pode ser negativo")
    @Digits(integer = 10, fraction = 2, message = "Custo médio deve ter no máximo 10 dígitos inteiros e 2 decimais")
    private BigDecimal custoMedioManutencao;

    @Min(value = 1, message = "Vida útil deve ser pelo menos 1 ano")
    @Max(value = 100, message = "Vida útil não pode exceder 100 anos")
    private Integer vidaUtilAnos;

    @DecimalMin(value = "0.0", inclusive = true, message = "Taxa de depreciação não pode ser negativa")
    @DecimalMax(value = "1.0", inclusive = true, message = "Taxa de depreciação não pode exceder 100%")
    @Digits(integer = 1, fraction = 4, message = "Taxa de depreciação deve ter no máximo 1 dígito inteiro e 4 decimais")
    private BigDecimal taxaDepreciacao;

    @DecimalMin(value = "0.0", inclusive = true, message = "Valor residual estimado não pode ser negativo")
    @Digits(integer = 10, fraction = 2, message = "Valor residual deve ter no máximo 10 dígitos inteiros e 2 decimais")
    private BigDecimal valorResidualEstimado;

    private LocalDate dataUltimaAvaliacao;

    @Size(max = 150, message = "Avaliador deve ter no máximo 150 caracteres")
    private String avaliadorUltimaAvaliacao;

    @Size(max = 100, message = "Método de avaliação deve ter no máximo 100 caracteres")
    private String metodoAvaliacao;

    @Size(max = 1000, message = "Justificativa da avaliação deve ter no máximo 1000 caracteres")
    private String justificativaAvaliacao;

    // Campos de relacionamento (para exibição)
    private String categoriaNome;
    private String localizacaoAtualNome;
    private String fornecedorNome;
    private List<String> movimentacoes;
    private List<String> manutencoes;
    private List<String> baixas;

    // Constructors
    public BemDTO() {}

    public BemDTO(String nome, String descricao, String numeroSerie, String numeroPatrimonio,
                  LocalDate dataAquisicao, BigDecimal valorAquisicao, StatusBem status,
                  CondicaoBem condicao, Long categoriaId, Long localizacaoAtualId) {
        this.nome = nome;
        this.descricao = descricao;
        this.numeroSerie = numeroSerie;
        this.numeroPatrimonio = numeroPatrimonio;
        this.dataAquisicao = dataAquisicao;
        this.valorAquisicao = valorAquisicao;
        this.status = status;
        this.condicao = condicao;
        this.categoriaId = categoriaId;
        this.localizacaoAtualId = localizacaoAtualId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getNumeroPatrimonio() {
        return numeroPatrimonio;
    }

    public void setNumeroPatrimonio(String numeroPatrimonio) {
        this.numeroPatrimonio = numeroPatrimonio;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public BigDecimal getValorAquisicao() {
        return valorAquisicao;
    }

    public void setValorAquisicao(BigDecimal valorAquisicao) {
        this.valorAquisicao = valorAquisicao;
    }

    public BigDecimal getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(BigDecimal valorAtual) {
        this.valorAtual = valorAtual;
    }

    public StatusBem getStatus() {
        return status;
    }

    public void setStatus(StatusBem status) {
        this.status = status;
    }

    public CondicaoBem getCondicao() {
        return condicao;
    }

    public void setCondicao(CondicaoBem condicao) {
        this.condicao = condicao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Long getLocalizacaoAtualId() {
        return localizacaoAtualId;
    }

    public void setLocalizacaoAtualId(Long localizacaoAtualId) {
        this.localizacaoAtualId = localizacaoAtualId;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public LocalDate getGarantiaAte() {
        return garantiaAte;
    }

    public void setGarantiaAte(LocalDate garantiaAte) {
        this.garantiaAte = garantiaAte;
    }

    public LocalDate getDataInstalacao() {
        return dataInstalacao;
    }

    public void setDataInstalacao(LocalDate dataInstalacao) {
        this.dataInstalacao = dataInstalacao;
    }

    public LocalDate getDataUltimaManutencao() {
        return dataUltimaManutencao;
    }

    public void setDataUltimaManutencao(LocalDate dataUltimaManutencao) {
        this.dataUltimaManutencao = dataUltimaManutencao;
    }

    public LocalDate getDataProximaManutencao() {
        return dataProximaManutencao;
    }

    public void setDataProximaManutencao(LocalDate dataProximaManutencao) {
        this.dataProximaManutencao = dataProximaManutencao;
    }

    public Integer getCicloManutencaoDias() {
        return cicloManutencaoDias;
    }

    public void setCicloManutencaoDias(Integer cicloManutencaoDias) {
        this.cicloManutencaoDias = cicloManutencaoDias;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getDimensoes() {
        return dimensoes;
    }

    public void setDimensoes(String dimensoes) {
        this.dimensoes = dimensoes;
    }

    public BigDecimal getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(BigDecimal pesoKg) {
        this.pesoKg = pesoKg;
    }

    public Integer getPotenciaWatts() {
        return potenciaWatts;
    }

    public void setPotenciaWatts(Integer potenciaWatts) {
        this.potenciaWatts = potenciaWatts;
    }

    public Integer getTensaoVolts() {
        return tensaoVolts;
    }

    public void setTensaoVolts(Integer tensaoVolts) {
        this.tensaoVolts = tensaoVolts;
    }

    public String getConsumoEnergia() {
        return consumoEnergia;
    }

    public void setConsumoEnergia(String consumoEnergia) {
        this.consumoEnergia = consumoEnergia;
    }

    public String getCertificacoes() {
        return certificacoes;
    }

    public void setCertificacoes(String certificacoes) {
        this.certificacoes = certificacoes;
    }

    public String getManualInstrucoes() {
        return manualInstrucoes;
    }

    public void setManualInstrucoes(String manualInstrucoes) {
        this.manualInstrucoes = manualInstrucoes;
    }

    public String getFotos() {
        return fotos;
    }

    public void setFotos(String fotos) {
        this.fotos = fotos;
    }

    public String getDocumentos() {
        return documentos;
    }

    public void setDocumentos(String documentos) {
        this.documentos = documentos;
    }

    public String getResponsavelAtual() {
        return responsavelAtual;
    }

    public void setResponsavelAtual(String responsavelAtual) {
        this.responsavelAtual = responsavelAtual;
    }

    public String getDepartamentoResponsavel() {
        return departamentoResponsavel;
    }

    public void setDepartamentoResponsavel(String departamentoResponsavel) {
        this.departamentoResponsavel = departamentoResponsavel;
    }

    public String getProjetoAssociado() {
        return projetoAssociado;
    }

    public void setProjetoAssociado(String projetoAssociado) {
        this.projetoAssociado = projetoAssociado;
    }

    public String getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(String centroCusto) {
        this.centroCusto = centroCusto;
    }

    public String getContaContabil() {
        return contaContabil;
    }

    public void setContaContabil(String contaContabil) {
        this.contaContabil = contaContabil;
    }

    public String getRateioDepartamentos() {
        return rateioDepartamentos;
    }

    public void setRateioDepartamentos(String rateioDepartamentos) {
        this.rateioDepartamentos = rateioDepartamentos;
    }

    public String getObservacoesTecnicas() {
        return observacoesTecnicas;
    }

    public void setObservacoesTecnicas(String observacoesTecnicas) {
        this.observacoesTecnicas = observacoesTecnicas;
    }

    public String getHistoricoAlteracoes() {
        return historicoAlteracoes;
    }

    public void setHistoricoAlteracoes(String historicoAlteracoes) {
        this.historicoAlteracoes = historicoAlteracoes;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getPrioridadeManutencao() {
        return prioridadeManutencao;
    }

    public void setPrioridadeManutencao(Integer prioridadeManutencao) {
        this.prioridadeManutencao = prioridadeManutencao;
    }

    public String getCriticidade() {
        return criticidade;
    }

    public void setCriticidade(String criticidade) {
        this.criticidade = criticidade;
    }

    public String getDisponibilidadeRequerida() {
        return disponibilidadeRequerida;
    }

    public void setDisponibilidadeRequerida(String disponibilidadeRequerida) {
        this.disponibilidadeRequerida = disponibilidadeRequerida;
    }

    public Integer getTempoMedioReparoHoras() {
        return tempoMedioReparoHoras;
    }

    public void setTempoMedioReparoHoras(Integer tempoMedioReparoHoras) {
        this.tempoMedioReparoHoras = tempoMedioReparoHoras;
    }

    public BigDecimal getCustoMedioManutencao() {
        return custoMedioManutencao;
    }

    public void setCustoMedioManutencao(BigDecimal custoMedioManutencao) {
        this.custoMedioManutencao = custoMedioManutencao;
    }

    public Integer getVidaUtilAnos() {
        return vidaUtilAnos;
    }

    public void setVidaUtilAnos(Integer vidaUtilAnos) {
        this.vidaUtilAnos = vidaUtilAnos;
    }

    public BigDecimal getTaxaDepreciacao() {
        return taxaDepreciacao;
    }

    public void setTaxaDepreciacao(BigDecimal taxaDepreciacao) {
        this.taxaDepreciacao = taxaDepreciacao;
    }

    public BigDecimal getValorResidualEstimado() {
        return valorResidualEstimado;
    }

    public void setValorResidualEstimado(BigDecimal valorResidualEstimado) {
        this.valorResidualEstimado = valorResidualEstimado;
    }

    public LocalDate getDataUltimaAvaliacao() {
        return dataUltimaAvaliacao;
    }

    public void setDataUltimaAvaliacao(LocalDate dataUltimaAvaliacao) {
        this.dataUltimaAvaliacao = dataUltimaAvaliacao;
    }

    public String getAvaliadorUltimaAvaliacao() {
        return avaliadorUltimaAvaliacao;
    }

    public void setAvaliadorUltimaAvaliacao(String avaliadorUltimaAvaliacao) {
        this.avaliadorUltimaAvaliacao = avaliadorUltimaAvaliacao;
    }

    public String getMetodoAvaliacao() {
        return metodoAvaliacao;
    }

    public void setMetodoAvaliacao(String metodoAvaliacao) {
        this.metodoAvaliacao = metodoAvaliacao;
    }

    public String getJustificativaAvaliacao() {
        return justificativaAvaliacao;
    }

    public void setJustificativaAvaliacao(String justificativaAvaliacao) {
        this.justificativaAvaliacao = justificativaAvaliacao;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }

    public String getLocalizacaoAtualNome() {
        return localizacaoAtualNome;
    }

    public void setLocalizacaoAtualNome(String localizacaoAtualNome) {
        this.localizacaoAtualNome = localizacaoAtualNome;
    }

    public String getFornecedorNome() {
        return fornecedorNome;
    }

    public void setFornecedorNome(String fornecedorNome) {
        this.fornecedorNome = fornecedorNome;
    }

    public List<String> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<String> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    public List<String> getManutencoes() {
        return manutencoes;
    }

    public void setManutencoes(List<String> manutencoes) {
        this.manutencoes = manutencoes;
    }

    public List<String> getBaixas() {
        return baixas;
    }

    public void setBaixas(List<String> baixas) {
        this.baixas = baixas;
    }

    @Override
    public String toString() {
        return "BemDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", numeroPatrimonio='" + numeroPatrimonio + '\'' +
                ", status=" + status +
                ", condicao=" + condicao +
                '}';
    }
}

