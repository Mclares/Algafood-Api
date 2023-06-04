package com.algaworks.algafood.api.v2.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v2.model.CidadeModelV2;
import com.algaworks.algafood.api.v2.model.input.CidadeInputV2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApiV2 {

	@ApiOperation("Lista as cidades cadastradas")
	CollectionModel<CidadeModelV2> listar();
	
	@ApiOperation("Busca uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da cidade inválido", 
				response = Problem.class),
		@ApiResponse(code = 404, message = "Cidade não encontrada", 
		response = Problem.class)
	})
	CidadeModelV2 buscarPorId(
			@ApiParam(value = "ID de uma cidade", example = "1")
			Long cidadeId);

	@ApiOperation("Cadastra uma nova cidade")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cidade cadastrada")
	})
	CidadeModelV2 adicionar(
			@ApiParam(name = "Corpo", 
			value = "Representação de uma nova cidade") CidadeInputV2 cidadeInput);
	
	@ApiOperation("Atualiza uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cidade atualizada"),
		@ApiResponse(code = 404, message = "Cidade não encontrada", 
		response = Problem.class)
	})
	CidadeModelV2 atualizar(
			@ApiParam(value = "ID de uma cidade", 
			example = "1") Long cidadeId, 
			@ApiParam(name = "Corpo", 
			value = "Representação de uma cidade com novos dados") CidadeInputV2 cidadeInput);
	
	@ApiOperation("Exclui uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cidade excluída"),
		@ApiResponse(code = 404, message = "Cidade não encontrada", 
		response = Problem.class)
	})
	void remover(
			@ApiParam(value = "ID de uma cidade", example = "1") Long cidadeId);
}