-- Tabela de Inventários
CREATE TABLE IF NOT EXISTS inventarios (
    id BIGSERIAL PRIMARY KEY,
    responsavel VARCHAR(255) NOT NULL,
    data_inicio TIMESTAMP NOT NULL,
    data_fim TIMESTAMP,
    status VARCHAR(50) NOT NULL DEFAULT 'EM_ANDAMENTO',
    observacoes TEXT,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    versao INTEGER NOT NULL DEFAULT 0
);

-- Tabela de Movimentações
CREATE TABLE IF NOT EXISTS movimentacoes (
    id BIGSERIAL PRIMARY KEY,
    bem_id BIGINT NOT NULL,
    tipo_movimentacao VARCHAR(50) NOT NULL,
    localizacao_origem_id BIGINT,
    localizacao_destino_id BIGINT,
    responsavel_movimentacao VARCHAR(255) NOT NULL,
    data_movimentacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    observacoes TEXT,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    versao INTEGER NOT NULL DEFAULT 0,
    FOREIGN KEY (bem_id) REFERENCES bens(id),
    FOREIGN KEY (localizacao_origem_id) REFERENCES localizacoes(id),
    FOREIGN KEY (localizacao_destino_id) REFERENCES localizacoes(id)
);

-- Índices para melhor performance
CREATE INDEX IF NOT EXISTS idx_inventarios_status ON inventarios(status);
CREATE INDEX IF NOT EXISTS idx_inventarios_responsavel ON inventarios(responsavel);
CREATE INDEX IF NOT EXISTS idx_movimentacoes_bem_id ON movimentacoes(bem_id);
CREATE INDEX IF NOT EXISTS idx_movimentacoes_tipo ON movimentacoes(tipo_movimentacao);
CREATE INDEX IF NOT EXISTS idx_movimentacoes_data ON movimentacoes(data_movimentacao);
