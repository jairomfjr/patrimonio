-- V12__Add_numero_tombamento_column.sql
-- Adicionar coluna numero_tombamento que está faltando na tabela bens

-- Adicionar a coluna permitindo NULL inicialmente
ALTER TABLE bens 
ADD COLUMN IF NOT EXISTS numero_tombamento VARCHAR(50);

-- Atualizar registros existentes com um valor padrão baseado no ID
UPDATE bens 
SET numero_tombamento = 'BEM' || LPAD(id::text, 6, '0')
WHERE numero_tombamento IS NULL;

-- Agora tornar a coluna NOT NULL
ALTER TABLE bens 
ALTER COLUMN numero_tombamento SET NOT NULL;

-- Adicionar constraint de unicidade
ALTER TABLE bens 
ADD CONSTRAINT idx_bem_tombamento UNIQUE (numero_tombamento);
