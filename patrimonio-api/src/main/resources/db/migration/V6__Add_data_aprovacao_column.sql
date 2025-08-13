-- V6__Add_data_aprovacao_column.sql
-- Adicionar coluna data_aprovacao que está faltando na tabela baixas

ALTER TABLE baixas 
ADD COLUMN IF NOT EXISTS data_aprovacao DATE;
