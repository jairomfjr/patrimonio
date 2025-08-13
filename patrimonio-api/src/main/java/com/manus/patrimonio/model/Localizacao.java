package com.manus.patrimonio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "localizacoes")
public class Localizacao extends EntidadeBase {

    @NotBlank(message = "Nome da localização é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    @Column(name = "nome", nullable = false, length = 100, unique = true)
    private String nome;

    @Size(max = 200, message = "Endereço deve ter no máximo 200 caracteres")
    @Column(name = "endereco", length = 200)
    private String endereco;

    @Size(max = 100, message = "Nome do responsável deve ter no máximo 100 caracteres")
    @Column(name = "responsavel", length = 100)
    private String responsavel;

    @Size(max = 50, message = "Contato deve ter no máximo 50 caracteres")
    @Column(name = "contato", length = 50)
    private String contato;

    @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
    @Column(name = "descricao", length = 500)
    private String descricao;

    @OneToMany(mappedBy = "localizacaoAtual", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Bem> bensAtuais = new ArrayList<>();

    @OneToMany(mappedBy = "localizacaoOrigem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Movimentacao> movimentacaesOrigem = new ArrayList<>();

    @OneToMany(mappedBy = "localizacaoDestino", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Movimentacao> movimentacaesDestino = new ArrayList<>();

    // Constructors
    public Localizacao() {}

    public Localizacao(String nome, String endereco, String responsavel, String contato, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.responsavel = responsavel;
        this.contato = contato;
        this.descricao = descricao;
    }

    // Getters and Setters
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

    public List<Bem> getBensAtuais() {
        return bensAtuais;
    }

    public void setBensAtuais(List<Bem> bensAtuais) {
        this.bensAtuais = bensAtuais;
    }

    public List<Movimentacao> getMovimentacaesOrigem() {
        return movimentacaesOrigem;
    }

    public void setMovimentacaesOrigem(List<Movimentacao> movimentacaesOrigem) {
        this.movimentacaesOrigem = movimentacaesOrigem;
    }

    public List<Movimentacao> getMovimentacaesDestino() {
        return movimentacaesDestino;
    }

    public void setMovimentacaesDestino(List<Movimentacao> movimentacaesDestino) {
        this.movimentacaesDestino = movimentacaesDestino;
    }

    // Helper methods
    public void adicionarBem(Bem bem) {
        bensAtuais.add(bem);
        bem.setLocalizacaoAtual(this);
    }

    public void removerBem(Bem bem) {
        bensAtuais.remove(bem);
        bem.setLocalizacaoAtual(null);
    }

    @Override
    public String toString() {
        return "Localizacao{" +
                "id=" + getId() +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", responsavel='" + responsavel + '\'' +
                ", contato='" + contato + '\'' +
                '}';
    }
}

