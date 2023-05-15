package com.algaworks.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.UsuarioModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteResponsavelControllerOpenApi {
	
	@ApiOperation("Lista usuários responsáveis por um restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "404", 
				description = "Restaurante não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	CollectionModel<UsuarioModel> listar(@ApiParam(
			value = "ID de um restaurante", example = "1") Long restauranteId);
	
	@ApiOperation("Desassocia o responsável de um restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Responsável desassociado de restaurante"),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class))),
	})
	ResponseEntity<Void> desassociarResponsavel(@ApiParam(
			value = "ID de um restaurante", example = "1") Long restauranteId,
							    @ApiParam(
			value = "ID de um usuário", example = "1") Long usuarioId);
	
	@ApiOperation("Associa o responsável ao restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Responsável associado ao restaurante"),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado",
		content = @Content(schema = @Schema(implementation = Problem.class))),
	})
	ResponseEntity<Void> associarResponsavel(@ApiParam(
			value = "ID de um restaurante", example = "1") Long restauranteId,
			 	  @ApiParam(
			value = "ID de um usuário", example = "1") Long usuarioId);
}