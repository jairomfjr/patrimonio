# Swagger/OpenAPI - Sistema de Gestão de Bens Patrimoniais

## Visão Geral

Este projeto implementa o Swagger/OpenAPI para documentação automática da API REST. O Swagger fornece uma interface interativa para testar e explorar todos os endpoints disponíveis.

## Acesso ao Swagger

### Interface Web (Swagger UI)
- **URL**: `http://localhost:8080/api/v1/swagger-ui/index.html`
- **Descrição**: Interface gráfica interativa para testar a API

### Documentação da API (OpenAPI JSON)
- **URL**: `http://localhost:8080/api/v1/api-docs`
- **Descrição**: Especificação OpenAPI em formato JSON

## Funcionalidades do Swagger

### 1. Documentação Automática
- Todos os endpoints são documentados automaticamente
- Schemas dos DTOs são gerados dinamicamente
- Validações e regras de negócio são exibidas

### 2. Interface Interativa
- Teste direto dos endpoints
- Visualização dos parâmetros de entrada
- Exemplos de requisições e respostas
- Códigos de status HTTP

### 3. Endpoints Documentados

#### Usuários
- CRUD completo de usuários
- Gerenciamento de perfis
- Autenticação e autorização
- Estatísticas e consultas avançadas

#### Bens Patrimoniais
- Cadastro e gestão de bens
- Categorização e localização
- Status e condições
- Filtros e buscas avançadas

#### Categorias
- Gerenciamento de categorias
- Associação com bens
- Estatísticas de uso

#### Localizações
- Controle de localizações
- Responsáveis e endereços
- Relacionamento com bens

#### Manutenções
- Agendamento e controle
- Tipos e status
- Custos e fornecedores
- Relatórios e estatísticas

#### Baixas
- Processo de baixa de bens
- Aprovações e vendas
- Controle administrativo
- Relatórios financeiros

#### Auditoria
- Rastreamento de todas as operações
- Logs de usuários e ações
- Histórico de mudanças
- Relatórios de segurança

#### Configurações
- Parâmetros do sistema
- Configurações editáveis
- Valores padrão
- Controle de versão

#### Notificações
- Sistema de alertas
- Prioridades e tipos
- Envio por email/push
- Controle de leitura

## Como Usar

### 1. Acesse o Swagger UI
Abra o navegador e acesse: `http://localhost:8080/api/v1/swagger-ui/index.html`

### 2. Explore os Endpoints
- Clique em qualquer endpoint para expandir
- Veja os parâmetros necessários
- Teste com diferentes valores

### 3. Execute Testes
- Use o botão "Try it out"
- Preencha os parâmetros
- Clique em "Execute"
- Veja a resposta em tempo real

### 4. Entenda os Schemas
- Visualize a estrutura dos DTOs
- Veja as validações aplicadas
- Entenda os tipos de dados

## Configuração

### Dependências
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.3.0</version>
</dependency>
```

### Configuração no application.yml
```yaml
springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
    operations-sorter: method
    tags-sorter: alpha
    enabled: true
  default-produces-media-type: application/json
  default-consumes-media-type: application/json
```

### Classe de Configuração
```java
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Gestão de Bens Patrimoniais")
                        .description("API REST para gerenciamento completo de bens patrimoniais")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Manus AI")
                                .email("contato@manus.ai")
                                .url("https://manus.ai"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor de Desenvolvimento"),
                        new Server()
                                .url("https://patrimonio.manus.ai")
                                .description("Servidor de Produção")
                ));
    }
}
```

## Benefícios

1. **Documentação Automática**: Sempre atualizada com o código
2. **Testes Interativos**: Facilita o desenvolvimento e testes
3. **Padrão da Indústria**: OpenAPI 3.0 é o padrão atual
4. **Integração com Ferramentas**: Suporte a várias ferramentas de desenvolvimento
5. **Colaboração**: Facilita a comunicação entre desenvolvedores e usuários

## Troubleshooting

### Swagger UI não carrega
- Verifique se a aplicação está rodando na porta 8080
- Confirme se as dependências estão no classpath
- Verifique os logs da aplicação

### Endpoints não aparecem
- Certifique-se de que os controllers estão anotados corretamente
- Verifique se há erros de compilação
- Confirme se o componente scan está funcionando

### Erro de CORS
- Verifique a configuração de CORS
- Confirme se o frontend está acessando a URL correta

## Recursos Adicionais

- **Validação**: Todos os DTOs incluem validações Bean Validation
- **Segurança**: Endpoints protegidos são marcados adequadamente
- **Paginação**: Suporte completo a paginação com Spring Data
- **Filtros**: Endpoints de busca com múltiplos filtros
- **Estatísticas**: Métricas e relatórios em tempo real

## Suporte

Para dúvidas ou problemas com o Swagger:
- Consulte a documentação oficial: https://springdoc.org/
- Verifique os logs da aplicação
- Entre em contato com a equipe de desenvolvimento
