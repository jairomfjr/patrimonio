package com.manus.patrimonio.enums;

public enum CondicaoBem {
    EXCELENTE("Excelente", "Como novo, sem desgaste"),
    BOM("Bom", "Pequenos sinais de uso, funcionando perfeitamente"),
    REGULAR("Regular", "Sinais de uso visíveis, funcionando adequadamente"),
    RUIM("Ruim", "Desgaste significativo, funcionamento limitado"),
    CRITICO("Crítico", "Muito desgastado, funcionamento precário"),
    INUTILIZAVEL("Inutilizável", "Não funciona, sem possibilidade de reparo");

    private final String descricao;
    private final String explicacao;

    CondicaoBem(String descricao, String explicacao) {
        this.descricao = descricao;
        this.explicacao = explicacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getExplicacao() {
        return explicacao;
    }

    public boolean isOperacional() {
        return this == EXCELENTE || this == BOM || this == REGULAR;
    }

    public boolean isManutencaoNecessaria() {
        return this == RUIM || this == CRITICO;
    }

    public boolean isBaixaRecomendada() {
        return this == INUTILIZAVEL;
    }
}

