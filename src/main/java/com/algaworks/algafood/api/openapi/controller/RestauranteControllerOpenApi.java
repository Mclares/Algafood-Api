package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {

	@ApiOperation("Lista os restaurantes cadastrados")
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nome da projeção de restaurantes", 
				allowableValues = "apenas-nome",name = "projecao", 
				paramType = "query", dataTypeClass = String.class)
	})
	@JsonView(RestauranteView.Resumo.class)
	List<RestauranteModel> listar();
	
	@ApiOperation(value = "Lista os restaurantes cadastrados", hidden = true)
	@JsonView(RestauranteView.ApenasNome.class)
	List<RestauranteModel> listarApenasNomes();
	
	@ApiOperation("Busca um restaurante por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "400", 
				description = "ID do restaurante inválido", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", 
				description = "Restaurante não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	RestauranteModel buscarPorId(
			@ApiParam(value = "ID de um restaurante", example = "1")
			Long restauranteId);

	@ApiOperation("Cadastra um novo restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Restaurante cadastrado")
	})
	RestauranteModel adicionar(
			@ApiParam(name = "Corpo", 
			value = "Representação de um novo restaurante") RestauranteInput restauranteInput);
	
	@ApiOperation("Atualiza um restaurante por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Restaurante atualizado"),
		@ApiResponse(responseCode = "404", 
				description = "Restaurante não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	RestauranteModel atualizar(
			@ApiParam(value = "ID de um restaurante", 
			example = "1") Long restauranteId, 
			@ApiParam(name = "Corpo", 
			value = "Representação de um restaurante com novos dados") RestauranteInput restauranteInput);
	
	@ApiOperation("Ativa um restaurante por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurante ativado"),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado",
			content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	void ativar(
			@ApiParam(value = "ID de um restaurante", 
			example = "1") Long restauranteId);
	
	@ApiOperation("Inativa um restaurante por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurante inativado"),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado",
			content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	void inativar(
			@ApiParam(value = "ID de um restaurante", 
			example = "1") Long restauranteId);

	@ApiOperation("Ativa restaurantes por uma lista de IDs")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurantes ativados"),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado",
			content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	void ativacaoMultiplos(
			@ApiParam(name = "Corpo", 
				value = "IDs dos restaurantes a serem ativados") 
			List<Long> restauranteIds);
	
	@ApiOperation("Inativa restaurantes por uma lista de IDs")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurantes inativados"),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado",
			content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	void inativacaoMultiplos(
			@ApiParam(name = "Corpo", 
					value = "IDs dos restaurantes a serem inativados") 
			List<Long> restauranteIds);

	@ApiOperation("Abre um restaurante por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurante aberto"),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado",
			content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	void abrir(
			@ApiParam(value = "ID de um restaurante", 
			example = "1") Long restauranteId);
	
	@ApiOperation("Fecha um restaurante por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurante fechado"),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado",
			content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	void fechar(
			@ApiParam(value = "ID de um restaurante", 
			example = "1") Long restauranteId);
}