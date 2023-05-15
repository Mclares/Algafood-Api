package com.algaworks.algafood.api.openapi.controller;

import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Pedidos")
public interface FluxoPedidoControllerOpenApi {
	
	@ApiOperation("Confirma um pedido por código")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Pedido confirmado"),
		@ApiResponse(responseCode = "404", description = "Pedido não encontrado",
			content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	ResponseEntity<Void> confirmar(@ApiParam(value = "Código de um pedido", 
			example = "291daf5f-81aa-11ed-8327-025041000001") String codigo);
	
	@ApiOperation("Marca um pedido como entregue por código")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Pedido entregue"),
		@ApiResponse(responseCode = "404", description = "Pedido não encontrado",
			content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	ResponseEntity<Void> entregar(@ApiParam(value = "Código de um pedido", 
			example = "291daf5f-81aa-11ed-8327-025041000001") String codigo);

	@ApiOperation("Marca um pedido como cancelado por código")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Pedido cancelado"),
		@ApiResponse(responseCode = "404", description = "Pedido não encontrado",
			content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	ResponseEntity<Void> cancelar(@ApiParam(value = "Código de um pedido", 
			example = "291daf5f-81aa-11ed-8327-025041000001") String codigo);
}
