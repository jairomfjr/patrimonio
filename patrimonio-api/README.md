# Sistema Robusto de Gestão de Bens Patrimoniais

## Visão Geral

O Sistema Robusto de Gestão de Bens Patrimoniais é uma aplicação empresarial desenvolvida em Java com Spring Boot, projetada para oferecer controle completo e eficiente do patrimônio organizacional. O sistema utiliza nomes de entidades expressivos e intuitivos, facilitando a compreensão e manutenção do código.

### Características Principais

**Entidades Expressivas e Bem Definidas:**
- **Bem**: Entidade central que representa cada item patrimonial
- **Categoria**: Classificação dos bens por tipo ou natureza
- **Localização**: Controle da localização física dos bens
- **Movimentação**: Histórico completo de transferências e alterações
- **Inventário**: Gestão de inventários periódicos

**Funcionalidades Avançadas:**
- CRUD completo para todas as entidades
- Sistema de busca avançada com múltiplos filtros
- Controle de status e condições dos bens
- Histórico detalhado de movimentações
- Relatórios e estatísticas em tempo real
- API REST documentada com Swagger
- Segurança com autenticação e autorização
- Auditoria automática de alterações

## Tecnologias Utilizadas

### Backend
- **Java 17**: Linguagem de programação principal
- **Spring Boot 3.2.0**: Framework base da aplicação
- **Spring Data JPA**: Persistência e acesso a dados
- **Spring Security**: Segurança e autenticação
- **Spring Web**: APIs REST
- **Spring Validation**: Validação de dados

### Banco de Dados
- **H2 Database**: Banco em memória para desenvolvimento
- **PostgreSQL**: Banco para produção (configurado)

### Documentação e Testes
- **SpringDoc OpenAPI**: Documentação automática da API
- **Swagger UI**: Interface interativa para testes
- **JUnit 5**: Framework de testes
- **Mockito**: Mocks para testes unitários

### Ferramentas de Build
- **Maven**: Gerenciamento de dependências
- **Docker**: Containerização da aplicação

## Arquitetura do Sistema

### Estrutura de Pacotes

```
com.manus.patrimonio/
├── controller/          # Controladores REST
├── service/             # Camada de serviços e regras de negócio
├── repository/          # Repositórios JPA
├── model/               # Entidades JPA
├── dto/                 # Data Transfer Objects
├── exception/           # Tratamento de exceções
├── config/              # Configurações do Spring
├── util/                # Classes utilitárias
└── enums/               # Enumerações do sistema
```

### Padrões Arquiteturais

**Arquitetura em Camadas:**
- **Camada de Apresentação**: Controllers REST
- **Camada de Negócio**: Services com regras de negócio
- **Camada de Persistência**: Repositories e Entities
- **Camada de Configuração**: Configurações do Spring

**Padrões Utilizados:**
- Repository Pattern para acesso a dados
- DTO Pattern para transferência de dados
- Builder Pattern para construção de objetos
- Strategy Pattern para validações

## Modelo de Dados Detalhado

### Entidade Bem (Asset)

A entidade central do sistema que representa cada item patrimonial da organização.

**Atributos Principais:**
- `id`: Identificador único (Long)
- `nome`: Nome descritivo do bem (String, 2-150 caracteres)
- `descricao`: Descrição detalhada (String, até 1000 caracteres)
- `numeroSerie`: Número de série único (String, até 100 caracteres)
- `dataAquisicao`: Data de aquisição (LocalDate, não pode ser futura)
- `valorAquisicao`: Valor monetário (BigDecimal, positivo)
- `status`: Status atual (Enum: ATIVO, INATIVO, EM_MANUTENCAO, BAIXADO)
- `condicao`: Estado físico (Enum: NOVO, BOM, REGULAR, RUIM)
- `observacoes`: Campo livre para anotações (String, até 1000 caracteres)

**Relacionamentos:**
- `categoria`: Categoria do bem (ManyToOne, obrigatório)
- `localizacaoAtual`: Localização atual (ManyToOne, obrigatório)
- `movimentacoes`: Histórico de movimentações (OneToMany)
- `inventarios`: Inventários que incluem o bem (ManyToMany)

### Entidade Categoria (Category)

Classificação dos bens por tipo, natureza ou departamento.

