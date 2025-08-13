-- V8__Add_ativo_column.sql
-- Adicionar coluna ativo que está faltando na tabela bens

ALTER TABLE bens 
ADD COLUMN IF NOT EXISTS ativo BOOLEAN DEFAULT TRUE;
