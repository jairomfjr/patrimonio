package com.manus.patrimonio.model;

import com.manus.patrimonio.enums.CondicaoBem;
import com.manus.patrimonio.enums.StatusBem;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entidade que representa um bem patrimonial.
 * Contém todas as informações necessárias para gestão de patrimônio.
 */
@Entity
@Table(name = "bens", indexes = {
    @Index(name = "idx_bem_tombamento", columnList = "numero_tombamento", unique = true),
    @Index(name = "idx_bem_categoria", columnList = "categoria_id"),
    @Index(name = "idx_bem_localizacao", columnList = "localizacao_atual_id"),
    @Index(name = "idx_bem_status", columnList = "status"),
    @Index(name = "idx_bem_condicao", columnList = "condicao"),
    @Index(name = "idx_bem_data_aquisicao", columnList = "data_aquisicao")
})
public class Bem extends EntidadeBase {

    @NotBlank(message = "Número de tombamento é obrigatório")
    @Size(min = 3, max = 50, message = "Número de tombamento deve ter entre 3 e 50 caracteres")
    @Column(name = "numero_tombamento", nullable = false, unique = true, length = 50)
    private String numeroTombamento;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 150, message = "Nome deve ter entre 2 e 150 caracteres")
    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(min = 10, max = 500, message = "Descrição deve ter entre 10 e 500 caracteres")
    @Column(name = "descricao", nullable = false, length = 500)
    private String descricao;

    @Size(max = 1000, message = "Observações deve ter no máximo 1000 caracteres")
    @Column(name = "observacoes", length = 1000)
    private String observacoes;

    @NotNull(message = "Categoria é obrigatória")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id", nullable = false, foreignKey = @ForeignKey(name = "fk_bem_categoria"))
    private Categoria categoria;

    @NotNull(message = "Localização atual é obrigatória")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "localizacao_atual_id", nullable = false, foreignKey = @ForeignKey(name = "fk_bem_localizacao"))
    private Localizacao localizacaoAtual;

    @NotNull(message = "Status é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private StatusBem status = StatusBem.ATIVO;

    @NotNull(message = "Condição é obrigatória")
    @Enumerated(EnumType.STRING)
    @Column(name = "condicao", nullable = false, length = 50)
    private CondicaoBem condicao = CondicaoBem.BOM;

    @NotNull(message = "Data de aquisição é obrigatória")
    @Column(name = "data_aquisicao", nullable = false)
    private LocalDate dataAquisicao;

    @NotNull(message = "Valor de aquisição é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Valor de aquisição deve ser maior que zero")
    @Digits(integer = 10, fraction = 2, message = "Valor de aquisição deve ter no máximo 10 dígitos inteiros e 2 decimais")
    @Column(name = "valor_aquisicao", nullable = false, precision = 12, scale = 2)
    private BigDecimal valorAquisicao;

    @DecimalMin(value = "0.0", message = "Valor atual não pode ser negativo")
    @Digits(integer = 10, fraction = 2, message = "Valor atual deve ter no máximo 10 dígitos inteiros e 2 decimais")
    @Column(name = "valor_atual", precision = 12, scale = 2)
    private BigDecimal valorAtual;

    @Size(max = 100, message = "Marca deve ter no máximo 100 caracteres")
    @Column(name = "marca", length = 100)
    private String marca;

    @Size(max = 100, message = "Modelo deve ter no máximo 100 caracteres")
    @Column(name = "modelo", length = 100)
    private String modelo;

    @Size(max = 50, message = "Número de série deve ter no máximo 50 caracteres")
    @Column(name = "numero_serie", length = 50)
    private String numeroSerie;

    @Size(max = 100, message = "Fornecedor deve ter no máximo 100 caracteres")
    @Column(name = "fornecedor", length = 100)
    private String fornecedor;

    @Size(max = 100, message = "Fabricante deve ter no máximo 100 caracteres")
    @Column(name = "fabricante", length = 100)
    private String fabricante;

    @Column(name = "ano_fabricacao")
    private Integer anoFabricacao;

    @Column(name = "garantia_ate")
    private LocalDate garantiaAte;

    @Size(max = 200, message = "Localização física deve ter no máximo 200 caracteres")
    @Column(name = "localizacao_fisica", length = 200)
    private String localizacaoFisica;

    @Size(max = 100, message = "Responsável deve ter no máximo 100 caracteres")
    @Column(name = "responsavel", length = 100)
    private String responsavel;

    @Size(max = 100, message = "Departamento deve ter no máximo 100 caracteres")
    @Column(name = "departamento", length = 100)
    private String departamento;

    @Size(max = 100, message = "Centro de custo deve ter no máximo 100 caracteres")
    @Column(name = "centro_custo", length = 100)
    private String centroCusto;

    @Column(name = "vida_util_anos")
    @Min(value = 1, message = "Vida útil deve ser pelo menos 1 ano")
    @Max(value = 100, message = "Vida útil não pode exceder 100 anos")
    private Integer vidaUtilAnos;

    @Column(name = "taxa_depreciacao")
    @DecimalMin(value = "0.0", message = "Taxa de depreciação não pode ser negativa")
    @DecimalMax(value = "100.0", message = "Taxa de depreciação não pode exceder 100%")
    @Digits(integer = 3, fraction = 2, message = "Taxa de depreciação deve ter no máximo 3 dígitos inteiros e 2 decimais")
    private BigDecimal taxaDepreciacao;

    @Column(name = "data_ultima_manutencao")
    private LocalDate dataUltimaManutencao;

    @Column(name = "data_proxima_manutencao")
    private LocalDate dataProximaManutencao;

    @Size(max = 500, message = "Observações de manutenção deve ter no máximo 500 caracteres")
    @Column(name = "observacoes_manutencao", length = 500)
    private String observacoesManutencao;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    // Relacionamentos
    @OneToMany(mappedBy = "bem", cascade = CascadeType.ALL, orphanRemoval = true)
    @BatchSize(size = 20)
    @Fetch(FetchMode.SUBSELECT)
    private List<Movimentacao> movimentacoes = new ArrayList<>();

    @ManyToMany(mappedBy = "bensInventariados", fetch = FetchType.LAZY)
    private List<Inventario> inventarios = new ArrayList<>();

    @OneToMany(mappedBy = "bem", cascade = CascadeType.ALL, orphanRemoval = true)
    @BatchSize(size = 20)
    @Fetch(FetchMode.SUBSELECT)
    private List<Manutencao> manutencoes = new ArrayList<>();

    @OneToMany(mappedBy = "bem", cascade = CascadeType.ALL, orphanRemoval = true)
    @BatchSize(size = 20)
    @Fetch(FetchMode.SUBSELECT)
    private List<Baixa> baixas = new ArrayList<>();

    // Construtores
    public Bem() {
        super();
    }

    public Bem(String nome, String numeroTombamento, String descricao, Categoria categoria, Localizacao localizacaoAtual,
               LocalDate dataAquisicao, BigDecimal valorAquisicao) {
        this();
        this.nome = nome;
        this.numeroTombamento = numeroTombamento;
        this.descricao = descricao;
        this.categoria = categoria;
        this.localizacaoAtual = localizacaoAtual;
        this.dataAquisicao = dataAquisicao;
        this.valorAquisicao = valorAquisicao;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroTombamento() {
        return numeroTombamento;
    }

    public void setNumeroTombamento(String numeroTombamento) {
        this.numeroTombamento = numeroTombamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public LocalDate getGarantiaAte() {
        return garantiaAte;
    }

    public void setGarantiaAte(LocalDate garantiaAte) {
        this.garantiaAte = garantiaAte;
    }

    public String getLocalizacaoFisica() {
        return localizacaoFisica;
    }

    public void setLocalizacaoFisica(String localizacaoFisica) {
        this.localizacaoFisica = localizacaoFisica;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(String centroCusto) {
        this.centroCusto = centroCusto;
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

    public String getObservacoesManutencao() {
        return observacoesManutencao;
    }

    public void setObservacoesManutencao(String observacoesManutencao) {
        this.observacoesManutencao = observacoesManutencao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    // Relacionamentos
    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    public List<Inventario> getInventarios() {
        return inventarios;
    }

    public void setInventarios(List<Inventario> inventarios) {
        this.inventarios = inventarios;
    }

    public List<Manutencao> getManutencoes() {
        return manutencoes;
    }

    public void setManutencoes(List<Manutencao> manutencoes) {
        this.manutencoes = manutencoes;
    }

    public List<Baixa> getBaixas() {
        return baixas;
    }

    public void setBaixas(List<Baixa> baixas) {
        this.baixas = baixas;
    }

    // Métodos de negócio
    public boolean isAtivo() {
        return ativo != null && ativo;
    }

    public boolean isInativo() {
        return !isAtivo();
    }

    public boolean isDisponivel() {
        return StatusBem.ATIVO.equals(status) && isAtivo();
    }

    public boolean isEmManutencao() {
        return StatusBem.EM_MANUTENCAO.equals(status);
    }

    public boolean isBaixado() {
        return StatusBem.BAIXADO.equals(status);
    }

    public boolean isEmInventario() {
        return StatusBem.INATIVO.equals(status);
    }

    public boolean isEmMovimentacao() {
        return StatusBem.EM_TRANSITO.equals(status);
    }

    public boolean isEmReserva() {
        return StatusBem.RESERVA.equals(status);
    }

    public boolean isEmprestado() {
        return StatusBem.ATIVO.equals(status);
    }

    public boolean isRoubado() {
        return StatusBem.ROUBADO.equals(status);
    }

    public boolean isPerdido() {
        return StatusBem.EXTRAVIADO.equals(status);
    }

    public boolean isDanificado() {
        return StatusBem.DANIFICADO.equals(status);
    }

    public boolean isObsoleto() {
        return StatusBem.OBSOLETO.equals(status);
    }

    public boolean isEmGarantia() {
        return garantiaAte != null && garantiaAte.isAfter(LocalDate.now());
    }

    public boolean precisaManutencao() {
        return dataProximaManutencao != null && dataProximaManutencao.isBefore(LocalDate.now());
    }

    public boolean podeSerMovimentado() {
        return isDisponivel() && !isEmManutencao() && !isEmInventario();
    }

    public boolean podeSerBaixado() {
        return isDisponivel() || isEmManutencao();
    }

    // Métodos de relacionamento
    public void adicionarMovimentacao(Movimentacao movimentacao) {
        movimentacoes.add(movimentacao);
        movimentacao.setBem(this);
    }

    public void removerMovimentacao(Movimentacao movimentacao) {
        movimentacoes.remove(movimentacao);
        movimentacao.setBem(null);
    }

    public void adicionarInventario(Inventario inventario) {
        inventarios.add(inventario);
    }

    public void removerInventario(Inventario inventario) {
        inventarios.remove(inventario);
    }

    public void adicionarManutencao(Manutencao manutencao) {
        manutencoes.add(manutencao);
        manutencao.setBem(this);
    }

    public void removerManutencao(Manutencao manutencao) {
        manutencoes.remove(manutencao);
        manutencao.setBem(null);
    }

    public void adicionarBaixa(Baixa baixa) {
        baixas.add(baixa);
        baixa.setBem(this);
    }

    public void removerBaixa(Baixa baixa) {
        baixas.remove(baixa);
        baixa.setBem(null);
    }

    // Equals e HashCode
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Bem bem = (Bem) obj;
        return Objects.equals(numeroTombamento, bem.numeroTombamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numeroTombamento);
    }

    @Override
    public String toString() {
        return "Bem{" +
                "id=" + getId() +
                ", nome='" + nome + '\'' +
                ", numeroTombamento='" + numeroTombamento + '\'' +
                ", descricao='" + descricao + '\'' +
                ", status=" + status +
                ", condicao=" + condicao +
                ", ativo=" + ativo +
                '}';
    }
}

