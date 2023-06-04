package com.algaworks.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.UsuarioModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("RestauranteResponsaveisModel")
@Data
public class RestauranteResponsaveisModelOpenApi {

	private RestauranteResponsaveisEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("RestauranteResponsaveisEmbeddedModelOpenApi")
	@Data
	public class RestauranteResponsaveisEmbeddedModelOpenApi {
		
		private List<UsuarioModel> usuarios;
	}
}
