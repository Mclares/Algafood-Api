package com.algaworks.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.ProdutoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("RestauranteProdutosModel")
@Data
public class RestauranteProdutosModelOpenApi {

	private RestauranteProdutosEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("RestauranteProdutosEmbeddedModel")
	@Data
	public class RestauranteProdutosEmbeddedModelOpenApi {
		
		private List<ProdutoModel> produtos;
	}
}
