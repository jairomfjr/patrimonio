package com.manus.patrimonio.model;

import com.manus.patrimonio.enums.TipoMovimentacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacoes")
public class Movimentacao extends EntidadeBase {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bem_id", nullable = false)
    @NotNull(message = "Bem é obrigatório")
    private Bem bem;

    @NotNull(message = "Data da movimentação é obrigatória")
    @Column(name = "data_movimentacao", nullable = false)
    private LocalDateTime dataMovimentacao;

    @NotNull(message = "Tipo de movimentação é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimentacao", nullable = false)
    private TipoMovimentacao tipoMovimentacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "localizacao_origem_id")
    private Localizacao localizacaoOrigem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "localizacao_destino_id")
    private Localizacao localizacaoDestino;

    @NotBlank(message = "Responsável pela movimentação é obrigatório")
    @Size(min = 2, max = 100, message = "Nome do responsável deve ter entre 2 e 100 caracteres")
    @Column(name = "responsavel_movimentacao", nullable = false, length = 100)
    private String responsavelMovimentacao;

    @Size(max = 1000, message = "Observações devem ter no máximo 1000 caracteres")
    @Column(name = "observacoes", length = 1000)
    private String observacoes;

    // Constructors
    public Movimentacao() {}

    public Movimentacao(Bem bem, LocalDateTime dataMovimentacao, TipoMovimentacao tipoMovimentacao,
                       Localizacao localizacaoOrigem, Localizacao localizacaoDestino, 
                       String responsavelMovimentacao) {
        this.bem = bem;
        this.dataMovimentacao = dataMovimentacao;
        this.tipoMovimentacao = tipoMovimentacao;
        this.localizacaoOrigem = localizacaoOrigem;
        this.localizacaoDestino = localizacaoDestino;
        this.responsavelMovimentacao = responsavelMovimentacao;
    }

    // Getters and Setters
    public Bem getBem() {
        return bem;
    }

    public void setBem(Bem bem) {
        this.bem = bem;
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public Localizacao getLocalizacaoOrigem() {
        return localizacaoOrigem;
    }

    public void setLocalizacaoOrigem(Localizacao localizacaoOrigem) {
        this.localizacaoOrigem = localizacaoOrigem;
    }

    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }

    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }

    public String getResponsavelMovimentacao() {
        return responsavelMovimentacao;
    }

    public void setResponsavelMovimentacao(String responsavelMovimentacao) {
        this.responsavelMovimentacao = responsavelMovimentacao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public String toString() {
        return "Movimentacao{" +
                "id=" + getId() +
                ", dataMovimentacao=" + dataMovimentacao +
                ", tipoMovimentacao=" + tipoMovimentacao +
                ", responsavelMovimentacao='" + responsavelMovimentacao + '\'' +
                '}';
    }
}

