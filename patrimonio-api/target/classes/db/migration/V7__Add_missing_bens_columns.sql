-- V7__Add_missing_bens_columns.sql
-- Adicionar colunas que faltam na tabela bens

ALTER TABLE bens 
ADD COLUMN IF NOT EXISTS nome VARCHAR(150),
ADD COLUMN IF NOT EXISTS ano_fabricacao INTEGER,
ADD COLUMN IF NOT EXISTS garantia_ate DATE,
ADD COLUMN IF NOT EXISTS data_ultima_manutencao DATE,
ADD COLUMN IF NOT EXISTS data_ultima_movimentacao DATE;
