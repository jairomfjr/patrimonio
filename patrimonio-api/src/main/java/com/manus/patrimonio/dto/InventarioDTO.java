package com.manus.patrimonio.dto;

import com.manus.patrimonio.enums.StatusInventario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class InventarioDTO {

    private Long id;

    @NotBlank(message = "Nome do inventário é obrigatório")
    @Size(min = 2, max = 150, message = "Nome deve ter entre 2 e 150 caracteres")
    private String nome;

    @NotNull(message = "Data de início é obrigatória")
    private LocalDate dataInicio;

    private LocalDate dataFim;

    @NotBlank(message = "Responsável é obrigatório")
    @Size(min = 2, max = 100, message = "Nome do responsável deve ter entre 2 e 100 caracteres")
    private String responsavel;

    @NotNull(message = "Status é obrigatório")
    private StatusInventario status;

    @Size(max = 1000, message = "Observações devem ter no máximo 1000 caracteres")
    private String observacoes;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private Long quantidadeBens;
    private List<Long> bensInventariadosIds;

    // Constructors
    public InventarioDTO() {}

    public InventarioDTO(String nome, LocalDate dataInicio, String responsavel, StatusInventario status) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.responsavel = responsavel;
        this.status = status;
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

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public StatusInventario getStatus() {
        return status;
    }

    public void setStatus(StatusInventario status) {
        this.status = status;
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

    public Long getQuantidadeBens() {
        return quantidadeBens;
    }

    public void setQuantidadeBens(Long quantidadeBens) {
        this.quantidadeBens = quantidadeBens;
    }

    public List<Long> getBensInventariadosIds() {
        return bensInventariadosIds;
    }

    public void setBensInventariadosIds(List<Long> bensInventariadosIds) {
        this.bensInventariadosIds = bensInventariadosIds;
    }

    @Override
    public String toString() {
        return "InventarioDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", responsavel='" + responsavel + '\'' +
                ", status=" + status +
                ", quantidadeBens=" + quantidadeBens +
                '}';
    }
}

