package com.manus.patrimonio.enums;

public enum TipoManutencao {
    PREVENTIVA("Preventiva", "Manutenção programada para prevenir falhas"),
    CORRETIVA("Corretiva", "Manutenção para corrigir falhas existentes"),
    PREDICTIVA("Preditiva", "Manutenção baseada em monitoramento e análise"),
    EMERGENCIA("Emergência", "Manutenção urgente para evitar paradas"),
    CALIBRACAO("Calibração", "Ajuste e calibração de equipamentos"),
    LIMPEZA("Limpeza", "Limpeza e higienização de equipamentos"),
    INSPECAO("Inspeção", "Verificação e inspeção de equipamentos"),
    REPARO("Reparo", "Reparo de componentes danificados"),
    SUBSTITUICAO("Substituição", "Substituição de componentes"),
    MODERNIZACAO("Modernização", "Atualização e modernização de equipamentos");

    private final String descricao;
    private final String explicacao;

    TipoManutencao(String descricao, String explicacao) {
        this.descricao = descricao;
        this.explicacao = explicacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getExplicacao() {
        return explicacao;
    }

    public boolean isProgramada() {
        return this == PREVENTIVA || this == PREDICTIVA || this == CALIBRACAO || this == LIMPEZA || this == INSPECAO;
    }

    public boolean isCorretiva() {
        return this == CORRETIVA || this == EMERGENCIA || this == REPARO || this == SUBSTITUICAO;
    }

    public boolean isEspecializada() {
        return this == MODERNIZACAO || this == CALIBRACAO;
    }
}
