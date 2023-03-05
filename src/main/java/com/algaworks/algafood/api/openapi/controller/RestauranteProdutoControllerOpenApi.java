package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.ProdutoModel;
import com.algaworks.algafood.api.model.input.ProdutoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Produtos")
public interface RestauranteProdutoControllerOpenApi {

	@ApiOperation("Lista os produtos cadastrados para os restaurantes")
	@ApiResponses({
		@ApiResponse(responseCode = "400", 
				description = "ID do restaurante inválido", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", 
				description = "Restaurante não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	List<ProdutoModel> listar(
		@ApiParam(value = "ID de um restaurante", example = "1") Long restauranteId,
		@ApiParam(value = "Indica se deve ou não incluir produtos inativos "
				+ "no resultado da listagem", 
				example = "false", defaultValue = "false") boolean incluirInativos);
	
	@ApiOperation("Busca um produto de um restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "400", 
				description = "ID do produto ou restaurante inválido", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", 
				description = "Produto de restaurante não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	ProdutoModel buscarPorId(
		@ApiParam(value = "ID de um restaurante", example = "1") Long restauranteId,
		@ApiParam(value = "ID de um produto", example = "1") Long produtoId);

	@ApiOperation("Cadastra um novo produto")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Produto cadastrado"),
		@ApiResponse(responseCode = "404", 
		description = "Restaurante não encontrado",
		content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	ProdutoModel adicionar(
		@ApiParam(value = "ID de um restaurante", example = "1") Long restauranteId,
		@ApiParam(name = "Corpo", 
		value = "Representação de um novo produto") ProdutoInput produtoInput);
	
	@ApiOperation("Atualiza um produto de um restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Produto atualizado"),
		@ApiResponse(responseCode = "404", 
				description = "Produto ou restaurante não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	ProdutoModel atualizar(
			@ApiParam(value = "ID de um restaurante", example = "1") Long restauranteId,
			@ApiParam(value = "ID de um produto", example = "1") Long produtoId,
			@ApiParam(name = "Corpo", 
			value = "Representação de um produto com novos dados") ProdutoInput produtoInput);
}