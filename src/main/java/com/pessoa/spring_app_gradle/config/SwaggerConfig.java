package com.pessoa.spring_app_gradle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	/**
	 *   Objetivo: Comunicar à api de forma externa para ser consumida pelo swagger.
	 *   entrada : Nenhum.
	 *   Saída   : Docker retorna essa comunicação.
	 **/
	@Bean
	public Docket funcionarioApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.pessoa.spring_app_gradle"))
				.paths(regex("/api.*"))
				.build()
				.apiInfo(metaInfo());
	}
	
	/**
	 *   Objetivo: Informar os dados de quem construiu essa api.
	 *   entrada : Nenhum.
	 *   Saída   : retorna essa informações.
	 **/
	private ApiInfo metaInfo() {
		@SuppressWarnings("rawtypes")
		ApiInfo apiInfo = new ApiInfo(
				"Spring Boot Swagger with Postgresql - Cadastro de Funcionarios",
				"Dados dos Funcionários",
				null,
				null,
				new Contact("Darley Cardoso", "https://www.linkedin.com/in/darley-garcia-cardoso-35292a148/",
						"garciacardoso.darley@gmail.com"),
						null,
						null,
						new ArrayList<VendorExtension>());
		return apiInfo ;
	}
	
}
