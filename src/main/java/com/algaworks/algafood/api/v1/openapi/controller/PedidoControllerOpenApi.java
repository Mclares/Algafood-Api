package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.PedidoModel;
import com.algaworks.algafood.api.v1.model.PedidoResumoModel;
import com.algaworks.algafood.api.v1.model.input.PedidoInput;
import com.algaworks.algafood.domain.filter.PedidoFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
				name = "campos", paramType = "query", type = "string")
	})
	@ApiOperation(value = "Lista os pedidos cadastrados")
	PagedModel<PedidoResumoModel> pesquisar(Pageable pageable, 
			PedidoFilter pedidoFilter);
	
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
				name = "campos", paramType = "query", type = "string")
	})
	@ApiOperation("Busca um pedido por codigo")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Código do pedido inválido", 
		response = Problem.class),
		@ApiResponse(code = 404, message = "Pedido não encontrado", 
		response = Problem.class)
	})
	PedidoModel buscarPorCodigo(
			@ApiParam(value = "Codigo de um pedido", 
			example = "291daf5f-81aa-11ed-8327-025041000001")
			String codigo);

	@ApiOperation("Cadastra um novo pedido")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Pedido cadastrado")
	})
	PedidoModel adicionar(
			@ApiParam(name = "Corpo", 
			value = "Representação de um novo pedido") PedidoInput pedidoInput);
}