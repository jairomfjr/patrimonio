-- Script de inicialização do banco de dados PostgreSQL
-- Sistema de Gestão de Bens Patrimoniais

-- Criar extensões necessárias
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Configurar timezone
SET timezone = 'America/Sao_Paulo';

-- Criar schema se não existir
CREATE SCHEMA IF NOT EXISTS patrimonio;

-- Configurar search_path
ALTER DATABASE patrimonio_db SET search_path TO patrimonio, public;

-- Criar usuário de aplicação com permissões limitadas
DO $$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = 'app_user') THEN
        CREATE ROLE app_user LOGIN PASSWORD 'app_password';
    END IF;
END
$$;

-- Conceder permissões ao usuário da aplicação
GRANT USAGE ON SCHEMA patrimonio TO app_user;
GRANT CREATE ON SCHEMA patrimonio TO app_user;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA patrimonio TO app_user;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA patrimonio TO app_user;

-- Configurar permissões padrão para futuras tabelas
ALTER DEFAULT PRIVILEGES IN SCHEMA patrimonio 
    GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO app_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA patrimonio 
    GRANT USAGE, SELECT ON SEQUENCES TO app_user;

-- Criar índices para melhor performance (serão criados automaticamente pelo Hibernate)
-- Mas podemos pré-criar alguns índices importantes

-- Comentários para documentação
COMMENT ON SCHEMA patrimonio IS 'Schema principal do sistema de gestão de bens patrimoniais';

-- Log de inicialização
INSERT INTO public.init_log (timestamp, message) 
VALUES (NOW(), 'Database initialized successfully for Sistema de Gestão de Bens Patrimoniais')
ON CONFLICT DO NOTHING;

