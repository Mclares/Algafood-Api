package com.algaworks.algafood.api.openapi.controller;

import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface FluxoPedidoControllerOpenApi {
	
	@ApiOperation("Confirma um pedido por código")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Pedido confirmado", 
				response = Problem.class),
		@ApiResponse(code = 404, message = "Pedido não encontrado", 
		response = Problem.class)
	})
	ResponseEntity<Void> confirmar(@ApiParam(value = "Código de um pedido", 
			example = "291daf5f-81aa-11ed-8327-025041000001") String codigo);
	
	@ApiOperation("Marca um pedido como entregue por código")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Pedido entregue", 
				response = Problem.class),
		@ApiResponse(code = 404, message = "Pedido não encontrado", 
		response = Problem.class)
	})
	ResponseEntity<Void> entregar(@ApiParam(value = "Código de um pedido", 
			example = "291daf5f-81aa-11ed-8327-025041000001") String codigo);

	@ApiOperation("Marca um pedido como cancelado por código")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Pedido cancelado", 
				response = Problem.class),
		@ApiResponse(code = 404, message = "Pedido não encontrado", 
		response = Problem.class)
	})
	ResponseEntity<Void> cancelar(@ApiParam(value = "Código de um pedido", 
			example = "291daf5f-81aa-11ed-8327-025041000001") String codigo);
}