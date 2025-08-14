# 🏢 Sistema de Gestão de Patrimônio - API

[![Java](https://img.shields.io/badge/Java-23-orange.svg)](https://openjdk.java.net/projects/jdk/23/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.9+-red.svg)](https://maven.apache.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15+-blue.svg)](https://www.postgresql.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

## 📋 Índice

- [🎯 Visão Geral](#-visão-geral)
- [🚀 Funcionalidades](#-funcionalidades)
- [🏗️ Arquitetura](#️-arquitetura)
- [🛠️ Tecnologias](#️-tecnologias)
- [📁 Estrutura do Projeto](#-estrutura-do-projeto)
- [⚙️ Configuração](#️-configuração)
- [🚀 Executando o Projeto](#-executando-o-projeto)
- [🧪 Testes](#-testes)
- [📊 API Endpoints](#-api-endpoints)
- [🗄️ Banco de Dados](#-banco-de-dados)
- [🔒 Segurança](#-segurança)
- [📈 Monitoramento](#-monitoramento)
- [🐳 Docker](#-docker)
- [📚 Documentação](#-documentação)
- [🤝 Contribuição](#-contribuição)
- [📄 Licença](#-licença)

## 🎯 Visão Geral

O **Sistema de Gestão de Patrimônio** é uma API robusta e escalável desenvolvida em Spring Boot para gerenciar bens patrimoniais de organizações. O sistema oferece funcionalidades completas para controle de inventário, manutenções, baixas, movimentações e auditoria de todos os processos relacionados ao patrimônio.

### ✨ Características Principais

- 🔐 **Autenticação e Autorização** com Spring Security
- 📊 **Gestão Completa de Bens** com rastreamento de ciclo de vida
- 🔄 **Sistema de Workflow** para aprovações e processos
- 📈 **Relatórios e Dashboards** com métricas em tempo real
- 🔍 **Auditoria Completa** de todas as operações
- 📱 **API RESTful** com documentação OpenAPI
- 🚀 **Performance Otimizada** com cache Redis e Quartz
- 🐳 **Containerização** com Docker

## 🚀 Funcionalidades Detalhadas

### 🏷️ Gestão de Bens Patrimoniais
- ✅ **Cadastro Completo**: Nome, descrição, número de série, categoria, localização
- ✅ **Rastreamento Avançado**: Status, condição, valor de aquisição, data de aquisição
- ✅ **Informações Técnicas**: Marca, modelo, fabricante, fornecedor, ano de fabricação
- ✅ **Controle Financeiro**: Valor atual, depreciação, vida útil, centro de custo
- ✅ **Localização e Responsabilidade**: Localização física, responsável, departamento
- ✅ **Manutenção e Garantia**: Data da última manutenção, próxima manutenção, garantia
- ✅ **Observações e Documentação**: Campo livre para anotações e documentação anexada

### 📋 Sistema de Inventário
- ✅ **Criação de Inventários**: Nome, período, responsável, observações
- ✅ **Execução de Contagem**: Contagem física dos bens em cada localização
- ✅ **Controle de Status**: Em andamento, concluído, cancelado
- ✅ **Relatórios de Divergências**: Comparação entre sistema e contagem física
- ✅ **Histórico Completo**: Rastreamento de todos os inventários realizados
- ✅ **Filtros Avançados**: Por período, responsável, localização, status

### 🔧 Gestão de Manutenções
- ✅ **Tipos de Manutenção**: Preventiva, corretiva, preditiva, emergencial
- ✅ **Agendamento Inteligente**: Baseado em vida útil e histórico de manutenções
- ✅ **Controle de Custos**: Valor da manutenção, fornecedor, número da nota fiscal
- ✅ **Workflow de Aprovação**: Solicitação → Aprovação → Execução → Finalização
- ✅ **Histórico Detalhado**: Solução aplicada, recomendações, próximas ações
- ✅ **Integração com Bens**: Atualização automática de datas de manutenção

### 📤 Processo de Baixas
- ✅ **Solicitação de Baixa**: Justificativa, valor residual, destino final
- ✅ **Workflow de Aprovação**: Aprovação técnica, aprovação administrativa
- ✅ **Controle de Documentação**: Documentação anexada, justificativa técnica
- ✅ **Destino Final**: Venda, doação, sucata, transferência
- ✅ **Controle Financeiro**: Valor de venda, comprador, data da venda
- ✅ **Auditoria Completa**: Rastreamento de todo o processo

### 🔄 Movimentações e Transferências
- ✅ **Tipos de Movimentação**: Entrada, saída, transferência, manutenção, baixa
- ✅ **Controle de Responsabilidade**: Responsável pela movimentação
- ✅ **Histórico de Localizações**: Rastreamento completo de onde o bem esteve
- ✅ **Aprovações**: Controle de aprovação para movimentações críticas
- ✅ **Observações**: Campo para justificativas e observações

### 👥 Gestão de Usuários e Perfis
- ✅ **Sistema de Perfis**: ADMIN, GESTOR, OPERADOR, CONSULTA
- ✅ **Controle de Acesso**: Permissões granulares por funcionalidade
- ✅ **Autenticação JWT**: Tokens seguros com refresh automático
- ✅ **Auditoria de Ações**: Log de todas as operações realizadas
- ✅ **Gestão de Sessões**: Controle de sessões ativas e inativas

### 📊 Relatórios e Analytics
- ✅ **Dashboard Executivo**: Visão geral do patrimônio em tempo real
- ✅ **Relatórios de Depreciação**: Cálculo automático de depreciação
- ✅ **Análise de Custos**: Custos de manutenção por período e categoria
- ✅ **Estatísticas de Utilização**: Taxa de utilização dos bens
- ✅ **Relatórios de Inventário**: Status e divergências encontradas
- ✅ **Análise de Baixas**: Histórico e justificativas de baixas
- ✅ **Métricas de Performance**: KPIs de gestão patrimonial

### 🔍 Sistema de Auditoria
- ✅ **Log de Operações**: Todas as alterações são registradas
- ✅ **Rastreamento de Usuários**: Quem fez o quê e quando
- ✅ **Histórico de Alterações**: Antes e depois de cada modificação
- ✅ **Relatórios de Auditoria**: Filtros por usuário, entidade, período
- ✅ **Compliance**: Atendimento a requisitos de auditoria

### ⚙️ Configurações do Sistema
- ✅ **Parâmetros Configuráveis**: Taxas de depreciação, prazos, limites
- ✅ **Configurações de Email**: Templates, destinatários, frequência
- ✅ **Configurações de Notificação**: Alertas, lembretes, avisos
- ✅ **Personalização**: Logos, cores, textos do sistema

## 🏗️ Arquitetura

### 📐 Padrões Arquiteturais
- **Arquitetura em Camadas** (Controller → Service → Repository)
- **Domain-Driven Design (DDD)** para modelagem de negócio
- **Repository Pattern** para acesso a dados
- **Service Layer** para lógica de negócio
- **DTO Pattern** para transferência de dados
- **Mapper Pattern** para conversão entre entidades e DTOs
- **Audit Pattern** para rastreamento de alterações
- **Strategy Pattern** para diferentes tipos de validação

### 🔄 Fluxo de Dados
```
HTTP Request → Controller → Service → Repository → Database
                ↓
            Response ← DTO ← Mapper ← Entity
```

### 🏛️ Estrutura de Camadas
```
┌─────────────────────────────────────────────────────────┐
│                    Controllers                          │
├─────────────────────────────────────────────────────────┤
│                     Services                           │
├─────────────────────────────────────────────────────────┤
│                   Repositories                         │
├─────────────────────────────────────────────────────────┤
│                     Entities                           │
├─────────────────────────────────────────────────────────┤
│                    Database                            │
└─────────────────────────────────────────────────────────┘
```

### 📦 Diagrama de Pacotes Detalhado

```
com.manus.patrimonio/
├── 📁 SistemaPatrimonioApplication.java          # Classe principal da aplicação
│
├── 📁 config/                                    # 🔧 Configurações do sistema
│   ├── SecurityConfig.java                      # Configuração de segurança e JWT
│   ├── CorsConfig.java                          # Configuração CORS
│   ├── SwaggerConfig.java                       # Configuração da documentação OpenAPI
│   ├── CacheConfig.java                         # Configuração de cache Redis
│   ├── QuartzConfig.java                        # Configuração do scheduler
│   └── AuditoriaConfig.java                     # Configuração de auditoria automática
│
├── 📁 controller/                                # 🎮 Controllers REST da API
│   ├── BemController.java                       # Gestão de bens patrimoniais
│   ├── CategoriaController.java                 # Gestão de categorias
│   ├── LocalizacaoController.java               # Gestão de localizações
│   ├── ManutencaoController.java                # Gestão de manutenções
│   ├── BaixaController.java                     # Processo de baixas
│   ├── InventarioController.java                # Sistema de inventário
│   ├── MovimentacaoController.java              # Controle de movimentações
│   ├── UsuarioController.java                   # Gestão de usuários
│   ├── PerfilController.java                    # Gestão de perfis e permissões
│   ├── AuditoriaController.java                 # Sistema de auditoria
│   ├── ConfiguracaoController.java              # Configurações do sistema
│   └── NotificacaoController.java               # Sistema de notificações
│
├── 📁 service/                                   # 🧠 Camada de lógica de negócio
│   ├── BemService.java                          # Lógica de negócio para bens
│   ├── CategoriaService.java                    # Lógica de negócio para categorias
│   ├── LocalizacaoService.java                  # Lógica de negócio para localizações
│   ├── ManutencaoService.java                   # Lógica de negócio para manutenções
│   ├── BaixaService.java                        # Lógica de negócio para baixas
│   ├── InventarioService.java                   # Lógica de negócio para inventários
│   ├── MovimentacaoService.java                 # Lógica de negócio para movimentações
│   ├── UsuarioService.java                      # Lógica de negócio para usuários
│   ├── PerfilService.java                       # Lógica de negócio para perfis
│   ├── AuditoriaService.java                    # Lógica de negócio para auditoria
│   ├── ConfiguracaoService.java                 # Lógica de negócio para configurações
│   └── NotificacaoService.java                  # Lógica de negócio para notificações
│
├── 📁 repository/                                # 💾 Camada de acesso a dados
│   ├── BemRepository.java                       # Repositório para entidade Bem
│   ├── CategoriaRepository.java                 # Repositório para entidade Categoria
│   ├── LocalizacaoRepository.java               # Repositório para entidade Localizacao
│   ├── ManutencaoRepository.java                # Repositório para entidade Manutencao
│   ├── BaixaRepository.java                     # Repositório para entidade Baixa
│   ├── InventarioRepository.java                # Repositório para entidade Inventario
│   ├── MovimentacaoRepository.java              # Repositório para entidade Movimentacao
│   ├── UsuarioRepository.java                   # Repositório para entidade Usuario
│   ├── PerfilRepository.java                    # Repositório para entidade Perfil
│   ├── AuditoriaRepository.java                 # Repositório para entidade Auditoria
│   ├── ConfiguracaoRepository.java              # Repositório para entidade Configuracao
│   └── NotificacaoRepository.java               # Repositório para entidade Notificacao
│
├── 📁 model/                                     # 🏗️ Entidades JPA do domínio
│   ├── EntidadeBase.java                        # Classe base com campos comuns
│   ├── Bem.java                                 # Entidade principal - Bens patrimoniais
│   ├── Categoria.java                           # Categorias de classificação dos bens
│   ├── Localizacao.java                         # Localizações físicas dos bens
│   ├── Manutencao.java                          # Registros de manutenções
│   ├── Baixa.java                               # Processos de baixa de bens
│   ├── Inventario.java                          # Controles de inventário
│   ├── Movimentacao.java                        # Histórico de movimentações
│   ├── Usuario.java                             # Usuários do sistema
│   ├── Perfil.java                              # Perfis de acesso e permissões
│   ├── Auditoria.java                           # Log de auditoria automática
│   ├── Configuracao.java                        # Configurações configuráveis do sistema
│   └── Notificacao.java                         # Sistema de notificações
│
├── 📁 dto/                                       # 📦 Data Transfer Objects
│   ├── BemDTO.java                              # DTO para transferência de dados de bens
│   ├── CategoriaDTO.java                        # DTO para transferência de dados de categorias
│   ├── LocalizacaoDTO.java                      # DTO para transferência de dados de localizações
│   ├── ManutencaoDTO.java                       # DTO para transferência de dados de manutenções
│   ├── BaixaDTO.java                            # DTO para transferência de dados de baixas
│   ├── InventarioDTO.java                       # DTO para transferência de dados de inventários
│   ├── MovimentacaoDTO.java                     # DTO para transferência de dados de movimentações
│   ├── UsuarioDTO.java                          # DTO para transferência de dados de usuários
│   ├── PerfilDTO.java                           # DTO para transferência de dados de perfis
│   ├── AuditoriaDTO.java                        # DTO para transferência de dados de auditoria
│   ├── ConfiguracaoDTO.java                     # DTO para transferência de dados de configurações
│   ├── NotificacaoDTO.java                      # DTO para transferência de dados de notificações
│   ├── request/                                 # DTOs específicos para requisições
│   │   ├── LoginRequest.java                    # DTO para requisição de login
│   │   ├── FiltroBemRequest.java                # DTO para filtros de busca de bens
│   │   └── FiltroRelatorioRequest.java          # DTO para filtros de relatórios
│   └── response/                                # DTOs específicos para respostas
│       ├── LoginResponse.java                   # DTO para resposta de login
│       ├── DashboardResponse.java               # DTO para resposta do dashboard
│       └── EstatisticaResponse.java             # DTO para respostas de estatísticas
│
├── 📁 mapper/                                    # 🔄 Mapeadores MapStruct
│   ├── PatrimonioMapper.java                    # Mapeador principal do sistema
│   ├── BemMapper.java                           # Mapeador específico para entidade Bem
│   ├── ManutencaoMapper.java                    # Mapeador específico para entidade Manutencao
│   ├── BaixaMapper.java                         # Mapeador específico para entidade Baixa
│   ├── InventarioMapper.java                    # Mapeador específico para entidade Inventario
│   ├── UsuarioMapper.java                       # Mapeador específico para entidade Usuario
│   └── AuditoriaMapper.java                     # Mapeador específico para entidade Auditoria
│
├── 📁 enums/                                     # 🏷️ Enumerações do sistema
│   ├── StatusBem.java                           # Status dos bens (ATIVO, INATIVO, etc.)
│   ├── CondicaoBem.java                         # Condição física dos bens
│   ├── TipoManutencao.java                      # Tipos de manutenção
│   ├── StatusManutencao.java                    # Status das manutenções
│   ├── TipoMovimentacao.java                    # Tipos de movimentação
│   ├── StatusBaixa.java                         # Status do processo de baixa
│   ├── StatusInventario.java                    # Status dos inventários
│   ├── TipoNotificacao.java                     # Tipos de notificação
│   └── PerfilUsuario.java                       # Perfis de usuário predefinidos
│
├── 📁 exception/                                 # ⚠️ Tratamento de exceções
│   ├── RecursoNaoEncontradoException.java       # Exceção para recursos não encontrados
│   ├── RegraDeNegocioException.java             # Exceção para violações de regras de negócio
│   ├── DadosInvalidosException.java             # Exceção para dados inválidos
│   ├── AcessoNegadoException.java               # Exceção para acesso negado
│   ├── TratadorGlobalExcecoes.java              # Handler global de exceções
│   └── RespostaErro.java                        # DTO para respostas de erro padronizadas
│
├── 📁 util/                                      # 🛠️ Classes utilitárias
│   ├── DateUtils.java                           # Utilitários para manipulação de datas
│   ├── ValidationUtils.java                     # Utilitários para validações
│   ├── SecurityUtils.java                       # Utilitários para segurança
│   ├── FileUtils.java                           # Utilitários para manipulação de arquivos
│   ├── EmailUtils.java                          # Utilitários para envio de emails
│   └── ReportUtils.java                         # Utilitários para geração de relatórios
│
└── 📁 config/                                    # 🔧 Configurações adicionais
    ├── DadosIniciais.java                       # Carregamento de dados iniciais do sistema
    └── AuditoriaAspect.java                     # Aspecto para auditoria automática
```

### 🔗 Relacionamentos entre Entidades

```
┌─────────────┐    ┌─────────────┐    ┌─────────────┐
│   Usuario   │◄───┤    Perfil   │◄───┤  Usuario   │
│             │    │             │    │  Perfil    │
└─────────────┘    └─────────────┘    └─────────────┘
       │                   │                   │
       │                   │                   │
       ▼                   ▼                   ▼
┌─────────────┐    ┌─────────────┐    ┌─────────────┐
│  Auditoria  │    │   Bem       │    │ Categoria   │
│             │    │             │    │             │
└─────────────┘    └─────────────┘    └─────────────┘
                          │                   │
                          │                   │
                          ▼                   ▼
┌─────────────┐    ┌─────────────┐    ┌─────────────┐
│ Localizacao │◄───┤ Manutencao │    │  Inventario │
│             │    │             │    │             │
└─────────────┘    └─────────────┘    └─────────────┘
                          │                   │
                          │                   │
                          ▼                   ▼
┌─────────────┐    ┌─────────────┐    ┌─────────────┐
│ Movimentacao│    │   Baixa     │    │ Notificacao │
│             │    │             │    │             │
└─────────────┘    └─────────────┘    └─────────────┘
```

### 🎯 Responsabilidades por Camada

#### **📁 Controller Layer**
- **Responsabilidade**: Receber requisições HTTP e retornar respostas
- **Funcionalidades**: Validação de entrada, roteamento, documentação OpenAPI
- **Padrões**: REST, validação com Bean Validation, tratamento de exceções

#### **📁 Service Layer**
- **Responsabilidade**: Implementar regras de negócio e orquestrar operações
- **Funcionalidades**: Validações de negócio, transações, cache, auditoria
- **Padrões**: Transactional, Cacheable, Event-driven

#### **📁 Repository Layer**
- **Responsabilidade**: Abstrair acesso aos dados e persistência
- **Funcionalidades**: CRUD operations, queries customizadas, paginação
- **Padrões**: Repository Pattern, Query Methods, Custom Queries

#### **📁 Model Layer**
- **Responsabilidade**: Representar entidades de domínio e relacionamentos
- **Funcionalidades**: Mapeamento JPA, validações, relacionamentos
- **Padrões**: JPA Entities, Bean Validation, Audit

#### **📁 DTO Layer**
- **Responsabilidade**: Transferir dados entre camadas sem expor entidades
- **Funcionalidades**: Serialização, validação de entrada, versionamento
- **Padrões**: Data Transfer Object, Builder Pattern

#### **📁 Mapper Layer**
- **Responsabilidade**: Converter entre entidades e DTOs
- **Funcionalidades**: Mapeamento automático, customizações, performance
- **Padrões**: MapStruct, Object Mapping

## 🛠️ Tecnologias

### 🎯 Core Framework
- **Java 23** - Linguagem de programação
- **Spring Boot 3.3.0** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Spring Security** - Segurança e autenticação
- **Spring Cache** - Sistema de cache

### 🗄️ Banco de Dados
- **PostgreSQL 15+** - Banco principal
- **H2** - Banco para testes
- **Flyway** - Migração de banco
- **Hibernate** - ORM

### 🔧 Ferramentas e Bibliotecas
- **Maven** - Gerenciamento de dependências
- **MapStruct** - Mapeamento entre objetos
- **SpringDoc OpenAPI** - Documentação da API
- **Quartz** - Agendamento de tarefas
- **Redis** - Cache distribuído

### 🧪 Testes
- **JUnit 5** - Framework de testes
- **Mockito** - Mocking para testes
- **TestContainers** - Testes com containers
- **Spring Boot Test** - Testes de integração

### 🚀 DevOps e Deploy
- **Docker** - Containerização
- **Docker Compose** - Orquestração local
- **Nginx** - Proxy reverso
- **Actuator** - Monitoramento

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/manus/patrimonio/
│   │   ├── config/           # 🔧 Configurações
│   │   ├── controller/       # 🎮 Controllers REST
│   │   ├── dto/             # 📦 Data Transfer Objects
│   │   ├── enums/           # 🏷️ Enumerações
│   │   ├── exception/       # ⚠️ Tratamento de exceções
│   │   ├── mapper/          # 🔄 Mapeadores MapStruct
│   │   ├── model/           # 🏗️ Entidades JPA
│   │   ├── repository/      # 💾 Repositórios de dados
│   │   ├── service/         # 🧠 Lógica de negócio
│   │   └── util/            # 🛠️ Utilitários
│   └── resources/
│       ├── db/migration/    # 🗄️ Migrações Flyway
│       ├── application.yml  # ⚙️ Configurações
│       └── static/          # 📁 Arquivos estáticos
└── test/                    # 🧪 Testes automatizados
```

### 🏗️ Entidades Principais

- **`Bem`** - Bens patrimoniais
- **`Categoria`** - Categorias de bens
- **`Localizacao`** - Localizações físicas
- **`Usuario`** - Usuários do sistema
- **`Perfil`** - Perfis de acesso
- **`Manutencao`** - Registros de manutenção
- **`Baixa`** - Processos de baixa
- **`Inventario`** - Controles de inventário
- **`Movimentacao`** - Movimentações de bens
- **`Auditoria`** - Log de auditoria
- **`Configuracao`** - Configurações do sistema
- **`Notificacao`** - Sistema de notificações

## ⚙️ Configuração

### 🔑 Variáveis de Ambiente

```bash
# Database
DB_URL=jdbc:postgresql://localhost:5432/patrimonio_db
DB_USERNAME=postgres
DB_PASSWORD=postgres

# Redis
REDIS_HOST=localhost
REDIS_PORT=6379

# Email
MAIL_USERNAME=seu-email@gmail.com
MAIL_PASSWORD=sua-senha-app

# JWT
JWT_SECRET=seu-secret-jwt-muito-seguro
JWT_EXPIRATION=86400000
```

### 📊 Configurações do Banco

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/patrimonio_db
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
  
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
```

### 🔒 Configurações de Segurança

```yaml
spring:
  security:
    jwt:
      secret: seu-secret-jwt-muito-seguro
      expiration: 86400000
      header: Authorization
      prefix: Bearer
```

## 🚀 Executando o Projeto

### 📋 Pré-requisitos

- **Java 23** ou superior
- **Maven 3.9+**
- **PostgreSQL 15+**
- **Redis 6+**
- **Docker** (opcional)

### 🐳 Com Docker (Recomendado)

```bash
# 1. Clone o repositório
git clone https://github.com/seu-usuario/patrimonio-api.git
cd patrimonio-api

# 2. Execute com Docker Compose
docker-compose up -d

# 3. Acesse a aplicação
curl http://localhost:8080/api/v1/actuator/health
```

### 🖥️ Execução Local

```bash
# 1. Clone o repositório
git clone https://github.com/seu-usuario/patrimonio-api.git
cd patrimonio-api

# 2. Configure o banco PostgreSQL
createdb patrimonio_db

# 3. Configure as variáveis de ambiente
export DB_URL=jdbc:postgresql://localhost:5432/patrimonio_db
export DB_USERNAME=postgres
export DB_PASSWORD=postgres

# 4. Execute a aplicação
mvn spring-boot:run
```

### 🔧 Configuração do Banco

```sql
-- 1. Crie o banco de dados
CREATE DATABASE patrimonio_db;

-- 2. Execute as migrações Flyway (automático)
-- As migrações são executadas automaticamente na inicialização
```

## 🧪 Testes

### 🚀 Executando Todos os Testes

```bash
mvn test
```

### 🎯 Testes Específicos

```bash
# Testes de serviço
mvn test -Dtest="*ServiceTest"

# Testes de modelo
mvn test -Dtest="*Test"

# Testes de integração
mvn test -Dtest="*IntegrationTest"
```

### 📊 Cobertura de Testes

```bash
# Gera relatório de cobertura
mvn jacoco:report

# Abra o relatório
open target/site/jacoco/index.html
```

## 📊 API Endpoints Completos

### 🔐 Autenticação e Segurança

```
POST   /api/v1/auth/login                    # Login de usuário
POST   /api/v1/auth/refresh                  # Renovar token JWT
POST   /api/v1/auth/logout                   # Logout e invalidação de token
GET    /api/v1/auth/me                       # Informações do usuário logado
POST   /api/v1/auth/change-password          # Alterar senha
POST   /api/v1/auth/forgot-password          # Recuperação de senha
```

### 🏷️ Bens Patrimoniais (`/api/v1/bens`)

#### **Operações CRUD Básicas**
```
GET    /api/v1/bens                          # Listar todos os bens (paginado)
POST   /api/v1/bens                          # Criar novo bem
GET    /api/v1/bens/{id}                     # Buscar bem por ID
PUT    /api/v1/bens/{id}                     # Atualizar bem completo
PATCH  /api/v1/bens/{id}/status              # Atualizar apenas status
DELETE /api/v1/bens/{id}                     # Excluir bem (soft delete)
```

#### **Busca e Filtros Avançados**
```
GET    /api/v1/bens/numero-serie/{numeroSerie}    # Buscar por número de série
GET    /api/v1/bens/status/{status}               # Filtrar por status (ATIVO, INATIVO, etc.)
GET    /api/v1/bens/condicao/{condicao}           # Filtrar por condição (EXCELENTE, BOM, etc.)
GET    /api/v1/bens/categoria/{categoriaId}       # Filtrar por categoria
GET    /api/v1/bens/localizacao/{localizacaoId}   # Filtrar por localização
GET    /api/v1/bens/buscar?termo={termo}          # Busca por texto (nome, descrição, etc.)
GET    /api/v1/bens/valor?valorMinimo={min}&valorMaximo={max}  # Filtro por faixa de valor
GET    /api/v1/bens/data?dataInicio={inicio}&dataFim={fim}     # Filtro por período de aquisição
GET    /api/v1/bens/filtros                     # Busca com múltiplos filtros combinados
```

#### **Relatórios e Estatísticas**
```
GET    /api/v1/bens/estatisticas/status         # Contagem de bens por status
GET    /api/v1/bens/estatisticas/condicao       # Contagem de bens por condição
GET    /api/v1/bens/estatisticas/valor-status   # Valor total dos bens por status
GET    /api/v1/bens/recentes                     # 10 bens mais recentemente adquiridos
GET    /api/v1/bens/sem-movimentacao             # Bens que nunca foram movimentados
```

### 📋 Sistema de Inventário (`/api/v1/inventarios`)

#### **Gestão de Inventários**
```
GET    /api/v1/inventarios                      # Listar todos os inventários
POST   /api/v1/inventarios                      # Criar novo inventário
GET    /api/v1/inventarios/{id}                 # Buscar inventário por ID
PUT    /api/v1/inventarios/{id}                 # Atualizar inventário
DELETE /api/v1/inventarios/{id}                 # Excluir inventário
```

#### **Execução e Controle**
```
POST   /api/v1/inventarios/{id}/executar        # Iniciar execução do inventário
POST   /api/v1/inventarios/{id}/concluir        # Marcar inventário como concluído
POST   /api/v1/inventarios/{id}/cancelar        # Cancelar inventário
PATCH  /api/v1/inventarios/{id}/status          # Alterar status do inventário
```

#### **Busca e Filtros**
```
GET    /api/v1/inventarios/status/{status}      # Filtrar por status
GET    /api/v1/inventarios/responsavel/{responsavel}  # Filtrar por responsável
GET    /api/v1/inventarios/periodo?inicio={inicio}&fim={fim}  # Filtrar por período
GET    /api/v1/inventarios/localizacao/{localizacaoId}  # Filtrar por localização
```

### 🔧 Gestão de Manutenções (`/api/v1/manutencoes`)

#### **Operações CRUD**
```
GET    /api/v1/manutencoes                      # Listar todas as manutenções
POST   /api/v1/manutencoes                      # Criar nova manutenção
GET    /api/v1/manutencoes/{id}                 # Buscar manutenção por ID
PUT    /api/v1/manutencoes/{id}                 # Atualizar manutenção
DELETE /api/v1/manutencoes/{id}                 # Excluir manutenção
```

#### **Controle de Workflow**
```
POST   /api/v1/manutencoes/{id}/finalizar       # Finalizar manutenção
POST   /api/v1/manutencoes/{id}/cancelar        # Cancelar manutenção
PATCH  /api/v1/manutencoes/{id}/status          # Alterar status
POST   /api/v1/manutencoes/{id}/aprovar         # Aprovar manutenção
POST   /api/v1/manutencoes/{id}/rejeitar        # Rejeitar manutenção
```

#### **Busca e Relatórios**
```
GET    /api/v1/manutencoes/bem/{bemId}          # Manutenções de um bem específico
GET    /api/v1/manutencoes/status/{status}      # Filtrar por status
GET    /api/v1/manutencoes/tipo/{tipo}          # Filtrar por tipo (PREVENTIVA, CORRETIVA)
GET    /api/v1/manutencoes/periodo?inicio={inicio}&fim={fim}  # Filtrar por período
GET    /api/v1/manutencoes/fornecedor/{fornecedor}  # Filtrar por fornecedor
GET    /api/v1/manutencoes/custo?min={min}&max={max}  # Filtrar por faixa de custo
```

### 📤 Processo de Baixas (`/api/v1/baixas`)

#### **Gestão de Baixas**
```
GET    /api/v1/baixas                           # Listar todas as baixas
POST   /api/v1/baixas                           # Criar nova solicitação de baixa
GET    /api/v1/baixas/{id}                      # Buscar baixa por ID
PUT    /api/v1/baixas/{id}                      # Atualizar baixa
DELETE /api/v1/baixas/{id}                      # Excluir baixa
```

#### **Workflow de Aprovação**
```
POST   /api/v1/baixas/{id}/aprovar              # Aprovar baixa
POST   /api/v1/baixas/{id}/rejeitar             # Rejeitar baixa
POST   /api/v1/baixas/{id}/finalizar            # Finalizar processo de baixa
POST   /api/v1/baixas/{id}/vender               # Registrar venda do bem
```

#### **Busca e Relatórios**
```
GET    /api/v1/baixas/status/{status}           # Filtrar por status
GET    /api/v1/baixas/bem/{bemId}               # Baixas de um bem específico
GET    /api/v1/baixas/periodo?inicio={inicio}&fim={fim}  # Filtrar por período
GET    /api/v1/baixas/valor?min={min}&max={max} # Filtrar por valor residual
GET    /api/v1/baixas/destino/{destino}         # Filtrar por destino final
GET    /api/v1/baixas/pendentes                 # Baixas pendentes de aprovação
GET    /api/v1/baixas/aprovadas                 # Baixas aprovadas
```

### 🏢 Categorias (`/api/v1/categorias`)

```
GET    /api/v1/categorias                       # Listar todas as categorias
POST   /api/v1/categorias                       # Criar nova categoria
GET    /api/v1/categorias/{id}                  # Buscar categoria por ID
PUT    /api/v1/categorias/{id}                  # Atualizar categoria
DELETE /api/v1/categorias/{id}                  # Excluir categoria
GET    /api/v1/categorias/nome/{nome}           # Buscar por nome
GET    /api/v1/categorias/buscar?termo={termo}  # Busca por texto
GET    /api/v1/categorias/com-bens              # Categorias que possuem bens
GET    /api/v1/categorias/sem-bens              # Categorias sem bens
GET    /api/v1/categorias/estatisticas/bens     # Contagem de bens por categoria
```

### 📍 Localizações (`/api/v1/localizacoes`)

```
GET    /api/v1/localizacoes                     # Listar todas as localizações
POST   /api/v1/localizacoes                     # Criar nova localização
GET    /api/v1/localizacoes/{id}                # Buscar localização por ID
PUT    /api/v1/localizacoes/{id}                # Atualizar localização
DELETE /api/v1/localizacoes/{id}                # Excluir localização
GET    /api/v1/localizacoes/nome/{nome}         # Buscar por nome
GET    /api/v1/localizacoes/buscar?termo={termo}  # Busca por texto
GET    /api/v1/localizacoes/responsavel/{responsavel}  # Buscar por responsável
GET    /api/v1/localizacoes/endereco?endereco={endereco}  # Buscar por endereço
GET    /api/v1/localizacoes/com-bens            # Localizações com bens
GET    /api/v1/localizacoes/sem-bens            # Localizações sem bens
GET    /api/v1/localizacoes/estatisticas/bens   # Contagem de bens por localização
```

### 👥 Gestão de Usuários (`/api/v1/usuarios`)

#### **Operações CRUD**
```
GET    /api/v1/usuarios                         # Listar todos os usuários
POST   /api/v1/usuarios                         # Criar novo usuário
GET    /api/v1/usuarios/{id}                    # Buscar usuário por ID
PUT    /api/v1/usuarios/{id}                    # Atualizar usuário
DELETE /api/v1/usuarios/{id}                    # Excluir usuário
```

#### **Gestão de Perfis e Acesso**
```
POST   /api/v1/usuarios/{id}/perfis             # Atribuir perfis ao usuário
DELETE /api/v1/usuarios/{id}/perfis/{perfilId}  # Remover perfil do usuário
GET    /api/v1/usuarios/{id}/perfis             # Listar perfis do usuário
POST   /api/v1/usuarios/{id}/ativar             # Ativar usuário
POST   /api/v1/usuarios/{id}/desativar          # Desativar usuário
```

#### **Busca e Filtros**
```
GET    /api/v1/usuarios/email/{email}           # Buscar por email
GET    /api/v1/usuarios/nome/{nome}             # Buscar por nome
GET    /api/v1/usuarios/ativo/{ativo}           # Filtrar por status ativo
GET    /api/v1/usuarios/perfil/{perfilId}       # Usuários de um perfil específico
```

### 🔐 Gestão de Perfis (`/api/v1/perfis`)

```
GET    /api/v1/perfis                           # Listar todos os perfis
POST   /api/v1/perfis                           # Criar novo perfil
GET    /api/v1/perfis/{id}                      # Buscar perfil por ID
PUT    /api/v1/perfis/{id}                      # Atualizar perfil
DELETE /api/v1/perfis/{id}                      # Excluir perfil
GET    /api/v1/perfis/nome/{nome}               # Buscar por nome
GET    /api/v1/perfis/buscar?termo={termo}      # Busca por texto
GET    /api/v1/perfis/sistema                   # Perfis do sistema (não editáveis)
GET    /api/v1/perfis/editaveis                 # Perfis editáveis
```

### 🔄 Movimentações (`/api/v1/movimentacoes`)

```
GET    /api/v1/movimentacoes                    # Listar todas as movimentações
POST   /api/v1/movimentacoes                    # Criar nova movimentação
GET    /api/v1/movimentacoes/{id}               # Buscar movimentação por ID
PUT    /api/v1/movimentacoes/{id}               # Atualizar movimentação
DELETE /api/v1/movimentacoes/{id}               # Excluir movimentação
GET    /api/v1/movimentacoes/bem/{bemId}        # Movimentações de um bem
GET    /api/v1/movimentacoes/tipo/{tipo}        # Filtrar por tipo
GET    /api/v1/movimentacoes/periodo?inicio={inicio}&fim={fim}  # Filtrar por período
GET    /api/v1/movimentacoes/responsavel/{responsavel}  # Filtrar por responsável
```

### 🔍 Sistema de Auditoria (`/api/v1/auditoria`)

```
GET    /api/v1/auditoria                        # Listar todos os logs de auditoria
GET    /api/v1/auditoria/{id}                   # Buscar log específico
GET    /api/v1/auditoria/usuario/{usuarioId}    # Auditoria por usuário
GET    /api/v1/auditoria/entidade/{entidade}    # Auditoria por entidade
GET    /api/v1/auditoria/acao/{acao}            # Auditoria por tipo de ação
GET    /api/v1/auditoria/periodo?inicio={inicio}&fim={fim}  # Filtrar por período
GET    /api/v1/auditoria/ip/{ip}                # Auditoria por endereço IP
```

### ⚙️ Configurações do Sistema (`/api/v1/configuracoes`)

```
GET    /api/v1/configuracoes                    # Listar todas as configurações
POST   /api/v1/configuracoes                    # Criar nova configuração
GET    /api/v1/configuracoes/{id}               # Buscar configuração por ID
PUT    /api/v1/configuracoes/{id}               # Atualizar configuração
DELETE /api/v1/configuracoes/{id}               # Excluir configuração
GET    /api/v1/configuracoes/chave/{chave}      # Buscar por chave
GET    /api/v1/configuracoes/sistema            # Configurações do sistema
GET    /api/v1/configuracoes/editaveis          # Configurações editáveis
GET    /api/v1/configuracoes/nao-editaveis      # Configurações não editáveis
```

### 📢 Sistema de Notificações (`/api/v1/notificacoes`)

```
GET    /api/v1/notificacoes                     # Listar todas as notificações
POST   /api/v1/notificacoes                     # Criar nova notificação
GET    /api/v1/notificacoes/{id}                # Buscar notificação por ID
PUT    /api/v1/notificacoes/{id}                # Atualizar notificação
DELETE /api/v1/notificacoes/{id}                # Excluir notificação
GET    /api/v1/notificacoes/usuario/{usuarioId} # Notificações de um usuário
GET    /api/v1/notificacoes/nao-lidas           # Notificações não lidas
POST   /api/v1/notificacoes/{id}/ler            # Marcar como lida
POST   /api/v1/notificacoes/{id}/enviar         # Enviar notificação
GET    /api/v1/notificacoes/tipo/{tipo}         # Filtrar por tipo
```

### 📊 Relatórios e Analytics (`/api/v1/relatorios`)

```
GET    /api/v1/relatorios/dashboard             # Dashboard executivo em tempo real
GET    /api/v1/relatorios/depreciacao           # Relatório de depreciação
GET    /api/v1/relatorios/manutencao            # Relatório de manutenção
GET    /api/v1/relatorios/inventario            # Relatório de inventário
GET    /api/v1/relatorios/baixas                # Relatório de baixas
GET    /api/v1/relatorios/movimentacoes         # Relatório de movimentações
GET    /api/v1/relatorios/usuarios              # Relatório de usuários
GET    /api/v1/relatorios/auditoria             # Relatório de auditoria
GET    /api/v1/relatorios/exportar/{tipo}       # Exportar relatório (PDF, Excel, CSV)
```

### 📈 Monitoramento e Health Checks (`/api/v1/actuator`)

```
GET    /api/v1/actuator/health                  # Status de saúde da aplicação
GET    /api/v1/actuator/info                    # Informações da aplicação
GET    /api/v1/actuator/metrics                 # Métricas do sistema
GET    /api/v1/actuator/prometheus              # Métricas no formato Prometheus
GET    /api/v1/actuator/loggers                 # Configuração de logs
GET    /api/v1/actuator/env                     # Variáveis de ambiente
GET    /api/v1/actuator/beans                   # Beans do Spring
GET    /api/v1/actuator/mappings                # Mapeamentos de endpoints
```

## 💡 Exemplos de Uso da API

### 🔐 Autenticação

#### **Login de Usuário**
```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "admin@patrimonio.com",
    "senha": "admin123"
  }'
```

**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "refresh_token_here",
  "tipo": "Bearer",
  "expiraEm": 86400000
}
```

### 🏷️ Gestão de Bens

#### **Criar Novo Bem**
```bash
curl -X POST http://localhost:8080/api/v1/bens \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Notebook Dell Inspiron 15",
    "descricao": "Notebook para desenvolvimento",
    "numeroSerie": "NB-DELL-001",
    "categoriaId": 1,
    "localizacaoId": 1,
    "dataAquisicao": "2024-01-15",
    "valorAquisicao": 3500.00,
    "marca": "Dell",
    "modelo": "Inspiron 15",
    "fabricante": "Dell Inc.",
    "fornecedor": "Dell Store",
    "anoFabricacao": 2024,
    "garantiaAte": "2027-01-15",
    "vidaUtilAnos": 5,
    "taxaDepreciacao": 20.00,
    "centroCusto": "TI",
    "responsavel": "João Silva",
    "departamento": "Desenvolvimento"
  }'
```

#### **Buscar Bens com Filtros**
```bash
curl -X GET "http://localhost:8080/api/v1/bens/filtros?categoriaId=1&status=ATIVO&valorMinimo=1000&valorMaximo=5000" \
  -H "Authorization: Bearer YOUR_TOKEN"
```

#### **Atualizar Status de um Bem**
```bash
curl -X PATCH "http://localhost:8080/api/v1/bens/1/status?status=EM_MANUTENCAO" \
  -H "Authorization: Bearer YOUR_TOKEN"
```

### 🔧 Gestão de Manutenções

#### **Criar Manutenção Preventiva**
```bash
curl -X POST http://localhost:8080/api/v1/manutencoes \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "bemId": 1,
    "tipo": "PREVENTIVA",
    "dataInicio": "2024-08-15",
    "dataFim": "2024-08-16",
    "descricao": "Manutenção preventiva trimestral",
    "responsavel": "Técnico de TI",
    "fornecedor": "Dell Support",
    "valor": 150.00,
    "numeroNotaFiscal": "NF-2024-001",
    "observacoes": "Limpeza e verificação de sistema"
  }'
```

#### **Finalizar Manutenção**
```bash
curl -X POST "http://localhost:8080/api/v1/manutencoes/1/finalizar" \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "dataFim": "2024-08-16",
    "solucaoAplicada": "Limpeza completa e atualização de drivers",
    "recomendacoes": "Manutenção preventiva a cada 3 meses"
  }'
```

### 📋 Sistema de Inventário

#### **Criar Inventário**
```bash
curl -X POST http://localhost:8080/api/v1/inventarios \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Inventário Geral 2024",
    "dataInicio": "2024-08-01",
    "dataFim": "2024-08-31",
    "responsavel": "Maria Santos",
    "observacoes": "Inventário anual de todos os bens"
  }'
```

#### **Executar Inventário**
```bash
curl -X POST "http://localhost:8080/api/v1/inventarios/1/executar" \
  -H "Authorization: Bearer YOUR_TOKEN"
```

### 📤 Processo de Baixas

#### **Solicitar Baixa de Bem**
```bash
curl -X POST http://localhost:8080/api/v1/baixas \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "bemId": 1,
    "justificativa": "Equipamento obsoleto e sem condições de uso",
    "valorResidual": 500.00,
    "destinoFinal": "VENDA",
    "responsavel": "João Silva",
    "documentacaoAnexada": "Relatório técnico de avaliação"
  }'
```

#### **Aprovar Baixa**
```bash
curl -X POST "http://localhost:8080/api/v1/baixas/1/aprovar" \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "aprovadoPor": "Gerente de TI",
    "dataAprovacao": "2024-08-15",
    "observacoes": "Aprovado após análise técnica"
  }'
```

### 📊 Relatórios e Estatísticas

#### **Dashboard Executivo**
```bash
curl -X GET "http://localhost:8080/api/v1/relatorios/dashboard" \
  -H "Authorization: Bearer YOUR_TOKEN"
```

#### **Estatísticas de Bens por Status**
```bash
curl -X GET "http://localhost:8080/api/v1/bens/estatisticas/status" \
  -H "Authorization: Bearer YOUR_TOKEN"
```

#### **Relatório de Depreciação**
```bash
curl -X GET "http://localhost:8080/api/v1/relatorios/depreciacao?ano=2024" \
  -H "Authorization: Bearer YOUR_TOKEN"
```

### 🔍 Sistema de Auditoria

#### **Consultar Logs de Auditoria**
```bash
curl -X GET "http://localhost:8080/api/v1/auditoria?page=0&size=20" \
  -H "Authorization: Bearer YOUR_TOKEN"
```

#### **Auditoria por Usuário**
```bash
curl -X GET "http://localhost:8080/api/v1/auditoria/usuario/1" \
  -H "Authorization: Bearer YOUR_TOKEN"
```

### ⚙️ Configurações do Sistema

#### **Listar Configurações Editáveis**
```bash
curl -X GET "http://localhost:8080/api/v1/configuracoes/editaveis" \
  -H "Authorization: Bearer YOUR_TOKEN"
```

#### **Atualizar Configuração**
```bash
curl -X PUT "http://localhost:8080/api/v1/configuracoes/1" \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "valor": "25.00",
    "descricao": "Taxa de depreciação padrão atualizada"
  }'
```

### 📢 Sistema de Notificações

#### **Criar Notificação**
```bash
curl -X POST http://localhost:8080/api/v1/notificacoes \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Manutenção Preventiva Agendada",
    "mensagem": "O bem Notebook Dell precisa de manutenção preventiva",
    "tipo": "LEMBRETE",
    "usuarioId": 1,
    "prioridade": "MEDIA"
  }'
```

#### **Marcar Notificação como Lida**
```bash
curl -X POST "http://localhost:8080/api/v1/notificacoes/1/ler" \
  -H "Authorization: Bearer YOUR_TOKEN"
```

## 🗄️ Banco de Dados

### 🏗️ Esquema Principal

```sql
-- Tabelas principais
bens                    -- Bens patrimoniais
categorias             -- Categorias de bens
localizacoes           -- Localizações físicas
usuarios               -- Usuários do sistema
perfis                 -- Perfis de acesso
usuario_perfis         -- Relacionamento usuário-perfil
manutencoes            -- Registros de manutenção
baixas                 -- Processos de baixa
inventarios            -- Controles de inventário
bens_inventariados     -- Relacionamento bem-inventário
movimentacoes          -- Movimentações de bens
auditoria              -- Log de auditoria
configuracoes          -- Configurações do sistema
notificacoes           -- Sistema de notificações
```

### 🔄 Migrações Flyway

O sistema utiliza **Flyway** para gerenciar a evolução do banco de dados:

- **V1** - Estrutura inicial das tabelas
- **V2** - Dados iniciais do sistema
- **V3** - Correções na tabela auditoria
- **V4** - Colunas adicionais de auditoria
- **V5** - Colunas adicionais de baixas
- **V6** - Coluna data_aprovacao em baixas
- **V7** - Colunas adicionais de bens
- **V8** - Coluna ativo em bens
- **V9** - Colunas restantes de bens
- **V10** - Colunas fabricante e fornecedor
- **V11** - Colunas marca, modelo e observacoes
- **V12** - Coluna numero_tombamento

### 📊 Índices e Performance

```sql
-- Índices principais para performance
CREATE INDEX idx_bem_numero_serie ON bens(numero_serie);
CREATE INDEX idx_bem_categoria ON bens(categoria_id);
CREATE INDEX idx_bem_localizacao ON bens(localizacao_id);
CREATE INDEX idx_bem_ativo ON bens(ativo);
CREATE INDEX idx_manutencao_bem ON manutencoes(bem_id);
CREATE INDEX idx_baixa_bem ON baixas(bem_id);
CREATE INDEX idx_auditoria_usuario ON auditoria(usuario_id);
CREATE INDEX idx_auditoria_data ON auditoria(data_acao);
```

## 🔒 Segurança

### 🛡️ Autenticação

- **JWT (JSON Web Tokens)** para autenticação stateless
- **Refresh Tokens** para renovação automática
- **Expiração configurável** dos tokens
- **Blacklist** de tokens revogados

### 🔐 Autorização

- **Role-Based Access Control (RBAC)**
- **Perfis predefinidos**: ADMIN, GESTOR, OPERADOR, CONSULTA
- **Permissões granulares** por funcionalidade
- **Controle de acesso** baseado em anotações

### 🚨 Segurança da API

- **CORS configurado** para origens permitidas
- **Validação de entrada** com Bean Validation
- **Sanitização de dados** para prevenir injeções
- **Rate limiting** para prevenir abuso
- **Logs de auditoria** para todas as operações

### 🔑 Perfis de Acesso

```java
// ADMIN - Acesso total ao sistema
// GESTOR - Gestão de bens, aprovações, relatórios
// OPERADOR - Operações diárias, manutenções, inventários
// CONSULTA - Apenas visualização e relatórios
```

## 📈 Monitoramento

### 📊 Spring Boot Actuator

```yaml
management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      show-details: when-authorized
```

### 🔍 Endpoints de Monitoramento

```
GET /api/v1/actuator/health          # Status de saúde
GET /api/v1/actuator/info            # Informações da aplicação
GET /api/v1/actuator/metrics         # Métricas do sistema
GET /api/v1/actuator/prometheus      # Métricas Prometheus
GET /api/v1/actuator/loggers         # Configuração de logs
```

### 📈 Métricas Disponíveis

- **Performance**: Tempo de resposta, throughput
- **Recursos**: Uso de CPU, memória, disco
- **Banco de Dados**: Conexões, queries, transações
- **Cache**: Hit/miss ratio, tamanho do cache
- **Negócio**: Contadores de operações, erros

### 📝 Logs

```yaml
logging:
  level:
    com.manus.patrimonio: INFO
    org.springframework.security: DEBUG
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
```

## 🐳 Docker

### 🏗️ Dockerfile

```dockerfile
FROM openjdk:23-jdk-slim
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

### 🚀 Docker Compose

```yaml
version: '3.8'
services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=docker
    depends_on:
      - postgres
      - redis
  
  postgres:
    image: postgres:15
    environment:
      POSTGRES_DB: patrimonio_db
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
  
  redis:
    image: redis:7-alpine
    ports:
      - "6379:6379"
    volumes:
      - redis_data:/data

volumes:
  postgres_data:
  redis_data:
```

### 🚀 Comandos Docker

```bash
# Construir e executar
docker-compose up --build

# Executar em background
docker-compose up -d

# Parar serviços
docker-compose down

# Ver logs
docker-compose logs -f app

# Executar comandos no container
docker-compose exec app mvn test
```

## 📚 Documentação

### 🔍 Swagger/OpenAPI

A documentação da API está disponível em:

```
http://localhost:8080/api/v1/swagger-ui.html
```

### 📖 Documentação Técnica

- **JavaDoc** para todas as classes e métodos
- **README** detalhado para cada módulo
- **Exemplos de uso** para endpoints principais
- **Diagramas de arquitetura** e fluxos

### 🧪 Postman Collection

```bash
# Importe a collection do Postman
patrimonio-api.postman_collection.json
```

## 🤝 Contribuição

### 📋 Como Contribuir

1. **Fork** o projeto
2. **Crie** uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. **Commit** suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. **Push** para a branch (`git push origin feature/AmazingFeature`)
5. **Abra** um Pull Request

### 🧪 Padrões de Código

- **Java Code Style** seguindo Google Style
- **Testes unitários** para todas as funcionalidades
- **Documentação** atualizada para mudanças
- **Commits semânticos** com mensagens claras

### 🐛 Reportando Bugs

- Use o template de issue do GitHub
- Inclua passos para reproduzir o bug
- Adicione logs e screenshots quando relevante
- Especifique versão do sistema e ambiente

## 📄 Licença

Este projeto está licenciado sob a **MIT License** - veja o arquivo [LICENSE](LICENSE) para detalhes.

## 🙏 Agradecimentos

- **Spring Boot Team** pelo excelente framework
- **Comunidade Java** pelo suporte e contribuições
- **Contribuidores** que ajudaram a melhorar o projeto

## 📞 Suporte

- **Issues**: [GitHub Issues](https://github.com/seu-usuario/patrimonio-api/issues)
- **Documentação**: [Wiki do Projeto](https://github.com/seu-usuario/patrimonio-api/wiki)
- **Email**: suporte@patrimonio-api.com

---

**⭐ Se este projeto foi útil para você, considere dar uma estrela no GitHub!**

---

*Desenvolvido com ❤️ pela equipe Patrimonio API*