**Atributos:**
- `id`: Identificador único
- `nome`: Nome da categoria (String, 2-100 caracteres, único)
- `descricao`: Descrição da categoria (String, até 500 caracteres)

**Relacionamentos:**
- `bens`: Lista de bens da categoria (OneToMany)

### Entidade Localização (Location)

Representa os locais físicos onde os bens podem estar localizados.

**Atributos:**
- `id`: Identificador único
- `nome`: Nome da localização (String, 2-100 caracteres, único)
- `endereco`: Endereço físico (String, até 200 caracteres)
- `responsavel`: Responsável pela localização (String, até 100 caracteres)
- `contato`: Informações de contato (String, até 50 caracteres)
- `descricao`: Descrição adicional (String, até 500 caracteres)

**Relacionamentos:**
- `bensAtuais`: Bens atualmente na localização (OneToMany)
- `movimentacaesOrigem`: Movimentações de saída (OneToMany)
- `movimentacaesDestino`: Movimentações de entrada (OneToMany)

### Entidade Movimentação (Movement)

Registra todas as movimentações e alterações dos bens.

**Atributos:**
- `id`: Identificador único
- `dataMovimentacao`: Data e hora da movimentação (LocalDateTime)
- `tipoMovimentacao`: Tipo (Enum: ENTRADA, SAIDA, TRANSFERENCIA, MANUTENCAO, BAIXA)
- `responsavelMovimentacao`: Pessoa responsável (String, 2-100 caracteres)
- `observacoes`: Observações sobre a movimentação (String, até 1000 caracteres)

**Relacionamentos:**
- `bem`: Bem movimentado (ManyToOne, obrigatório)
- `localizacaoOrigem`: Local de origem (ManyToOne, opcional)
- `localizacaoDestino`: Local de destino (ManyToOne, opcional)

### Entidade Inventário (Inventory)

Controla os inventários periódicos realizados na organização.

**Atributos:**
- `id`: Identificador único
- `nome`: Nome do inventário (String, 2-150 caracteres)
- `dataInicio`: Data de início (LocalDate)
- `dataFim`: Data de conclusão (LocalDate, opcional)
- `responsavel`: Responsável pelo inventário (String, 2-100 caracteres)
- `status`: Status (Enum: EM_ANDAMENTO, CONCLUIDO, CANCELADO)
- `observacoes`: Observações gerais (String, até 1000 caracteres)

**Relacionamentos:**
- `bensInventariados`: Bens incluídos no inventário (ManyToMany)

## API REST Endpoints

### Categorias (/api/categorias)

**Operações de Consulta:**
- `GET /api/categorias` - Lista todas as categorias (paginado)
- `GET /api/categorias/{id}` - Busca categoria por ID
- `GET /api/categorias/nome/{nome}` - Busca categoria por nome
- `GET /api/categorias/buscar?termo={termo}` - Busca por texto
- `GET /api/categorias/ordenadas` - Lista ordenada por nome
- `GET /api/categorias/com-bens` - Categorias com bens associados
- `GET /api/categorias/sem-bens` - Categorias sem bens
- `GET /api/categorias/estatisticas/bens` - Contagem de bens por categoria

**Operações de Modificação:**
- `POST /api/categorias` - Cria nova categoria
- `PUT /api/categorias/{id}` - Atualiza categoria
- `DELETE /api/categorias/{id}` - Exclui categoria (se não houver bens)

### Localizações (/api/localizacoes)

**Operações de Consulta:**
- `GET /api/localizacoes` - Lista todas as localizações (paginado)
- `GET /api/localizacoes/{id}` - Busca localização por ID
- `GET /api/localizacoes/nome/{nome}` - Busca por nome
- `GET /api/localizacoes/buscar?termo={termo}` - Busca por texto
- `GET /api/localizacoes/ordenadas` - Lista ordenada por nome
- `GET /api/localizacoes/responsavel/{responsavel}` - Busca por responsável
- `GET /api/localizacoes/endereco?endereco={endereco}` - Busca por endereço
- `GET /api/localizacoes/com-bens` - Localizações com bens
- `GET /api/localizacoes/sem-bens` - Localizações sem bens
- `GET /api/localizacoes/estatisticas/bens` - Contagem de bens por localização

