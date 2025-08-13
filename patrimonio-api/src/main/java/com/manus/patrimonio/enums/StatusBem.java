package com.manus.patrimonio.enums;

public enum StatusBem {
    ATIVO("Ativo", "Bem em uso normal"),
    INATIVO("Inativo", "Bem temporariamente fora de uso"),
    EM_MANUTENCAO("Em Manutenção", "Bem em processo de manutenção"),
    EM_TRANSITO("Em Trânsito", "Bem sendo transferido entre localizações"),
    RESERVA("Em Reserva", "Bem disponível para uso futuro"),
    BAIXADO("Baixado", "Bem removido do patrimônio"),
    EXTRAVIADO("Extraviado", "Bem não localizado"),
    ROUBADO("Roubado", "Bem furtado ou roubado"),
    DANIFICADO("Danificado", "Bem com danos que impedem uso"),
    OBSOLETO("Obsoleto", "Bem tecnologicamente ultrapassado");

    private final String descricao;
    private final String explicacao;

    StatusBem(String descricao, String explicacao) {
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
        return this == ATIVO || this == RESERVA;
    }

    public boolean isManutencao() {
        return this == EM_MANUTENCAO;
    }

    public boolean isProblema() {
        return this == EXTRAVIADO || this == ROUBADO || this == DANIFICADO;
    }
}

