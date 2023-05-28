package com.algaworks.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.RestauranteApenasNomeModel;
import com.algaworks.algafood.api.model.RestauranteBasicoModel;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {

	@ApiOperation("Lista os restaurantes cadastrados")
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nome da projeção de restaurantes", 
				allowableValues = "apenas-nome",name = "projecao", 
				paramType = "query", dataTypeClass = String.class)
	})
	CollectionModel<RestauranteBasicoModel> listar();

	@ApiIgnore
	@ApiOperation(value = "Lista os restaurantes cadastrados", hidden = true)
	CollectionModel<RestauranteApenasNomeModel> listarApenasNomes();
	
	@ApiOperation("Busca um restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do restaurante inválido", 
		response = Problem.class),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", 
		response = Problem.class)
	})
	RestauranteModel buscarPorId(
			@ApiParam(value = "ID de um restaurante", example = "1")
			Long restauranteId);

	@ApiOperation("Cadastra um novo restaurante")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Restaurante cadastrado")
	})
	RestauranteModel adicionar(
			@ApiParam(name = "Corpo", 
			value = "Representação de um novo restaurante") RestauranteInput restauranteInput);
	
	@ApiOperation("Atualiza um restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Restaurante atualizado"),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", 
		response = Problem.class)
	})
	RestauranteModel atualizar(
			@ApiParam(value = "ID de um restaurante", 
			example = "1") Long restauranteId, 
			@ApiParam(name = "Corpo", 
			value = "Representação de um restaurante com novos dados") RestauranteInput restauranteInput);
	
	@ApiOperation("Ativa um restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Restaurante ativado"),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", 
		response = Problem.class)
	})
	ResponseEntity<Void> ativar(
			@ApiParam(value = "ID de um restaurante", 
			example = "1") Long restauranteId);
	
	@ApiOperation("Inativa um restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Restaurante inativado"),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", 
		response = Problem.class)
	})
	ResponseEntity<Void> inativar(
			@ApiParam(value = "ID de um restaurante", 
			example = "1") Long restauranteId);

	@ApiOperation("Ativa restaurantes por uma lista de IDs")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Restaurantes ativados"),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", 
		response = Problem.class)
	})
	void ativacaoMultiplos(
			@ApiParam(name = "Corpo", 
				value = "IDs dos restaurantes a serem ativados") 
			List<Long> restauranteIds);
	
	@ApiOperation("Inativa restaurantes por uma lista de IDs")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Restaurantes inativados"),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", 
		response = Problem.class)
	})
	void inativacaoMultiplos(
			@ApiParam(name = "Corpo", 
					value = "IDs dos restaurantes a serem inativados") 
			List<Long> restauranteIds);

	@ApiOperation("Abre um restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Restaurante aberto"),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", 
		response = Problem.class)
	})
	ResponseEntity<Void> abrir(
			@ApiParam(value = "ID de um restaurante", 
			example = "1") Long restauranteId);
	
	@ApiOperation("Fecha um restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Restaurante fechado"),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", 
		response = Problem.class)
	})
	ResponseEntity<Void> fechar(
			@ApiParam(value = "ID de um restaurante", 
			example = "1") Long restauranteId);
}