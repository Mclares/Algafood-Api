package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.ProdutoModel;
import com.algaworks.algafood.api.v1.model.input.ProdutoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Produtos")
public interface RestauranteProdutoControllerOpenApi {

	@ApiOperation("Lista os produtos cadastrados para os restaurantes")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do restaurante inválido",
		response = Problem.class),
		@ApiResponse(code = 404, message = "Restaurante não encontrado",
		response = Problem.class)
	})
	CollectionModel<ProdutoModel> listar(
		@ApiParam(value = "ID de um restaurante", example = "1") Long restauranteId,
		@ApiParam(value = "Indica se deve ou não incluir produtos inativos "
				+ "no resultado da listagem", 
				example = "false", defaultValue = "false") Boolean incluirInativos);
	
	@ApiOperation("Busca um produto de um restaurante")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do produto ou restaurante inválido",
		response = Problem.class),
		@ApiResponse(code = 404, message = "Produto de restaurante não encontrado",
		response = Problem.class)
	})
	ProdutoModel buscarPorId(
		@ApiParam(value = "ID de um restaurante", example = "1") Long restauranteId,
		@ApiParam(value = "ID de um produto", example = "1") Long produtoId);

	@ApiOperation("Cadastra um novo produto")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Produto cadastrado"),
		@ApiResponse(code = 404, message = "Produto ou restaurante não encontrado",
		response = Problem.class)
	})
	ProdutoModel adicionar(
		@ApiParam(value = "ID de um restaurante", example = "1") Long restauranteId,
		@ApiParam(name = "Corpo", 
		value = "Representação de um novo produto") ProdutoInput produtoInput);
	
	@ApiOperation("Atualiza um produto de um restaurante")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Produto atualizado"),
		@ApiResponse(code = 404, message = "Produto ou restaurante não encontrado",
		response = Problem.class)
	})
	ProdutoModel atualizar(
			@ApiParam(value = "ID de um restaurante", example = "1") Long restauranteId,
			@ApiParam(value = "ID de um produto", example = "1") Long produtoId,
			@ApiParam(name = "Corpo", 
			value = "Representação de um produto com novos dados") ProdutoInput produtoInput);
}