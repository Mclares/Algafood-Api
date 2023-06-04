package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.UsuarioModel;
import com.algaworks.algafood.api.v1.model.input.SenhaInput;
import com.algaworks.algafood.api.v1.model.input.UsuarioComSenhaInput;
import com.algaworks.algafood.api.v1.model.input.UsuarioInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuarios")
public interface UsuarioControllerOpenApi {

	@ApiOperation("Lista os usuários cadastrados")
	CollectionModel<UsuarioModel> listar();
	
	@ApiOperation("Busca um usuário por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do usuário inválido",
		response = Problem.class),
		@ApiResponse(code = 404, message = "Usuário não encontrado",
		response = Problem.class)
	})
	UsuarioModel buscarPorId(
			@ApiParam(value = "ID de um usuário", example = "1")
			Long usuarioId);

	@ApiOperation("Cadastra um novo usuário")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Usuário cadastrado"),
	})
	UsuarioModel adicionar(
			@ApiParam(name = "Corpo", 
			value = "Representação de um novo usuario") UsuarioComSenhaInput usuarioComSenhaInput);
	
	@ApiOperation("Atualiza um usuário por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Usuário atualizado"),
		@ApiResponse(code = 404, message = "Usuário não encontrado",
		response = Problem.class)
	})
	UsuarioModel atualizar(
			@ApiParam(value = "ID de um usuário", 
			example = "1") Long usuarioId, 
			@ApiParam(name = "Corpo", 
			value = "Representação de um usuário com novos dados") UsuarioInput usuarioInput);

	@ApiOperation("Atualiza a senha de um usuário por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Senha atualizada"),
		@ApiResponse(code = 404, message = "Usuário não encontrado",
		response = Problem.class)
	})
	void atualizarSenha(
			@ApiParam(value = "ID de um usuário", 
			example = "1") Long usuarioId, 
			@ApiParam(name = "Corpo", 
			value = "Representação de uma nova senha atualizada") SenhaInput senhaInput);
	
	@ApiOperation("Exclui um usuário por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Usuário excluído"),
		@ApiResponse(code = 404, message = "Usuário não encontrado",
		response = Problem.class)
	})
	void remover(
		@ApiParam(value = "ID de um usuário", example = "1") Long usuarioId);
}