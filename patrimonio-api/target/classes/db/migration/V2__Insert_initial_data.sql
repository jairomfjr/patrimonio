-- V2__Insert_initial_data.sql
-- Sistema de Patrimônio - Dados Iniciais

-- Inserir perfis padrão
INSERT INTO perfis (nome, descricao, ativo) VALUES
('ADMIN', 'Administrador do Sistema', true),
('GESTOR', 'Gestor de Patrimônio', true),
('OPERADOR', 'Operador do Sistema', true),
('CONSULTA', 'Apenas Consulta', true);

-- Inserir usuário administrador padrão (senha: admin123)
INSERT INTO usuarios (username, email, nome_completo, senha_hash, ativo) VALUES
('admin', 'admin@patrimonio.com', 'Administrador do Sistema', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', true);

-- Atribuir perfil de administrador ao usuário admin
INSERT INTO usuario_perfis (usuario_id, perfil_id) VALUES (1, 1);

-- Inserir categorias padrão
INSERT INTO categorias (nome, descricao, codigo, ativo) VALUES
('Equipamentos de Informática', 'Computadores, notebooks, tablets e periféricos', 'INFO', true),
('Móveis e Utensílios', 'Móveis de escritório, mesas, cadeiras, armários', 'MOVEL', true),
('Veículos', 'Automóveis, motocicletas e veículos utilitários', 'VEICULO', true),
('Equipamentos Industriais', 'Máquinas e equipamentos de produção', 'IND', true),
('Imóveis', 'Edifícios, terrenos e construções', 'IMOVEL', true),
('Equipamentos de Telecomunicação', 'Telefones, sistemas de comunicação', 'TELECOM', true),
('Equipamentos Médicos', 'Aparelhos e instrumentos médicos', 'MEDICO', true),
('Ferramentas', 'Ferramentas manuais e elétricas', 'FERRAMENTA', true);

-- Inserir localizações padrão
INSERT INTO localizacoes (nome, descricao, endereco, responsavel, telefone, email, ativo) VALUES
('Sede Principal', 'Edifício sede da empresa', 'Rua das Flores, 123 - Centro', 'João Silva', '(11) 99999-9999', 'joao.silva@empresa.com', true),
('Filial Norte', 'Filial da região norte', 'Av. Norte, 456 - Zona Norte', 'Maria Santos', '(11) 88888-8888', 'maria.santos@empresa.com', true),
('Depósito Central', 'Depósito principal de materiais', 'Rua do Depósito, 789 - Industrial', 'Pedro Costa', '(11) 77777-7777', 'pedro.costa@empresa.com', true),
('Laboratório', 'Laboratório de pesquisa e desenvolvimento', 'Rua da Ciência, 321 - Tecnológico', 'Ana Oliveira', '(11) 66666-6666', 'ana.oliveira@empresa.com', true),
('Oficina', 'Oficina de manutenção', 'Rua da Manutenção, 654 - Industrial', 'Carlos Lima', '(11) 55555-5555', 'carlos.lima@empresa.com', true);

-- Inserir configurações do sistema
INSERT INTO configuracoes (chave, valor, descricao, tipo, editavel) VALUES
('SISTEMA.NOME', 'Sistema de Gestão de Patrimônio', 'Nome do sistema', 'STRING', false),
('SISTEMA.VERSAO', '2.0.0', 'Versão atual do sistema', 'STRING', false),
('SISTEMA.EMPRESA', 'Empresa Exemplo LTDA', 'Nome da empresa', 'STRING', true),
('SISTEMA.CNPJ', '12.345.678/0001-90', 'CNPJ da empresa', 'STRING', true),
('SISTEMA.ENDERECO', 'Rua das Flores, 123 - Centro - São Paulo/SP', 'Endereço da empresa', 'STRING', true),
('SISTEMA.TELEFONE', '(11) 3333-3333', 'Telefone da empresa', 'STRING', true),
('SISTEMA.EMAIL', 'contato@empresa.com', 'Email de contato', 'STRING', true),
('PATRIMONIO.VALOR_MINIMO_BAIXA', '100.00', 'Valor mínimo para baixa de patrimônio', 'DECIMAL', true),
('PATRIMONIO.DIAS_GARANTIA_PADRAO', '365', 'Dias padrão de garantia', 'INTEGER', true),
('NOTIFICACAO.EMAIL_ENABLED', 'true', 'Habilitar notificações por email', 'BOOLEAN', true),
('NOTIFICACAO.SLACK_ENABLED', 'false', 'Habilitar notificações no Slack', 'BOOLEAN', true),
('BACKUP.AUTOMATICO', 'true', 'Backup automático habilitado', 'BOOLEAN', true),
('BACKUP.RETENCAO_DIAS', '30', 'Dias de retenção do backup', 'INTEGER', true),
('AUDITORIA.RETENCAO_DIAS', '2555', 'Dias de retenção da auditoria', 'INTEGER', true),
('SEGURANCA.SESSAO_TIMEOUT', '3600', 'Timeout da sessão em segundos', 'INTEGER', true),
('SEGURANCA.TENTATIVAS_LOGIN', '3', 'Número máximo de tentativas de login', 'INTEGER', true);

-- Inserir alguns bens de exemplo
INSERT INTO bens (nome, descricao, numero_serie, numero_patrimonio, data_aquisicao, valor_aquisicao, valor_atual, status, condicao, categoria_id, localizacao_atual_id, fornecedor, garantia_ate) VALUES
('Notebook Dell Latitude 5520', 'Notebook empresarial Dell com processador Intel i7', 'DL5520-001', 'PAT001', '2023-01-15', 4500.00, 3800.00, 'ATIVO', 'EXCELENTE', 1, 1, 'Dell Brasil', '2026-01-15'),
('Impressora HP LaserJet Pro', 'Impressora laser monocromática para alta produção', 'HP-LJ-002', 'PAT002', '2023-02-20', 1200.00, 1000.00, 'ATIVO', 'BOM', 1, 1, 'HP Brasil', '2025-02-20'),
('Mesa de Escritório', 'Mesa executiva com gavetas', 'ME-003', 'PAT003', '2023-03-10', 800.00, 700.00, 'ATIVO', 'BOM', 2, 1, 'Móveis Silva', '2024-03-10'),
('Cadeira Ergonômica', 'Cadeira de escritório com suporte lombar', 'CE-004', 'PAT004', '2023-03-15', 600.00, 500.00, 'ATIVO', 'BOM', 2, 1, 'Móveis Silva', '2024-03-15'),
('Microscópio Binocular', 'Microscópio para laboratório com aumento 40x-1000x', 'MB-005', 'PAT005', '2023-04-05', 2500.00, 2200.00, 'ATIVO', 'EXCELENTE', 7, 4, 'LabEquip', '2026-04-05');

-- Inserir algumas movimentações de exemplo
INSERT INTO movimentacoes (bem_id, tipo_movimentacao, data_movimentacao, localizacao_origem_id, localizacao_destino_id, responsavel_movimentacao, motivo, observacoes) VALUES
(1, 'TRANSFERENCIA', '2023-06-01 09:00:00', 1, 2, 'João Silva', 'Transferência para filial norte', 'Usuário solicitou transferência para trabalhar na filial'),
(3, 'TRANSFERENCIA', '2023-07-15 14:30:00', 1, 3, 'Maria Santos', 'Transferência para depósito', 'Mesa será utilizada em projeto temporário'),
(5, 'TRANSFERENCIA', '2023-08-20 10:15:00', 4, 1, 'Ana Oliveira', 'Retorno do laboratório', 'Microscópio retornou da manutenção');

-- Inserir inventário de exemplo
INSERT INTO inventarios (nome, descricao, data_inicio, status, responsavel, observacoes) VALUES
('Inventário Geral 2023', 'Inventário anual de todos os bens patrimoniais', '2023-12-01', 'EM_ANDAMENTO', 'João Silva', 'Inventário anual obrigatório');

-- Inserir itens do inventário
INSERT INTO inventario_itens (inventario_id, bem_id, encontrado, condicao_encontrada, localizacao_encontrada_id, observacoes, data_verificacao) VALUES
(1, 1, true, 'EXCELENTE', 2, 'Bem encontrado na localização correta', '2023-12-01 09:00:00'),
(1, 2, true, 'BOM', 1, 'Bem encontrado na localização correta', '2023-12-01 09:30:00'),
(1, 3, false, null, null, 'Bem não encontrado na localização esperada', '2023-12-01 10:00:00'),
(1, 4, true, 'BOM', 1, 'Bem encontrado na localização correta', '2023-12-01 10:30:00'),
(1, 5, true, 'EXCELENTE', 4, 'Bem encontrado na localização correta', '2023-12-01 11:00:00');

-- Inserir manutenção de exemplo
INSERT INTO manutencoes (bem_id, tipo_manutencao, descricao, data_inicio, data_fim, custo, fornecedor, responsavel, status, observacoes) VALUES
(5, 'PREVENTIVA', 'Limpeza e calibração do microscópio', '2023-08-15', '2023-08-20', 150.00, 'LabEquip', 'Ana Oliveira', 'CONCLUIDA', 'Manutenção preventiva semestral realizada com sucesso');

-- Inserir notificações de exemplo
INSERT INTO notificacoes (usuario_id, titulo, mensagem, tipo, lida) VALUES
(1, 'Bem não encontrado no inventário', 'O bem PAT003 (Mesa de Escritório) não foi encontrado durante o inventário', 'ALERTA', false),
(1, 'Manutenção preventiva agendada', 'Lembrete: Manutenção preventiva do microscópio (PAT005) deve ser agendada para fevereiro/2024', 'INFORMACAO', false);
