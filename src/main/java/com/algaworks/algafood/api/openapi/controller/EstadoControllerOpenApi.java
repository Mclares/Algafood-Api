package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.api.model.input.EstadoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {

	@ApiOperation("Lista os estados cadastrados")
	List<EstadoModel> listar();
	
	@ApiOperation("Busca um estado por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "400", 
				description = "ID do estado inválido", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", 
				description = "Estado não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	EstadoModel buscarPorId(
			@ApiParam(value = "ID de um estado", example = "1")
			Long estadoId);

	@ApiOperation("Cadastra um novo estado")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Estado cadastrado")
	})
	EstadoModel adicionar(
			@ApiParam(name = "Corpo", 
			value = "Representação de um novo estado") EstadoInput estadoInput);
	
	@ApiOperation("Atualiza um estado por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Estado atualizado"),
		@ApiResponse(responseCode = "404", 
				description = "Estado não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	EstadoModel atualizar(
			@ApiParam(value = "ID de um estado", 
			example = "1") Long estadoId, 
			@ApiParam(name = "Corpo", 
			value = "Representação de um estado com novos dados") EstadoInput estadoInput);
	
	@ApiOperation("Exclui um estado por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Estado excluído"),
		@ApiResponse(responseCode = "404", 
				description = "Estado não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	void remover(
			@ApiParam(value = "ID de um estado", example = "1") Long estadoId);
}
