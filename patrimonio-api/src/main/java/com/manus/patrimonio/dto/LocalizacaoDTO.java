package com.manus.patrimonio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class LocalizacaoDTO {

    private Long id;

    @NotBlank(message = "Nome da localização é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @Size(max = 200, message = "Endereço deve ter no máximo 200 caracteres")
    private String endereco;

    @Size(max = 100, message = "Nome do responsável deve ter no máximo 100 caracteres")
    private String responsavel;

    @Size(max = 50, message = "Contato deve ter no máximo 50 caracteres")
    private String contato;

    @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
    private String descricao;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private Long quantidadeBens;

    // Constructors
    public LocalizacaoDTO() {}

    public LocalizacaoDTO(String nome, String endereco, String responsavel, String contato, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.responsavel = responsavel;
        this.contato = contato;
        this.descricao = descricao;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    @Override
    public String toString() {
        return "LocalizacaoDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", responsavel='" + responsavel + '\'' +
                ", contato='" + contato + '\'' +
                ", quantidadeBens=" + quantidadeBens +
                '}';
    }
}

