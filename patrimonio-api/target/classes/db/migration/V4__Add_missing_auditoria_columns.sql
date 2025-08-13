-- V4__Add_missing_auditoria_columns.sql
-- Adicionar colunas que faltam na tabela auditoria

ALTER TABLE auditoria 
ADD COLUMN IF NOT EXISTS sessao_id VARCHAR(255),
ADD COLUMN IF NOT EXISTS metodo_http VARCHAR(10),
ADD COLUMN IF NOT EXISTS url_requisicao TEXT,
ADD COLUMN IF NOT EXISTS parametros_requisicao TEXT,
ADD COLUMN IF NOT EXISTS resultado_acao TEXT,
ADD COLUMN IF NOT EXISTS tempo_execucao_ms BIGINT,
ADD COLUMN IF NOT EXISTS erro_ocorrido TEXT;
