package com.algaworks.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.FormaPagamentoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {
	
	@ApiOperation("Lista as formas de pagamento de um restaurante")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante não encontrado",
		response = Problem.class)
	})
	CollectionModel<FormaPagamentoModel> listar(@ApiParam(
			value = "ID de um restaurante", example = "1") Long restauranteId);
	
	@ApiOperation("Desassocia uma forma de pagamento de restaurante")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Forma de pagamento desassociada de Restaurante"),
		@ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrada",
		response = Problem.class)
	})
	ResponseEntity<Void> desassociar(@ApiParam(
			value = "ID de um restaurante", example = "1") Long restauranteId,
					@ApiParam(
			value = "ID de um forma de pagamento", example = "1") Long formaPagamentoId);
	
	@ApiOperation("Associa uma forma de pagamento ao restaurante")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Forma de pagamento associada a Restaurante"),
		@ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrada",
		response = Problem.class)
	})
	ResponseEntity<Void> associar(@ApiParam(
			value = "ID de um restaurante", example = "1") Long restauranteId,
			 	  @ApiParam(
			value = "ID de um forma de pagamento", example = "1") Long formaPagamentoId);
}