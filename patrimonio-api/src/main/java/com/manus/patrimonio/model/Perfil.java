package com.manus.patrimonio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "perfis")
public class Perfil extends EntidadeBase {

    @NotBlank(message = "Nome do perfil é obrigatório")
    @Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 50 caracteres")
    @Column(name = "nome", nullable = false, length = 50, unique = true)
    private String nome;

    @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
    @Column(name = "descricao", length = 500)
    private String descricao;

    @Column(name = "ativo")
    private Boolean ativo = true;

    @Column(name = "nivel_acesso")
    private Integer nivelAcesso = 1;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
        name = "perfil_permissoes",
        joinColumns = @JoinColumn(name = "perfil_id")
    )
    @Column(name = "permissao")
    private List<String> permissoes = new ArrayList<>();

    // Constructors
    public Perfil() {}

    public Perfil(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Perfil(String nome, String descricao, Integer nivelAcesso) {
        this.nome = nome;
        this.descricao = descricao;
        this.nivelAcesso = nivelAcesso;
    }

    // Getters and Setters
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

    // Business methods
    public boolean isAtivo() {
        return ativo != null && ativo;
    }

    public boolean hasPermissao(String permissao) {
        return permissoes.contains(permissao);
    }

    public void adicionarPermissao(String permissao) {
        if (!permissoes.contains(permissao)) {
            permissoes.add(permissao);
        }
    }

    public void removerPermissao(String permissao) {
        permissoes.remove(permissao);
    }

    public boolean isAdmin() {
        return "ADMIN".equals(nome) || nivelAcesso >= 100;
    }

    public boolean isGestor() {
        return "GESTOR".equals(nome) || nivelAcesso >= 50;
    }

    public boolean isOperador() {
        return "OPERADOR".equals(nome) || nivelAcesso >= 20;
    }

    public boolean isConsulta() {
        return "CONSULTA".equals(nome) || nivelAcesso >= 10;
    }

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "id=" + getId() +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", ativo=" + ativo +
                ", nivelAcesso=" + nivelAcesso +
                '}';
    }
}
