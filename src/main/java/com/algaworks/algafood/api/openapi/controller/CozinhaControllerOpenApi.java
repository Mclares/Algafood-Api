package com.algaworks.algafood.api.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.input.CozinhaInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {

	@ApiOperation("Lista as cozinhas cadastradas")
	PagedModel<CozinhaModel> listar(Pageable pageable);
	
	@ApiOperation("Busca uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da cozinha inválido", 
				response = Problem.class),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", 
		response = Problem.class)
	})
	CozinhaModel buscarPorId(
			@ApiParam(value = "ID de uma cozinha", example = "1")
			Long cozinhaId);

	@ApiOperation("Cadastra uma nova cozinha")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cozinha cadastrada")
	})
	CozinhaModel adicionar(
			@ApiParam(name = "Corpo", 
			value = "Representação de uma nova cozinha") CozinhaInput cozinhaInput);
	
	@ApiOperation("Atualiza uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cozinha atualizada"),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", 
		response = Problem.class)
	})
	CozinhaModel atualizar(
			@ApiParam(value = "ID de uma cozinha", 
			example = "1") Long cozinhaId, 
			@ApiParam(name = "Corpo", 
			value = "Representação de uma cozinha com novos dados") CozinhaInput cozinhaInput);
	
	@ApiOperation("Exclui uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cozinha excluída"),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", 
		response = Problem.class)
	})
	void remover(
			@ApiParam(value = "ID de uma cozinha", example = "1") Long cozinhaId);
}