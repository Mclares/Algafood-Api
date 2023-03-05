package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.GrupoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Usuarios")
public interface UsuarioGrupoControllerOpenApi {
	
	@ApiOperation("Lista os usuários associados a um grupo")
	@ApiResponses({
		@ApiResponse(responseCode = "400", 
				description = "ID do usuário inválido",
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", 
				description = "Usuário não encontrado para o grupo",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	List<GrupoModel> listar(
			@ApiParam(value = "ID de um usuário", example = "1") Long usuarioId);
	
	@ApiOperation("Desassocia um usuário de um grupo")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Usuário desassociado de grupo"),
		@ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class))),
	})
	void desassociarGrupo(@ApiParam(
			value = "ID de um usuário", example = "1") Long usuarioId,
							  @ApiParam(
			value = "ID de um grupo", example = "1") Long grupoId);
	
	@ApiOperation("Associa um usuário a um grupo")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Usuário associado ao grupo"),
		@ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class))),
	})
	void associarGrupo(@ApiParam(
			value = "ID de um usuário", example = "1") Long usuarioId,
							  @ApiParam(
			value = "ID de um grupo", example = "1") Long grupoId);
}