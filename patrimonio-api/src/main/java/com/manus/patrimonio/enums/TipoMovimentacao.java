package com.manus.patrimonio.enums;

public enum TipoMovimentacao {
    TRANSFERENCIA("Transferência", "Movimentação entre localizações"),
    ENTRADA("Entrada", "Entrada de bem no patrimônio"),
    SAIDA("Saída", "Saída temporária de bem"),
    RETORNO("Retorno", "Retorno de bem de saída temporária"),
    MANUTENCAO("Manutenção", "Envio para manutenção"),
    INVENTARIO("Inventário", "Movimentação para verificação de inventário"),
    EMPRESTIMO("Empréstimo", "Empréstimo para terceiros"),
    DEVOLUCAO_EMPRESTIMO("Devolução de Empréstimo", "Devolução de bem emprestado"),
    RESERVA("Reserva", "Movimentação para reserva"),
    DESRESERVA("Desreserva", "Remoção de bem da reserva");

    private final String descricao;
    private final String explicacao;

    TipoMovimentacao(String descricao, String explicacao) {
        this.descricao = descricao;
        this.explicacao = explicacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getExplicacao() {
        return explicacao;
    }

    public boolean isEntrada() {
        return this == ENTRADA || this == RETORNO || this == DEVOLUCAO_EMPRESTIMO || this == DESRESERVA;
    }

    public boolean isSaida() {
        return this == SAIDA || this == MANUTENCAO || this == EMPRESTIMO || this == RESERVA;
    }

    public boolean isTransferencia() {
        return this == TRANSFERENCIA || this == INVENTARIO;
    }
}

