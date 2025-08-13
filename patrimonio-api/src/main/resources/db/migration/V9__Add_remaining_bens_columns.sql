-- V9__Add_remaining_bens_columns.sql
-- Adicionar todas as colunas que ainda estão faltando na tabela bens

ALTER TABLE bens 
ADD COLUMN IF NOT EXISTS centro_custo VARCHAR(100),
ADD COLUMN IF NOT EXISTS vida_util_anos INTEGER,
ADD COLUMN IF NOT EXISTS taxa_depreciacao DECIMAL(5,2),
ADD COLUMN IF NOT EXISTS data_proxima_manutencao DATE,
ADD COLUMN IF NOT EXISTS observacoes_manutencao VARCHAR(500),
ADD COLUMN IF NOT EXISTS localizacao_fisica VARCHAR(200),
ADD COLUMN IF NOT EXISTS responsavel VARCHAR(100),
ADD COLUMN IF NOT EXISTS departamento VARCHAR(100);
