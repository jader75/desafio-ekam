package br.com.jader.desafio.ekam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API de Beneficiários", version = "1.0",description = "Documentação da API de Beneficiários utilizando Springdoc OpenAPI"))
public class DesafioEkamApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioEkamApplication.class, args);
    }
}
