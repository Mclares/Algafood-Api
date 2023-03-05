package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.FormaPagamentoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {
	
	@ApiOperation("Lista as formas de pagamento de um restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "404", 
				description = "Restaurante não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	List<FormaPagamentoModel> listar(@ApiParam(
			value = "ID de um restaurante", example = "1") Long restauranteId);
	
	@ApiOperation("Desassocia uma forma de pagamento de restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Forma de pagamento desassociada de restaurante"),
		@ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrada",
				content = @Content(schema = @Schema(implementation = Problem.class))),
	})
	void desassociar(@ApiParam(
			value = "ID de um restaurante", example = "1") Long restauranteId,
					@ApiParam(
			value = "ID de um forma de pagamento", example = "1") Long formaPagamentoId);
	
	@ApiOperation("Associa uma forma de pagamento ao restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Forma de pagamento associada ao restaurante"),
		@ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrada",
		content = @Content(schema = @Schema(implementation = Problem.class))),
	})
	void associar(@ApiParam(
			value = "ID de um restaurante", example = "1") Long restauranteId,
			 	  @ApiParam(
			value = "ID de um forma de pagamento", example = "1") Long formaPagamentoId);
}