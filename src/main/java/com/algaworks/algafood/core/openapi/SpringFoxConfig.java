package com.algaworks.algafood.core.openapi;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.OAS_30)
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
					.paths(PathSelectors.any())
//					.paths(PathSelectors.ant("/restaurantes/*"))
					.build()
				.useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET, globalGetResponses())
				.globalResponses(HttpMethod.POST, globalPostResponses())
				.globalResponses(HttpMethod.PUT, globalPutResponses())
				.globalResponses(HttpMethod.DELETE, globalDeleteResponses())
				.apiInfo(apiInfo())
				.tags(new Tag("Cidades", "Gerencia as cidades"));
	} 
	
	private List<Response> globalGetResponses() {
		return Arrays.asList(
				new ResponseBuilder()
					.code("500")
					.description("Erro interno do servidor")
					.build(),
				new ResponseBuilder()
					.code("406")
					.description("Recurso não possui representação que poderia ser aceita pelo consumidor")
					.build()
		); 
	}
	
	private List<Response> globalPostResponses() {
		return Arrays.asList(
				new ResponseBuilder()
					.code("400")
					.description("Requisição inválida (erro do cliente)")
					.build(),
				new ResponseBuilder()
					.code("406")
					.description("Recurso não possui representação que poderia ser aceita pelo consumidor")
					.build(),
				new ResponseBuilder()
					.code("500")
					.description("Erro interno do servidor")
					.build(),
				new ResponseBuilder()
					.code("415")
					.description("Requisição recusada porque o corpo está em um formato não suportado")
					.build()
		); 
	}

	private List<Response> globalPutResponses() {
		return Arrays.asList(
				new ResponseBuilder()
					.code("400")
					.description("Requisição inválida (erro do cliente)")
					.build(),
				new ResponseBuilder()
					.code("406")
					.description("Recurso não possui representação que poderia ser aceita pelo consumidor")
					.build(),
				new ResponseBuilder()
					.code("500")
					.description("Erro interno do servidor")
					.build(),
				new ResponseBuilder()
					.code("415")
					.description("Requisição recusada porque o corpo está em um formato não suportado")
					.build()
		); 
	}
	
	private List<Response> globalDeleteResponses() {
		return Arrays.asList(
				new ResponseBuilder()
					.code("400")
					.description("Requisição inválida (erro do cliente)")
					.build(),
				new ResponseBuilder()
					.code("500")
					.description("Erro interno do servidor")
					.build()
		); 
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Algafood Api")
				.description("Api aberta para clientes e restaurantes")
				.version("1.0")
				.contact(new Contact("Algafood", 
						"https://www.algafood.com", "contato@algafood.com"))
				.build();
	}
}
