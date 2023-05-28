package com.algaworks.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.UsuarioModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteResponsavelControllerOpenApi {
	
	@ApiOperation("Lista usuários responsáveis por um restaurante")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante não encontrado",
		response = Problem.class)
	})
	CollectionModel<UsuarioModel> listar(@ApiParam(
			value = "ID de um restaurante", example = "1") Long restauranteId);
	
	@ApiOperation("Desassocia o responsável de um restaurante")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Responsável desassociado de restaurante"),
		@ApiResponse(code = 404, message = "Restaurante não encontrado",
		response = Problem.class)
	})
	ResponseEntity<Void> desassociarResponsavel(@ApiParam(
			value = "ID de um restaurante", example = "1") Long restauranteId,
							    @ApiParam(
			value = "ID de um usuário", example = "1") Long usuarioId);
	
	@ApiOperation("Associa o responsável ao restaurante")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Responsável associado ao restaurante"),
		@ApiResponse(code = 404, message = "Restaurante não encontrado",
		response = Problem.class)
	})
	ResponseEntity<Void> associarResponsavel(@ApiParam(
			value = "ID de um restaurante", example = "1") Long restauranteId,
			 	  @ApiParam(
			value = "ID de um usuário", example = "1") Long usuarioId);
}