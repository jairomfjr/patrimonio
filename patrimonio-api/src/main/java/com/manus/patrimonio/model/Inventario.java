package com.manus.patrimonio.model;

import com.manus.patrimonio.enums.StatusInventario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inventarios")
public class Inventario extends EntidadeBase {

    @NotBlank(message = "Nome do inventário é obrigatório")
    @Size(min = 2, max = 150, message = "Nome deve ter entre 2 e 150 caracteres")
    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @NotNull(message = "Data de início é obrigatória")
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @NotBlank(message = "Responsável é obrigatório")
    @Size(min = 2, max = 100, message = "Nome do responsável deve ter entre 2 e 100 caracteres")
    @Column(name = "responsavel", nullable = false, length = 100)
    private String responsavel;

    @NotNull(message = "Status é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusInventario status;

    @Size(max = 1000, message = "Observações devem ter no máximo 1000 caracteres")
    @Column(name = "observacoes", length = 1000)
    private String observacoes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "inventario_bens",
        joinColumns = @JoinColumn(name = "inventario_id"),
        inverseJoinColumns = @JoinColumn(name = "bem_id")
    )
    private List<Bem> bensInventariados = new ArrayList<>();

    // Constructors
    public Inventario() {}

    public Inventario(String nome, LocalDate dataInicio, String responsavel, StatusInventario status) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.responsavel = responsavel;
        this.status = status;
    }

    // Getters and Setters
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

    public List<Bem> getBensInventariados() {
        return bensInventariados;
    }

    public void setBensInventariados(List<Bem> bensInventariados) {
        this.bensInventariados = bensInventariados;
    }

    // Helper methods
    public void adicionarBem(Bem bem) {
        bensInventariados.add(bem);
        bem.getInventarios().add(this);
    }

    public void removerBem(Bem bem) {
        bensInventariados.remove(bem);
        bem.getInventarios().remove(this);
    }

    public void concluirInventario() {
        this.status = StatusInventario.CONCLUIDO;
        this.dataFim = LocalDate.now();
    }

    public void cancelarInventario() {
        this.status = StatusInventario.CANCELADO;
        this.dataFim = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Inventario{" +
                "id=" + getId() +
                ", nome='" + nome + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", responsavel='" + responsavel + '\'' +
                ", status=" + status +
                '}';
    }
}

