package com.manus.patrimonio.enums;

public enum StatusInventario {
    PLANEJADO("Planejado", "Inventário planejado mas não iniciado"),
    EM_ANDAMENTO("Em Andamento", "Inventário em execução"),
    PAUSADO("Pausado", "Inventário temporariamente interrompido"),
    CONCLUIDO("Concluído", "Inventário finalizado com sucesso"),
    CANCELADO("Cancelado", "Inventário cancelado"),
    EM_REVISAO("Em Revisão", "Inventário concluído, aguardando revisão"),
    APROVADO("Aprovado", "Inventário revisado e aprovado"),
    REPROVADO("Reprovado", "Inventário revisado e reprovado");

    private final String descricao;
    private final String explicacao;

    StatusInventario(String descricao, String explicacao) {
        this.descricao = descricao;
        this.explicacao = explicacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getExplicacao() {
        return explicacao;
    }

    public boolean isAtivo() {
        return this == PLANEJADO || this == EM_ANDAMENTO || this == PAUSADO;
    }

    public boolean isFinalizado() {
        return this == CONCLUIDO || this == APROVADO || this == REPROVADO;
    }

    public boolean isRevisao() {
        return this == EM_REVISAO;
    }
}

