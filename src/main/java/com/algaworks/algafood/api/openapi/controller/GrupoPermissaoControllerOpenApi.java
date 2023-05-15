package com.algaworks.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.PermissaoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoPermissaoControllerOpenApi {
	
	@ApiOperation("Lista as permissões associadas a um grupo")
	@ApiResponses({
		@ApiResponse(responseCode = "400", 
				description = "ID do grupo inválido",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", 
				description = "Permissão não encontrada para o grupo",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	CollectionModel<PermissaoModel> listar(
		@ApiParam(value = "ID de um grupo", example = "1") Long grupoId);
	
	@ApiOperation("Desassocia uma permissão de um grupo")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Permissão desassociada de grupo"),
		@ApiResponse(responseCode = "404", description = "Grupo ou permissão não encontrada",
				content = @Content(schema = @Schema(implementation = Problem.class))),
	})
	ResponseEntity<Void> desassociarPermissao(@ApiParam(
			value = "ID de um grupo", example = "1") Long grupoId,
							  @ApiParam(
			value = "ID de uma permissão", example = "1") Long permissaoId);
	
	@ApiOperation("Associa uma permissão a um grupo")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Permissão associada ao grupo"),
		@ApiResponse(responseCode = "404", description = "Grupo ou permissão não encontrada",
				content = @Content(schema = @Schema(implementation = Problem.class))),
	})
	ResponseEntity<Void> associarPermissao(@ApiParam(
			value = "ID de um grupo", example = "1") Long grupoId,
		  				   @ApiParam(
			value = "ID de uma permissão", example = "1") Long permissaoId);
}