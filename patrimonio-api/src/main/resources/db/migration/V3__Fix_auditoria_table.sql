-- V3__Fix_auditoria_table.sql
-- Corrigir tabela auditoria para incluir colunas da EntidadeBase

ALTER TABLE auditoria 
ADD COLUMN IF NOT EXISTS data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN IF NOT EXISTS data_atualizacao TIMESTAMP,
ADD COLUMN IF NOT EXISTS versao BIGINT DEFAULT 0;

-- Atualizar registros existentes
UPDATE auditoria 
SET data_criacao = data_acao 
WHERE data_criacao IS NULL;