**Operações de Modificação:**
- `POST /api/localizacoes` - Cria nova localização
- `PUT /api/localizacoes/{id}` - Atualiza localização
- `DELETE /api/localizacoes/{id}` - Exclui localização (se não houver bens)

### Bens Patrimoniais (/api/bens)

**Operações de Consulta:**
- `GET /api/bens` - Lista todos os bens (paginado)
- `GET /api/bens/{id}` - Busca bem por ID
- `GET /api/bens/numero-serie/{numeroSerie}` - Busca por número de série
- `GET /api/bens/status/{status}` - Filtra por status
- `GET /api/bens/condicao/{condicao}` - Filtra por condição
- `GET /api/bens/categoria/{categoriaId}` - Filtra por categoria
- `GET /api/bens/localizacao/{localizacaoId}` - Filtra por localização
- `GET /api/bens/buscar?termo={termo}` - Busca por texto
- `GET /api/bens/valor?valorMinimo={min}&valorMaximo={max}` - Filtra por faixa de valor
- `GET /api/bens/data?dataInicio={inicio}&dataFim={fim}` - Filtra por faixa de data
- `GET /api/bens/filtros` - Busca com múltiplos filtros
- `GET /api/bens/recentes` - 10 bens mais recentes
- `GET /api/bens/sem-movimentacao` - Bens nunca movimentados

**Estatísticas:**
- `GET /api/bens/estatisticas/status` - Contagem por status
- `GET /api/bens/estatisticas/condicao` - Contagem por condição
- `GET /api/bens/estatisticas/valor-status` - Valor total por status

**Operações de Modificação:**
- `POST /api/bens` - Cria novo bem
- `PUT /api/bens/{id}` - Atualiza bem
- `PATCH /api/bens/{id}/status?status={status}` - Atualiza apenas status
- `DELETE /api/bens/{id}` - Exclui bem (se não estiver ativo)

## Segurança e Autenticação

### Modelo de Segurança

O sistema implementa autenticação HTTP Basic com dois níveis de acesso:

**Usuários Padrão:**
- **user/user123** (ROLE_USER): Acesso de leitura a todos os endpoints
- **admin/admin123** (ROLE_ADMIN): Acesso completo (leitura e escrita)

**Permissões por Endpoint:**
- **Endpoints GET**: Requer ROLE_USER ou ROLE_ADMIN
- **Endpoints POST/PUT/PATCH/DELETE**: Requer ROLE_ADMIN
- **Documentação e Health Check**: Acesso público
- **Console H2**: Acesso público (apenas desenvolvimento)

### Configuração CORS

O sistema está configurado para aceitar requisições de qualquer origem, facilitando o desenvolvimento de frontends separados.

## Validações e Regras de Negócio

### Validações de Entrada

**Campos Obrigatórios:**
- Nomes de entidades (mínimo 2 caracteres)
- Datas de aquisição (não podem ser futuras)
- Valores monetários (devem ser positivos)
- Status e condições (valores válidos dos enums)

**Validações de Unicidade:**
- Números de série dos bens
- Nomes de categorias
- Nomes de localizações

### Regras de Negócio

**Exclusão de Entidades:**
- Categorias só podem ser excluídas se não houver bens associados
- Localizações só podem ser excluídas se não houver bens atualmente nelas
- Bens só podem ser excluídos se não estiverem com status ATIVO

**Movimentações:**
- Toda movimentação deve ter um responsável identificado
- Movimentações de transferência devem ter origem e destino diferentes
- Movimentações de entrada podem não ter localização de origem

## Configuração e Execução

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6 ou superior
- Docker (opcional, para containerização)

### Executando Localmente

1. **Clone o repositório:**
```bash
git clone <repository-url>
cd sistema-patrimonio-robusto
```

2. **Compile o projeto:**
```bash
mvn clean compile
```

3. **Execute os testes:**
```bash
mvn test
```

4. **Execute a aplicação:**
```bash
mvn spring-boot:run
```

### Acessando a Aplicação

- **API Base**: http://localhost:8080/api
- **Documentação Swagger**: http://localhost:8080/swagger-ui.html
- **Console H2**: http://localhost:8080/h2-console
- **Health Check**: http://localhost:8080/actuator/health

### Dados de Teste

O sistema é inicializado com dados de exemplo:

**Categorias:**
- Informática
- Mobiliário
- Veículos
- Equipamentos

