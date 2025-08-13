package com.manus.patrimonio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "configuracoes")
public class Configuracao extends EntidadeBase {

    @NotBlank(message = "Chave é obrigatória")
    @Size(max = 100, message = "Chave deve ter no máximo 100 caracteres")
    @Column(name = "chave", nullable = false, length = 100, unique = true)
    private String chave;

    @Column(name = "valor")
    private String valor;

    @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
    @Column(name = "descricao", length = 500)
    private String descricao;

    @NotBlank(message = "Tipo é obrigatório")
    @Size(max = 50, message = "Tipo deve ter no máximo 50 caracteres")
    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "editavel")
    private Boolean editavel = true;

    @Column(name = "valor_padrao")
    private String valorPadrao;

    @Column(name = "validacao_regex")
    private String validacaoRegex;

    @Column(name = "valores_permitidos")
    private String valoresPermitidos;

    @Column(name = "valor_minimo")
    private String valorMinimo;

    @Column(name = "valor_maximo")
    private String valorMaximo;

    @Column(name = "unidade_medida")
    private String unidadeMedida;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "subcategoria")
    private String subcategoria;

    @Column(name = "ordem_exibicao")
    private Integer ordemExibicao;

    @Column(name = "visivel_interface")
    private Boolean visivelInterface = true;

    @Column(name = "requer_restart")
    private Boolean requerRestart = false;

    @Column(name = "versao_configuracao")
    private String versaoConfiguracao;

    // Constructors
    public Configuracao() {}

    public Configuracao(String chave, String valor, String descricao, String tipo) {
        this.chave = chave;
        this.valor = valor;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    // Getters and Setters
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

    public String getValorPadrao() {
        return valorPadrao;
    }

    public void setValorPadrao(String valorPadrao) {
        this.valorPadrao = valorPadrao;
    }

    public String getValidacaoRegex() {
        return validacaoRegex;
    }

    public void setValidacaoRegex(String validacaoRegex) {
        this.validacaoRegex = validacaoRegex;
    }

    public String getValoresPermitidos() {
        return valoresPermitidos;
    }

    public void setValoresPermitidos(String valoresPermitidos) {
        this.valoresPermitidos = valoresPermitidos;
    }

    public String getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(String valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public String getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(String valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    public Integer getOrdemExibicao() {
        return ordemExibicao;
    }

    public void setOrdemExibicao(Integer ordemExibicao) {
        this.ordemExibicao = ordemExibicao;
    }

    public Boolean getVisivelInterface() {
        return visivelInterface;
    }

    public void setVisivelInterface(Boolean visivelInterface) {
        this.visivelInterface = visivelInterface;
    }

    public Boolean getRequerRestart() {
        return requerRestart;
    }

    public void setRequerRestart(Boolean requerRestart) {
        this.requerRestart = requerRestart;
    }

    public String getVersaoConfiguracao() {
        return versaoConfiguracao;
    }

    public void setVersaoConfiguracao(String versaoConfiguracao) {
        this.versaoConfiguracao = versaoConfiguracao;
    }

    // Business methods
    public boolean isEditavel() {
        return editavel != null && editavel;
    }

    public boolean isVisivelInterface() {
        return visivelInterface != null && visivelInterface;
    }

    public boolean isRequerRestart() {
        return requerRestart != null && requerRestart;
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

    public boolean isDateTime() {
        return "DATETIME".equals(tipo);
    }

    public boolean isJson() {
        return "JSON".equals(tipo);
    }

    public boolean isPassword() {
        return "PASSWORD".equals(tipo);
    }

    public boolean isEmail() {
        return "EMAIL".equals(tipo);
    }

    public boolean isUrl() {
        return "URL".equals(tipo);
    }

    public boolean isFile() {
        return "FILE".equals(tipo);
    }

    public boolean temValorPadrao() {
        return valorPadrao != null && !valorPadrao.isEmpty();
    }

    public boolean temValidacao() {
        return validacaoRegex != null && !validacaoRegex.isEmpty();
    }

    public boolean temValoresPermitidos() {
        return valoresPermitidos != null && !valoresPermitidos.isEmpty();
    }

    public boolean temLimites() {
        return (valorMinimo != null && !valorMinimo.isEmpty()) ||
               (valorMaximo != null && !valorMaximo.isEmpty());
    }

    public boolean temUnidadeMedida() {
        return unidadeMedida != null && !unidadeMedida.isEmpty();
    }

    public boolean temCategoria() {
        return categoria != null && !categoria.isEmpty();
    }

    public boolean temSubcategoria() {
        return subcategoria != null && !subcategoria.isEmpty();
    }

    public String getValorComUnidade() {
        if (temUnidadeMedida() && valor != null) {
            return valor + " " + unidadeMedida;
        }
        return valor;
    }

    public void resetarParaPadrao() {
        if (temValorPadrao()) {
            this.valor = this.valorPadrao;
        }
    }

    public boolean isValorValido() {
        if (valor == null) {
            return true; // Valor nulo é válido se não for obrigatório
        }

        // Validação por regex se definida
        if (temValidacao() && !valor.matches(validacaoRegex)) {
            return false;
        }

        // Validação de valores permitidos
        if (temValoresPermitidos()) {
            String[] valores = valoresPermitidos.split(",");
            boolean encontrado = false;
            for (String valorPermitido : valores) {
                if (valorPermitido.trim().equals(valor.trim())) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                return false;
            }
        }

        // Validação de limites numéricos
        if (isInteger() || isDecimal()) {
            try {
                double valorNumerico = Double.parseDouble(valor);
                if (valorMinimo != null && !valorMinimo.isEmpty()) {
                    double min = Double.parseDouble(valorMinimo);
                    if (valorNumerico < min) {
                        return false;
                    }
                }
                if (valorMaximo != null && !valorMaximo.isEmpty()) {
                    double max = Double.parseDouble(valorMaximo);
                    if (valorNumerico > max) {
                        return false;
                    }
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "Configuracao{" +
                "id=" + getId() +
                ", chave='" + chave + '\'' +
                ", valor='" + valor + '\'' +
                ", tipo='" + tipo + '\'' +
                ", editavel=" + editavel +
                '}';
    }
}
