-- V10__Add_fabricante_fornecedor_columns.sql
-- Adicionar colunas fabricante e fornecedor que estão faltando na tabela bens

ALTER TABLE bens 
ADD COLUMN IF NOT EXISTS fabricante VARCHAR(100),
ADD COLUMN IF NOT EXISTS fornecedor VARCHAR(100);
