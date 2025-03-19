package br.com.fiap.apiRest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API de livros", description = "Exemplo de API Restful com swagger da 2tdspw", version = "v1"))
public class ApiRestApplication {

	public static void main(String[] args) {

		SpringApplication.run(ApiRestApplication.class, args);

	}

}
