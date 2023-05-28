package com.algaworks.algafood.core.openapi;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.model.PermissaoModel;
import com.algaworks.algafood.api.model.ProdutoModel;
import com.algaworks.algafood.api.model.RestauranteBasicoModel;
import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.api.openapi.model.CidadesModelOpenApi;
import com.algaworks.algafood.api.openapi.model.CozinhasModelOpenApi;
import com.algaworks.algafood.api.openapi.model.EstadosModelOpenApi;
import com.algaworks.algafood.api.openapi.model.FormasPagamentoModelOpenApi;
import com.algaworks.algafood.api.openapi.model.GruposModelOpenApi;
import com.algaworks.algafood.api.openapi.model.LinksModelOpenApi;
import com.algaworks.algafood.api.openapi.model.PageableModelOpenApi;
import com.algaworks.algafood.api.openapi.model.PedidosResumoModelOpenApi;
import com.algaworks.algafood.api.openapi.model.PermissoesModelOpenApi;
import com.algaworks.algafood.api.openapi.model.RestauranteProdutosModelOpenApi;
import com.algaworks.algafood.api.openapi.model.RestauranteResponsaveisModelOpenApi;
import com.algaworks.algafood.api.openapi.model.RestaurantesBasicoModelOpenApi;
import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RepresentationBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {
	
	@Bean
	public JacksonModuleRegistrar springFoxJacksonConfig() {
		return objectMapper -> objectMapper.registerModule(new JavaTimeModule());
	}
	
	@Bean
	public Docket apiDocket() {
		var typeResolver = new TypeResolver();
		
		return new Docket(DocumentationType.OAS_30)
				.select()
					.apis(RequestHandlerSelectors.basePackage(
							"com.algaworks.algafood.api"))
					.paths(PathSelectors.any())
					.build()
				.useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET, globalGetResponses())
				.globalResponses(HttpMethod.POST, globalPostResponses())
				.globalResponses(HttpMethod.PUT, globalPutResponses())
				.globalResponses(HttpMethod.DELETE, globalDeleteResponses())
				.additionalModels(typeResolver.resolve(Problem.class))
				.ignoredParameterTypes(ServletWebRequest.class, 
						URL.class, URI.class, URLStreamHandler.class,
						Resource.class, File.class, InputStream.class)	
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.directModelSubstitute(Links.class, LinksModelOpenApi.class)
				.alternateTypeRules(AlternateTypeRules
						.newRule(typeResolver
								.resolve(PagedModel.class, CozinhaModel.class), 
								CozinhasModelOpenApi.class))
//				.alternateTypeRules(AlternateTypeRules
//						.newRule(typeResolver
//								.resolve(Page.class, PedidoModel.class), 
//								PedidosModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules
						.newRule(typeResolver
								.resolve(CollectionModel.class, CidadeModel.class), 
								CidadesModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules
						.newRule(typeResolver
								.resolve(CollectionModel.class, EstadoModel.class), 
								EstadosModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules
						.newRule(typeResolver
								.resolve(CollectionModel.class, FormaPagamentoModel.class), 
								FormasPagamentoModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules
						.newRule(typeResolver
								.resolve(CollectionModel.class, GrupoModel.class), 
								GruposModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules
						.newRule(typeResolver
								.resolve(CollectionModel.class, PermissaoModel.class), 
								PermissoesModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules
						.newRule(typeResolver
								.resolve(CollectionModel.class, ProdutoModel.class), 
								RestauranteProdutosModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules
						.newRule(typeResolver
								.resolve(CollectionModel.class, RestauranteBasicoModel.class), 
								RestaurantesBasicoModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules
						.newRule(typeResolver
								.resolve(CollectionModel.class, UsuarioModel.class), 
								RestauranteResponsaveisModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules
						.newRule(typeResolver
								.resolve(PagedModel.class, PedidoResumoModel.class), 
								PedidosResumoModelOpenApi.class))
				.apiInfo(apiInfo())
				.tags(
						new Tag("Cidades", "Gerencia as cidades"),
						new Tag("Grupos", "Gerencia os grupos de usuários"),
						new Tag("Cozinhas", "Gerencia as cozinhas"),
						new Tag("FormasPagamento", "Gerencia as formas de pagamento"),
						new Tag("Pedidos", "Gerencia os pedidos"),
						new Tag("Restaurantes", "Gerencia os restaurantes"),
						new Tag("Estados", "Gerencia os estados"),
						new Tag("Produtos", "Gerencia os produtos de restaurantes"),
						new Tag("Usuarios", "Gerencia os usuários"),
						new Tag("Estatísticas", "Gerencia os indicadores"),
						new Tag("Permissões", "Gerencia as permissões"));
	} 
	
	private List<Response> globalGetResponses() {
		return Arrays.asList(
				new ResponseBuilder()
					.code("500")
					.description("Erro interno do servidor")
					.representation(MediaType.APPLICATION_JSON)
					.apply(getProblemaModelReference())
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
					.representation(MediaType.APPLICATION_JSON)
					.apply(getProblemaModelReference())
					.build(),
				new ResponseBuilder()
					.code("406")
					.description("Recurso não possui representação que poderia ser aceita pelo consumidor")
					.build(),
				new ResponseBuilder()
					.code("500")
					.description("Erro interno do servidor")
					.representation(MediaType.APPLICATION_JSON)
					.apply(getProblemaModelReference())
					.build(),
				new ResponseBuilder()
					.code("415")
					.description("Requisição recusada porque o corpo está em um formato não suportado")
					.representation(MediaType.APPLICATION_JSON)
					.apply(getProblemaModelReference())
					.build()
		); 
	}

	private List<Response> globalPutResponses() {
		return Arrays.asList(
				new ResponseBuilder()
					.code("400")
					.description("Requisição inválida (erro do cliente)")
					.representation(MediaType.APPLICATION_JSON)
					.apply(getProblemaModelReference())
					.build(),
				new ResponseBuilder()
					.code("406")
					.description("Recurso não possui representação que poderia ser aceita pelo consumidor")
					.build(),
				new ResponseBuilder()
					.code("500")
					.description("Erro interno do servidor")
					.representation(MediaType.APPLICATION_JSON)
					.apply(getProblemaModelReference())
					.build(),
				new ResponseBuilder()
					.code("415")
					.description("Requisição recusada porque o corpo está em um formato não suportado")
					.representation(MediaType.APPLICATION_JSON)
					.apply(getProblemaModelReference())
					.build()
		); 
	}
	
	private List<Response> globalDeleteResponses() {
		return Arrays.asList(
				new ResponseBuilder()
					.code("400")
					.description("Requisição inválida (erro do cliente)")
					.representation(MediaType.APPLICATION_JSON)
					.apply(getProblemaModelReference())
					.build(),
				new ResponseBuilder()
					.code("500")
					.description("Erro interno do servidor")
					.representation(MediaType.APPLICATION_JSON)
					.apply(getProblemaModelReference())
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
	
	private Consumer<RepresentationBuilder> getProblemaModelReference() {
		return r -> r.model(m -> m.name("Problema")
				.referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
						q -> q.name("Problema")
						.namespace("com.algaworks.algafood.api.exceptionhandler")))));
	}
}
