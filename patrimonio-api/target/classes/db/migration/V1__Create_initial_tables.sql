-- V1__Create_initial_tables.sql
-- Sistema de Patrimônio - Tabelas Iniciais

-- Tabela de Categorias
CREATE TABLE categorias (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    descricao TEXT,
    codigo VARCHAR(20) UNIQUE,
    ativo BOOLEAN DEFAULT TRUE,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    versao BIGINT DEFAULT 0
);

-- Tabela de Localizações
CREATE TABLE localizacoes (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    descricao TEXT,
    endereco TEXT,
    responsavel VARCHAR(150),
    telefone VARCHAR(20),
    email VARCHAR(100),
    ativo BOOLEAN DEFAULT TRUE,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    versao BIGINT DEFAULT 0
);

-- Tabela de Bens
CREATE TABLE bens (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    descricao TEXT,
    numero_serie VARCHAR(100) UNIQUE,
    numero_patrimonio VARCHAR(50) UNIQUE,
    data_aquisicao DATE NOT NULL,
    valor_aquisicao DECIMAL(15,2) NOT NULL,
    valor_atual DECIMAL(15,2),
    status VARCHAR(50) NOT NULL,
    condicao VARCHAR(50) NOT NULL,
    observacoes TEXT,
    categoria_id BIGINT NOT NULL REFERENCES categorias(id),
    localizacao_atual_id BIGINT NOT NULL REFERENCES localizacoes(id),
    fornecedor VARCHAR(150),
    garantia_ate DATE,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    versao BIGINT DEFAULT 0
);

-- Tabela de Movimentações
CREATE TABLE movimentacoes (
    id BIGSERIAL PRIMARY KEY,
    bem_id BIGINT NOT NULL REFERENCES bens(id),
    tipo_movimentacao VARCHAR(50) NOT NULL,
    data_movimentacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    localizacao_origem_id BIGINT REFERENCES localizacoes(id),
    localizacao_destino_id BIGINT NOT NULL REFERENCES localizacoes(id),
    responsavel_movimentacao VARCHAR(150),
    motivo TEXT,
    observacoes TEXT,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    versao BIGINT DEFAULT 0
);

-- Tabela de Inventários
CREATE TABLE inventarios (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    descricao TEXT,
    data_inicio DATE NOT NULL,
    data_fim DATE,
    status VARCHAR(50) NOT NULL,
    responsavel VARCHAR(150),
    observacoes TEXT,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    versao BIGINT DEFAULT 0
);

-- Tabela de Itens do Inventário
CREATE TABLE inventario_itens (
    id BIGSERIAL PRIMARY KEY,
    inventario_id BIGINT NOT NULL REFERENCES inventarios(id),
    bem_id BIGINT NOT NULL REFERENCES bens(id),
    encontrado BOOLEAN,
    condicao_encontrada VARCHAR(50),
    localizacao_encontrada_id BIGINT REFERENCES localizacoes(id),
    observacoes TEXT,
    data_verificacao TIMESTAMP,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    versao BIGINT DEFAULT 0,
    UNIQUE(inventario_id, bem_id)
);

-- Tabela de Manutenções
CREATE TABLE manutencoes (
    id BIGSERIAL PRIMARY KEY,
    bem_id BIGINT NOT NULL REFERENCES bens(id),
    tipo_manutencao VARCHAR(50) NOT NULL,
    descricao TEXT NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE,
    custo DECIMAL(15,2),
    fornecedor VARCHAR(150),
    responsavel VARCHAR(150),
    status VARCHAR(50) NOT NULL,
    observacoes TEXT,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    versao BIGINT DEFAULT 0
);

-- Tabela de Baixas
CREATE TABLE baixas (
    id BIGSERIAL PRIMARY KEY,
    bem_id BIGINT NOT NULL REFERENCES bens(id),
    motivo VARCHAR(100) NOT NULL,
    descricao TEXT,
    data_baixa DATE NOT NULL,
    valor_residual DECIMAL(15,2),
    processo_administrativo VARCHAR(100),
    responsavel VARCHAR(150),
    observacoes TEXT,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    versao BIGINT DEFAULT 0
);

