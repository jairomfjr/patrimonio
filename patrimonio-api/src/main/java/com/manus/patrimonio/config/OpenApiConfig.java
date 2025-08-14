package com.manus.patrimonio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Gestão de Bens Patrimoniais")
                        .description("API REST para gerenciamento completo de bens patrimoniais, " +
                                   "incluindo categorias, localizações, movimentações e inventários.")
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
                                .url("http://localhost:8080/api/v1")
                                .description("Servidor de Desenvolvimento"),
                        new Server()
                                .url("https://patrimonio.manus.ai/api/v1")
                                .description("Servidor de Produção")
                ));
    }
}

