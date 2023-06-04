package com.algaworks.algafood.api.v1.openapi.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.FotoProdutoModel;
import com.algaworks.algafood.api.v1.model.input.FotoProdutoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Produtos")
public interface RestauranteProdutoFotoControllerOpenApi {
	
	@ApiOperation(value = "Atualiza a foto do produto de um restaurante")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Foto de produto atualizada"),
		@ApiResponse(code = 404, message = "Foto de produto não encontrada",
		response = Problem.class)
	})
	FotoProdutoModel atualizarFoto(
		@ApiParam(value = "ID de um restaurante", example = "1") Long restauranteId,
		@ApiParam(value = "ID de um produto", example = "1") Long produtoId,
		FotoProdutoInput fotoProdutoInput,
		@ApiParam(value = "Arquivo da foto do produto (máximo 500KB, apenas JPG e PNG)", required = true) 
		MultipartFile arquivo) throws IOException; 

	@ApiOperation(value = "Exclui a foto do produto de um restaurante")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Foto de produto excluída"),
		@ApiResponse(code = 400, message = "ID do restaurante ou produto inválido",
		response = Problem.class),
		@ApiResponse(code = 404, message = "Foto do produto não encontrada",
		response = Problem.class)
	})
	void remover(
		@ApiParam(value = "ID de um restaurante", example = "1") Long restauranteId,
		@ApiParam(value = "ID de um produto", example = "1") Long produtoId); 
	
	@ApiOperation(value = "Busca a foto do produto de um restaurante",
			produces = "image/jpeg, image/png, application/json")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do restaurante ou produto inválido",
		response = Problem.class),
		@ApiResponse(code = 404, message = "Foto do produto não encontrada",
		response = Problem.class)
	})
	ResponseEntity<?> buscarFoto(
		@ApiParam(value = "ID de um restaurante", example = "1") Long restauranteId,
		@ApiParam(value = "ID de um produto", example = "1") Long produtoId, 
		@ApiParam(hidden = true, required = false) String acceptHeader)
		throws HttpMediaTypeNotAcceptableException;
}