**Localizações:**
- Matriz (Rua Principal, 123)
- Filial Norte (Av. Norte, 456)
- Almoxarifado (Rua Industrial, 789)

**Bens de Exemplo:**
- Notebook Dell Inspiron 15 (Ativo)
- Mesa de Escritório (Ativo)
- Impressora HP LaserJet (Em Manutenção)
- Cadeira Ergonômica (Ativo)
- Monitor LG 24" (Ativo)

## Containerização com Docker

### Dockerfile

O projeto inclui um Dockerfile otimizado para produção:

```dockerfile
FROM openjdk:17-jre-slim
COPY target/sistema-patrimonio-robusto-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### Executando com Docker

```bash
# Build da imagem
docker build -t sistema-patrimonio .

# Execução do container
docker run -p 8080:8080 sistema-patrimonio
```

## Monitoramento e Observabilidade

### Actuator Endpoints

- **Health**: `/actuator/health` - Status da aplicação
- **Info**: `/actuator/info` - Informações da aplicação
- **Metrics**: `/actuator/metrics` - Métricas da aplicação

### Logs

O sistema utiliza logging estruturado com diferentes níveis:
- **DEBUG**: Informações detalhadas para desenvolvimento
- **INFO**: Informações gerais de funcionamento
- **WARN**: Avisos sobre situações não críticas
- **ERROR**: Erros que requerem atenção

## Extensibilidade e Customização

### Adicionando Novas Entidades

Para adicionar uma nova entidade ao sistema:

1. Criar a classe de entidade em `model/`
2. Criar o repositório em `repository/`
3. Criar o DTO em `dto/`
4. Criar o serviço em `service/`
5. Criar o controlador em `controller/`
6. Adicionar validações e testes

### Personalizando Validações

As validações podem ser customizadas através de:
- Anotações Bean Validation
- Validadores customizados
- Regras de negócio nos serviços

### Configurando Diferentes Ambientes

O sistema suporta múltiplos perfis:
- `dev`: Desenvolvimento (H2, logs detalhados)
- `test`: Testes (H2, logs mínimos)
- `prod`: Produção (PostgreSQL, logs otimizados)

## Roadmap e Melhorias Futuras

### Funcionalidades Planejadas

**Curto Prazo:**
- Relatórios em PDF/Excel
- Upload de imagens dos bens
- Notificações por email
- Dashboard com gráficos

**Médio Prazo:**
- Aplicativo mobile
- Integração com sistemas ERP
- Códigos de barras/QR codes
- Workflow de aprovações

**Longo Prazo:**
- Inteligência artificial para previsões
- IoT para rastreamento automático
- Blockchain para auditoria
- Análise preditiva de manutenção

### Melhorias Técnicas

- Cache distribuído com Redis
- Mensageria com RabbitMQ
- Microserviços com Spring Cloud
- Observabilidade com Micrometer

## Contribuição e Desenvolvimento

### Padrões de Código

- Seguir convenções Java padrão
- Usar anotações Spring apropriadas
- Implementar testes para novas funcionalidades
- Documentar APIs com Swagger
- Manter cobertura de testes acima de 80%

### Estrutura de Commits

```
tipo(escopo): descrição

Exemplos:
feat(bem): adicionar busca por faixa de valor
fix(security): corrigir validação de permissões
docs(readme): atualizar documentação da API
test(service): adicionar testes para BemService
```

## Suporte e Documentação

### Recursos Disponíveis

- **Documentação da API**: Swagger UI integrado
- **Código Fonte**: Comentários detalhados
- **Testes**: Exemplos de uso das funcionalidades
- **Logs**: Informações de debug e erro

### Solução de Problemas

**Problemas Comuns:**
1. Erro de conexão com banco: Verificar configurações em `application.yml`
2. Erro de autenticação: Verificar credenciais (user/user123 ou admin/admin123)
3. Erro de validação: Verificar formato dos dados enviados
4. Erro 404: Verificar se a aplicação está rodando na porta 8080

## Licença e Créditos

**Desenvolvido por**: Manus AI  
**Versão**: 1.0.0  
**Licença**: MIT License  
**Data**: 2024

Este sistema foi desenvolvido seguindo as melhores práticas de desenvolvimento Java e Spring Boot, com foco em robustez, escalabilidade e facilidade de manutenção.

