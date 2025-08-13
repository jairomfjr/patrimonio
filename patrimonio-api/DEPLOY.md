# Guia de Deploy - Sistema de Gestão de Bens Patrimoniais

## Visão Geral

Este guia fornece instruções detalhadas para fazer o deploy do Sistema de Gestão de Bens Patrimoniais em diferentes ambientes, desde desenvolvimento local até produção em nuvem.

## Pré-requisitos

### Ambiente Local
- Java 17 ou superior
- Maven 3.6+
- Docker e Docker Compose
- Git

### Ambiente de Produção
- Servidor Linux (Ubuntu 20.04+ recomendado)
- Docker e Docker Compose
- Nginx (se não usar o container)
- PostgreSQL (se não usar o container)
- Certificado SSL (para HTTPS)

## Deploy Local com Docker

### 1. Preparação do Ambiente

```bash
# Clone o repositório
git clone <repository-url>
cd sistema-patrimonio-robusto

# Verifique se o Docker está rodando
docker --version
docker-compose --version
```

### 2. Build e Execução

```bash
# Build da aplicação
mvn clean package -DskipTests

# Subir todos os serviços
docker-compose up -d

# Verificar status dos containers
docker-compose ps

# Visualizar logs
docker-compose logs -f app
```

### 3. Verificação

- **API**: http://localhost:8080/api
- **Swagger**: http://localhost:8080/swagger-ui.html
- **Health Check**: http://localhost:8080/actuator/health
- **Nginx**: http://localhost (se habilitado)

## Deploy em Produção

### 1. Preparação do Servidor

```bash
# Atualizar sistema
sudo apt update && sudo apt upgrade -y

# Instalar Docker
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh
sudo usermod -aG docker $USER

# Instalar Docker Compose
sudo curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

# Reiniciar sessão para aplicar permissões
logout
```

### 2. Configuração de Produção

```bash
# Criar diretório da aplicação
sudo mkdir -p /opt/patrimonio
sudo chown $USER:$USER /opt/patrimonio
cd /opt/patrimonio

# Clonar repositório
git clone <repository-url> .

# Configurar variáveis de ambiente
cp .env.example .env
nano .env
```

### 3. Configuração de Ambiente (.env)

```bash
# Banco de dados
POSTGRES_DB=patrimonio_prod
POSTGRES_USER=patrimonio_user
POSTGRES_PASSWORD=sua_senha_segura_aqui

# Aplicação
SPRING_PROFILES_ACTIVE=prod
JAVA_OPTS=-Xmx2g -Xms1g

# Nginx
NGINX_HOST=seu-dominio.com
SSL_CERT_PATH=/etc/ssl/certs/seu-dominio.crt
SSL_KEY_PATH=/etc/ssl/private/seu-dominio.key
```

### 4. SSL/TLS (HTTPS)

```bash
# Criar diretório para certificados
mkdir -p ssl

# Opção 1: Let's Encrypt (recomendado)
sudo apt install certbot
sudo certbot certonly --standalone -d seu-dominio.com
sudo cp /etc/letsencrypt/live/seu-dominio.com/fullchain.pem ssl/cert.pem
sudo cp /etc/letsencrypt/live/seu-dominio.com/privkey.pem ssl/key.pem

# Opção 2: Certificado auto-assinado (apenas desenvolvimento)
openssl req -x509 -nodes -days 365 -newkey rsa:2048 \
  -keyout ssl/key.pem -out ssl/cert.pem
```

### 5. Deploy de Produção

```bash
# Build da aplicação
mvn clean package -DskipTests

# Subir serviços de produção
docker-compose -f docker-compose.yml -f docker-compose.prod.yml up -d

# Verificar status
docker-compose ps
docker-compose logs app
```

## Configurações Específicas por Ambiente

### Desenvolvimento (application-dev.yml)

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:patrimonio_dev
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: create-drop
  h2:
    console:
      enabled: true

logging:
  level:
    com.manus.patrimonio: DEBUG
```

### Teste (application-test.yml)

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:patrimonio_test
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: false

logging:
  level:
    com.manus.patrimonio: WARN
```

### Produção (application-prod.yml)

```yaml
spring:
  datasource:
    url: jdbc:postgresql://postgres:5432/${POSTGRES_DB}
    username: ${POSTGRES_USER}
    password: ${POSTGRES_PASSWORD}
  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: false
  
server:
  port: 8080
  servlet:
    context-path: /

logging:
  level:
    com.manus.patrimonio: INFO
    org.springframework.security: WARN
  file:
    name: /var/log/patrimonio/application.log
```

## Monitoramento e Manutenção

### 1. Health Checks

```bash
# Verificar saúde da aplicação
curl http://localhost:8080/actuator/health

# Verificar métricas
curl http://localhost:8080/actuator/metrics

# Verificar informações
curl http://localhost:8080/actuator/info
```

