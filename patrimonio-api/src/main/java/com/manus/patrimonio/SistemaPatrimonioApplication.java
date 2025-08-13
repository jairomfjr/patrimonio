package com.manus.patrimonio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SistemaPatrimonioApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaPatrimonioApplication.class, args);
    }
}

