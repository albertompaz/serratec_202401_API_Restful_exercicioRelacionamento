package org.serratec.backend.h2banco.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

	@Value("${dominio.openapi.dev-url}")
	private String devUrl;
	
	@Value("${dominio.openapi.prod-url}")
	private String prodUrl;
	
	@Bean
	OpenAPI myOpenApi() {
		Server devServer = new Server();
		devServer.setUrl(devUrl);
		devServer.setDescription("URL do servidor de desenvolvimento");
		Server prodServer = new Server();
		prodServer.setUrl(prodUrl);
		prodServer.setDescription("URL do servidor de produção");
		
		Contact contact = new Contact();
		contact.setEmail("contato@meudominio.com.br");
		contact.setName("Fulano");
		contact.setUrl("https://www.meudominio.com.br");
		
		License apacheLicense = new License().name("Apache License")
				.url("https://www.apache.org/license/LICENSE-2.0");
		
		Info info = new Info().title("API de Teste").version("1.0").contact(contact)
				.description("API para testes diversos").termsOfService("https://www.meudominio.com.br/termos")
				.license(apacheLicense);
		
		return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
	}
	
}