-- Tabela de Usuários
CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    nome_completo VARCHAR(150) NOT NULL,
    senha_hash VARCHAR(255) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    data_ultimo_login TIMESTAMP,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    versao BIGINT DEFAULT 0
);

-- Tabela de Perfis
CREATE TABLE perfis (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE,
    descricao TEXT,
    ativo BOOLEAN DEFAULT TRUE,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    versao BIGINT DEFAULT 0
);

-- Tabela de Usuários x Perfis
CREATE TABLE usuario_perfis (
    usuario_id BIGINT NOT NULL REFERENCES usuarios(id),
    perfil_id BIGINT NOT NULL REFERENCES perfis(id),
    data_atribuicao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (usuario_id, perfil_id)
);

-- Tabela de Auditoria
CREATE TABLE auditoria (
    id BIGSERIAL PRIMARY KEY,
    entidade VARCHAR(100) NOT NULL,
    entidade_id BIGINT NOT NULL,
    acao VARCHAR(50) NOT NULL,
    usuario_id BIGINT REFERENCES usuarios(id),
    dados_anteriores JSONB,
    dados_novos JSONB,
    ip_address VARCHAR(45),
    user_agent TEXT,
    data_acao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de Configurações do Sistema
CREATE TABLE configuracoes (
    id BIGSERIAL PRIMARY KEY,
    chave VARCHAR(100) NOT NULL UNIQUE,
    valor TEXT,
    descricao TEXT,
    tipo VARCHAR(50) NOT NULL,
    editavel BOOLEAN DEFAULT TRUE,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    versao BIGINT DEFAULT 0
);

-- Tabela de Notificações
CREATE TABLE notificacoes (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL REFERENCES usuarios(id),
    titulo VARCHAR(200) NOT NULL,
    mensagem TEXT NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    lida BOOLEAN DEFAULT FALSE,
    data_envio TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_leitura TIMESTAMP,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP,
    versao BIGINT DEFAULT 0
);

-- Índices para performance
CREATE INDEX idx_bens_categoria ON bens(categoria_id);
CREATE INDEX idx_bens_localizacao ON bens(localizacao_atual_id);
CREATE INDEX idx_bens_status ON bens(status);
CREATE INDEX idx_bens_numero_patrimonio ON bens(numero_patrimonio);
CREATE INDEX idx_movimentacoes_bem ON movimentacoes(bem_id);
CREATE INDEX idx_movimentacoes_data ON movimentacoes(data_movimentacao);
CREATE INDEX idx_inventario_itens_inventario ON inventario_itens(inventario_id);
CREATE INDEX idx_inventario_itens_bem ON inventario_itens(bem_id);
CREATE INDEX idx_manutencoes_bem ON manutencoes(bem_id);
CREATE INDEX idx_baixas_bem ON baixas(bem_id);
CREATE INDEX idx_auditoria_entidade ON auditoria(entidade, entidade_id);
CREATE INDEX idx_auditoria_data ON auditoria(data_acao);
CREATE INDEX idx_notificacoes_usuario ON notificacoes(usuario_id);
CREATE INDEX idx_notificacoes_lida ON notificacoes(lida);

-- Constraints de validação
ALTER TABLE bens ADD CONSTRAINT chk_valor_aquisicao_positive CHECK (valor_aquisicao > 0);
ALTER TABLE bens ADD CONSTRAINT chk_data_aquisicao_past CHECK (data_aquisicao <= CURRENT_DATE);
ALTER TABLE movimentacoes ADD CONSTRAINT chk_localizacoes_diferentes CHECK (localizacao_origem_id != localizacao_destino_id);
ALTER TABLE manutencoes ADD CONSTRAINT chk_data_manutencao CHECK (data_fim IS NULL OR data_fim >= data_inicio);
ALTER TABLE baixas ADD CONSTRAINT chk_data_baixa CHECK (data_baixa <= CURRENT_DATE);
