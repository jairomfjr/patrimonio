package com.manus.patrimonio.enums;

public enum StatusManutencao {
    AGENDADA("Agendada", "Manutenção agendada para data futura"),
    EM_ANDAMENTO("Em Andamento", "Manutenção sendo executada"),
    PAUSADA("Pausada", "Manutenção temporariamente interrompida"),
    CONCLUIDA("Concluída", "Manutenção finalizada com sucesso"),
    CANCELADA("Cancelada", "Manutenção cancelada"),
    AGUARDANDO_PECAS("Aguardando Peças", "Manutenção aguardando chegada de peças"),
    AGUARDANDO_APROVACAO("Aguardando Aprovação", "Manutenção aguardando aprovação de custos"),
    EM_ANALISE("Em Análise", "Manutenção em análise técnica"),
    REPROVADA("Reprovada", "Manutenção reprovada"),
    EM_TESTE("Em Teste", "Manutenção concluída, equipamento em teste");

    private final String descricao;
    private final String explicacao;

    StatusManutencao(String descricao, String explicacao) {
        this.descricao = descricao;
        this.explicacao = explicacao;
    }

    public String getDescricacao() {
        return descricao;
    }

    public String getExplicacao() {
        return explicacao;
    }

    public boolean isAtiva() {
        return this == AGENDADA || this == EM_ANDAMENTO || this == PAUSADA || this == AGUARDANDO_PECAS || 
               this == AGUARDANDO_APROVACAO || this == EM_ANALISE || this == EM_TESTE;
    }

    public boolean isFinalizada() {
        return this == CONCLUIDA || this == CANCELADA || this == REPROVADA;
    }

    public boolean isAguardando() {
        return this == AGUARDANDO_PECAS || this == AGUARDANDO_APROVACAO || this == EM_ANALISE;
    }
}
