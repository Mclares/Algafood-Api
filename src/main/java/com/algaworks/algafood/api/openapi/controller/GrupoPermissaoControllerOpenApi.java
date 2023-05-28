package com.algaworks.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.PermissaoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoPermissaoControllerOpenApi {
	
	@ApiOperation("Lista as permissões associadas a um grupo")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do grupo inválido",
		response = Problem.class),
		@ApiResponse(code = 404, message = "Permissão não encontrada para o grupo", 
		response = Problem.class)
	})
	CollectionModel<PermissaoModel> listar(
		@ApiParam(value = "ID de um grupo", example = "1") Long grupoId);
	
	@ApiOperation("Desassocia uma permissão de um grupo")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Permissão desassociada de grupo"),
		@ApiResponse(code = 404, message = "Grupo ou Permissão não encontrada para o grupo", 
		response = Problem.class)
	})
	ResponseEntity<Void> desassociarPermissao(@ApiParam(
			value = "ID de um grupo", example = "1") Long grupoId,
							  @ApiParam(
			value = "ID de uma permissão", example = "1") Long permissaoId);
	
	@ApiOperation("Associa uma permissão a um grupo")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Permissão associada de grupo"),
		@ApiResponse(code = 404, message = "Grupo ou Permissão não encontrada para o grupo", 
		response = Problem.class)
	})
	ResponseEntity<Void> associarPermissao(@ApiParam(
			value = "ID de um grupo", example = "1") Long grupoId,
		  				   @ApiParam(
			value = "ID de uma permissão", example = "1") Long permissaoId);
}