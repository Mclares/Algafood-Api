package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.api.model.input.SenhaInput;
import com.algaworks.algafood.api.model.input.UsuarioComSenhaInput;
import com.algaworks.algafood.api.model.input.UsuarioInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Usuarios")
public interface UsuarioControllerOpenApi {

	@ApiOperation("Lista os usuários cadastrados")
	List<UsuarioModel> listar();
	
	@ApiOperation("Busca um usuário por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "400", 
				description = "ID do usuário inválido", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", 
				description = "Usuário não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	UsuarioModel buscarPorId(
			@ApiParam(value = "ID de um usuário", example = "1")
			Long usuarioId);

	@ApiOperation("Cadastra um novo usuário")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Usuário cadastrado")
	})
	UsuarioModel adicionar(
			@ApiParam(name = "Corpo", 
			value = "Representação de um novo usuario") UsuarioComSenhaInput usuarioComSenhaInput);
	
	@ApiOperation("Atualiza um usuário por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Usuário atualizado"),
		@ApiResponse(responseCode = "404", 
				description = "Usuário não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	UsuarioModel atualizar(
			@ApiParam(value = "ID de um usuário", 
			example = "1") Long usuarioId, 
			@ApiParam(name = "Corpo", 
			value = "Representação de um usuário com novos dados") UsuarioInput usuarioInput);

	@ApiOperation("Atualiza a senha de um usuário por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Senha atualizada"),
		@ApiResponse(responseCode = "404", 
				description = "Usuário não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	void atualizarSenha(
			@ApiParam(value = "ID de um usuário", 
			example = "1") Long usuarioId, 
			@ApiParam(name = "Corpo", 
			value = "Representação de uma nova senha atualizada") SenhaInput senhaInput);
	
	@ApiOperation("Exclui um usuário por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Usuário excluído"),
		@ApiResponse(responseCode = "404", 
				description = "Usuário não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	void remover(
		@ApiParam(value = "ID de um usuário", example = "1") Long usuarioId);
}
