package com.algaworks.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.api.model.input.FormaPagamentoInput;
import com.algaworks.algafood.api.openapi.model.FormasPagamentoModelOpenApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "FormasPagamento")
public interface FormaPagamentoControllerOpenApi {

	@ApiOperation("Lista as formas de pagamento cadastradas")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK", 
				response = FormasPagamentoModelOpenApi.class)
	})
	ResponseEntity<CollectionModel<FormaPagamentoModel>> listar(
			ServletWebRequest request);
	
	@ApiOperation("Busca uma forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da forma de pagamento inválida", 
				response = Problem.class),
		@ApiResponse(code = 404, message = "Forma de pagamento não encontrada", 
		response = Problem.class)
	})
	ResponseEntity<FormaPagamentoModel> buscarporId (
			@ApiParam(value = "ID de uma forma de pagamento", example = "1")
			Long formaPagamentoId, ServletWebRequest request);

	@ApiOperation("Cadastra uma nova forma de pagamento")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Forma de pagamento cadastrada")
	})
	FormaPagamentoModel adicionar(
			@ApiParam(name = "Corpo", 
			value = "Representação de uma nova forma de pagamento") FormaPagamentoInput formaPagamentoInput);
	
	@ApiOperation("Atualiza uma forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Forma de pagamento atualizada"),
		@ApiResponse(code = 404, message = "Forma de pagamento não encontrada",
				response = Problem.class)
	})
	FormaPagamentoModel atualizar(
			@ApiParam(value = "ID de uma forma de pagamento", 
			example = "1") Long formaPagamentoId, 
			@ApiParam(name = "Corpo", 
			value = "Representação de uma forma de pagamento com novos dados") FormaPagamentoInput formaPagamentoInput);
	
	@ApiOperation("Exclui uma forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Forma de pagamento excluída"),
		@ApiResponse(code = 404, message = "Forma de pagamento não encontrada",
				response = Problem.class)
	})
	void remover(
			@ApiParam(value = "ID de uma forma de pagamento", example = "1") Long formaPagamentoId);
}