### 2. Logs

```bash
# Logs da aplicação
docker-compose logs -f app

# Logs do banco
docker-compose logs -f postgres

# Logs do Nginx
docker-compose logs -f nginx

# Logs salvos em arquivo (produção)
tail -f /var/log/patrimonio/application.log
```

### 3. Backup do Banco

```bash
# Backup manual
docker-compose exec postgres pg_dump -U patrimonio_user patrimonio_db > backup_$(date +%Y%m%d_%H%M%S).sql

# Script de backup automático
#!/bin/bash
BACKUP_DIR="/opt/patrimonio/backups"
DATE=$(date +%Y%m%d_%H%M%S)
mkdir -p $BACKUP_DIR

docker-compose exec -T postgres pg_dump -U patrimonio_user patrimonio_db > $BACKUP_DIR/backup_$DATE.sql
gzip $BACKUP_DIR/backup_$DATE.sql

# Manter apenas últimos 7 backups
find $BACKUP_DIR -name "backup_*.sql.gz" -mtime +7 -delete
```

### 4. Atualizações

```bash
# Atualizar código
git pull origin main

# Rebuild da aplicação
mvn clean package -DskipTests

# Atualizar containers
docker-compose build app
docker-compose up -d app

# Verificar se tudo está funcionando
curl http://localhost:8080/actuator/health
```

## Troubleshooting

### Problemas Comuns

**1. Aplicação não inicia**
```bash
# Verificar logs
docker-compose logs app

# Verificar se o banco está rodando
docker-compose ps postgres

# Verificar conectividade
docker-compose exec app ping postgres
```

**2. Erro de conexão com banco**
```bash
# Verificar variáveis de ambiente
docker-compose exec app env | grep SPRING

# Testar conexão manual
docker-compose exec postgres psql -U patrimonio_user -d patrimonio_db
```

**3. Problemas de performance**
```bash
# Verificar uso de recursos
docker stats

# Ajustar limites de memória no docker-compose.yml
services:
  app:
    deploy:
      resources:
        limits:
          memory: 2G
        reservations:
          memory: 1G
```

**4. Problemas de SSL**
```bash
# Verificar certificados
openssl x509 -in ssl/cert.pem -text -noout

# Testar configuração SSL
curl -I https://seu-dominio.com
```

### Comandos Úteis

```bash
# Reiniciar apenas a aplicação
docker-compose restart app

# Rebuild completo
docker-compose down
docker-compose build --no-cache
docker-compose up -d

# Limpar volumes (CUIDADO: apaga dados)
docker-compose down -v

# Verificar uso de espaço
docker system df

# Limpar recursos não utilizados
docker system prune -a
```

## Segurança

### 1. Configurações de Firewall

```bash
# UFW (Ubuntu)
sudo ufw allow 22/tcp    # SSH
sudo ufw allow 80/tcp    # HTTP
sudo ufw allow 443/tcp   # HTTPS
sudo ufw enable

# Bloquear acesso direto à aplicação
sudo ufw deny 8080/tcp
```

### 2. Configurações do Docker

```bash
# Executar containers como usuário não-root
# (já configurado no Dockerfile)

# Limitar recursos
# (configurado no docker-compose.yml)

# Usar secrets para senhas
echo "sua_senha_segura" | docker secret create postgres_password -
```

### 3. Configurações do Nginx

```nginx
# Rate limiting (já configurado)
limit_req_zone $binary_remote_addr zone=api:10m rate=10r/s;

# Headers de segurança (já configurados)
add_header X-Frame-Options "SAMEORIGIN" always;
add_header X-Content-Type-Options "nosniff" always;
```

## Escalabilidade

### 1. Load Balancer

```yaml
# docker-compose.yml
services:
  app1:
    build: .
    environment:
      - INSTANCE_NAME=app1
  
  app2:
    build: .
    environment:
      - INSTANCE_NAME=app2
  
  nginx:
    # Configurar upstream com múltiplas instâncias
```

### 2. Cache Redis

```yaml
# Já configurado no docker-compose.yml
redis:
  image: redis:7-alpine
  volumes:
    - redis_data:/data
```

### 3. Monitoramento Avançado

```yaml
# Adicionar ao docker-compose.yml
prometheus:
  image: prom/prometheus
  ports:
    - "9090:9090"

grafana:
  image: grafana/grafana
  ports:
    - "3000:3000"
```

## Conclusão

Este guia fornece uma base sólida para o deploy do Sistema de Gestão de Bens Patrimoniais. Para ambientes de produção críticos, considere:

- Implementar CI/CD com GitHub Actions ou Jenkins
- Usar orquestração com Kubernetes
- Implementar monitoramento avançado
- Configurar alertas automáticos
- Realizar testes de carga regulares

Para suporte adicional, consulte a documentação da API em `/swagger-ui.html` ou entre em contato com a equipe de desenvolvimento.

