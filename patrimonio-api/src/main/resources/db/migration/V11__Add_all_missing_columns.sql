-- V11__Add_all_missing_columns.sql
-- Adicionar todas as colunas que estão faltando na tabela bens

ALTER TABLE bens 
ADD COLUMN IF NOT EXISTS marca VARCHAR(100),
ADD COLUMN IF NOT EXISTS modelo VARCHAR(100),
ADD COLUMN IF NOT EXISTS observacoes VARCHAR(1000);
