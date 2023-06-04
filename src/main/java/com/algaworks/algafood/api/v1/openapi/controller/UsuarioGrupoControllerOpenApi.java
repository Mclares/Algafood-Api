package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.GrupoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuarios")
public interface UsuarioGrupoControllerOpenApi {
	
	@ApiOperation("Lista os usuários associados a um grupo")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do usuário inválido",
		response = Problem.class),
		@ApiResponse(code = 404, message = "Usuário não encontrado para o grupo",
		response = Problem.class)
	})
	CollectionModel<GrupoModel> listar(
			@ApiParam(value = "ID de um usuário", example = "1") Long usuarioId);
	
	@ApiOperation("Desassocia um usuário de um grupo")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Usuário desassociado de grupo"),
		@ApiResponse(code = 404, message = "Usuário ou grupo não encontrado",
		response = Problem.class)
	})
	ResponseEntity<Void> desassociarGrupo(@ApiParam(
			value = "ID de um usuário", example = "1") Long usuarioId,
							  @ApiParam(
			value = "ID de um grupo", example = "1") Long grupoId);
	
	@ApiOperation("Associa um usuário a um grupo")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Usuário associado de grupo"),
		@ApiResponse(code = 404, message = "Usuário ou grupo não encontrado",
		response = Problem.class)
	})
	ResponseEntity<Void> associarGrupo(@ApiParam(
			value = "ID de um usuário", example = "1") Long usuarioId,
							  @ApiParam(
			value = "ID de um grupo", example = "1") Long grupoId);
}