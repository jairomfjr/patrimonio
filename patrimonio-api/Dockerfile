# Dockerfile para Sistema de Gestão de Bens Patrimoniais
# Baseado em OpenJDK 17 para melhor performance e segurança

# Estágio de build
FROM maven:3.9.4-openjdk-17 AS build
WORKDIR /app

# Copiar arquivos de configuração do Maven
COPY pom.xml .
COPY src ./src

# Compilar e empacotar a aplicação
RUN mvn clean package -DskipTests

# Estágio de produção
FROM openjdk:17-jre-slim

# Criar usuário não-root para segurança
RUN addgroup --system spring && adduser --system spring --ingroup spring

# Instalar dependências necessárias
RUN apt-get update && apt-get install -y \
    curl \
    && rm -rf /var/lib/apt/lists/*

# Definir diretório de trabalho
WORKDIR /app

# Copiar o JAR da aplicação
COPY --from=build /app/target/sistema-patrimonio-robusto-*.jar app.jar

# Alterar proprietário dos arquivos
RUN chown -R spring:spring /app

# Mudar para usuário não-root
USER spring:spring

# Expor a porta da aplicação
EXPOSE 8080

# Configurar health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

# Configurar JVM para container
ENV JAVA_OPTS="-Xmx512m -Xms256m -XX:+UseG1GC -XX:+UseContainerSupport"

# Comando de inicialização
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

