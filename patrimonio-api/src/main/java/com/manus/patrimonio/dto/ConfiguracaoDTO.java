package com.manus.patrimonio.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class ConfiguracaoDTO {

    private Long id;

    @NotBlank(message = "Chave é obrigatória")
    @Size(min = 3, max = 100, message = "Chave deve ter entre 3 e 100 caracteres")
    private String chave;

    @Size(max = 1000, message = "Valor deve ter no máximo 1000 caracteres")
    private String valor;

    @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
    private String descricao;

    @NotBlank(message = "Tipo é obrigatório")
    @Size(min = 2, max = 50, message = "Tipo deve ter entre 2 e 50 caracteres")
    private String tipo;

    private Boolean editavel = true;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private Long versao;

    // Constructors
    public ConfiguracaoDTO() {}

    public ConfiguracaoDTO(String chave, String valor, String descricao, String tipo) {
        this.chave = chave;
        this.valor = valor;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getEditavel() {
        return editavel;
    }

    public void setEditavel(Boolean editavel) {
        this.editavel = editavel;
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

    public Long getVersao() {
        return versao;
    }

    public void setVersao(Long versao) {
        this.versao = versao;
    }

    // Helper methods
    public boolean isEditavel() {
        return editavel != null && editavel;
    }

    public boolean isString() {
        return "STRING".equals(tipo);
    }

    public boolean isInteger() {
        return "INTEGER".equals(tipo);
    }

    public boolean isDecimal() {
        return "DECIMAL".equals(tipo);
    }

    public boolean isBoolean() {
        return "BOOLEAN".equals(tipo);
    }

    public boolean isDate() {
        return "DATE".equals(tipo);
    }

    public Integer getValorAsInteger() {
        if (isInteger() && valor != null) {
            try {
                return Integer.parseInt(valor);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    public Boolean getValorAsBoolean() {
        if (isBoolean() && valor != null) {
            return Boolean.parseBoolean(valor);
        }
        return null;
    }

    public Double getValorAsDecimal() {
        if (isDecimal() && valor != null) {
            try {
                return Double.parseDouble(valor);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ConfiguracaoDTO{" +
                "id=" + id +
                ", chave='" + chave + '\'' +
                ", valor='" + valor + '\'' +
                ", tipo='" + tipo + '\'' +
                ", editavel=" + editavel +
                '}';
    }
}
