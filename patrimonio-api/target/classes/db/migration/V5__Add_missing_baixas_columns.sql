-- V5__Add_missing_baixas_columns.sql
-- Adicionar colunas que faltam na tabela baixas

ALTER TABLE baixas 
ADD COLUMN IF NOT EXISTS aprovado_por VARCHAR(150),
ADD COLUMN IF NOT EXISTS justificativa_tecnica TEXT,
ADD COLUMN IF NOT EXISTS documentacao_anexada TEXT,
ADD COLUMN IF NOT EXISTS destino_final VARCHAR(255),
ADD COLUMN IF NOT EXISTS valor_venda DECIMAL(12,2),
ADD COLUMN IF NOT EXISTS data_venda DATE,
ADD COLUMN IF NOT EXISTS comprador VARCHAR(150);
