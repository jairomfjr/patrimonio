package com.manus.patrimonio.dto;

import jakarta.validation.constraints.*;

import java.util.List;

public class PerfilDTO {

    private Long id;

    @NotBlank(message = "Nome do perfil é obrigatório")
    @Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 50 caracteres")
    private String nome;

    @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
    private String descricao;

    private Boolean ativo = true;

    @Min(value = 1, message = "Nível de acesso deve ser pelo menos 1")
    @Max(value = 100, message = "Nível de acesso não pode exceder 100")
    private Integer nivelAcesso = 1;

    private List<String> permissoes;

    // Campos de relacionamento (para exibição)
    private Long quantidadeUsuarios;
    private String statusDescricao;

    // Constructors
    public PerfilDTO() {}

    public PerfilDTO(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public PerfilDTO(String nome, String descricao, Integer nivelAcesso) {
        this.nome = nome;
        this.descricao = descricao;
        this.nivelAcesso = nivelAcesso;
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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(Integer nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public List<String> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<String> permissoes) {
        this.permissoes = permissoes;
    }

    public Long getQuantidadeUsuarios() {
        return quantidadeUsuarios;
    }

    public void setQuantidadeUsuarios(Long quantidadeUsuarios) {
        this.quantidadeUsuarios = quantidadeUsuarios;
    }

    public String getStatusDescricao() {
        return statusDescricao;
    }

    public void setStatusDescricao(String statusDescricao) {
        this.statusDescricao = statusDescricao;
    }

    // Business methods
    public boolean isAtivo() {
        return ativo != null && ativo;
    }

    public boolean isAdmin() {
        return "ADMIN".equals(nome) || (nivelAcesso != null && nivelAcesso >= 100);
    }

    public boolean isGestor() {
        return "GESTOR".equals(nome) || (nivelAcesso != null && nivelAcesso >= 50);
    }

    public boolean isOperador() {
        return "OPERADOR".equals(nome) || (nivelAcesso != null && nivelAcesso >= 20);
    }

    public boolean isConsulta() {
        return "CONSULTA".equals(nome) || (nivelAcesso != null && nivelAcesso >= 10);
    }

    public boolean hasPermissao(String permissao) {
        return permissoes != null && permissoes.contains(permissao);
    }

    public void adicionarPermissao(String permissao) {
        if (permissoes == null) {
            permissoes = new java.util.ArrayList<>();
        }
        if (!permissoes.contains(permissao)) {
            permissoes.add(permissao);
        }
    }

    public void removerPermissao(String permissao) {
        if (permissoes != null) {
            permissoes.remove(permissao);
        }
    }

    @Override
    public String toString() {
        return "PerfilDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", ativo=" + ativo +
                ", nivelAcesso=" + nivelAcesso +
                '}';
    }
}
