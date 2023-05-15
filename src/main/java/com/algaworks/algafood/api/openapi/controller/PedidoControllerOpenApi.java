package com.algaworks.algafood.api.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.domain.filter.PedidoFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
				name = "campos", paramType = "query", type = "string")
	})
	@ApiOperation("Lista os pedidos cadastrados")
	PagedModel<PedidoResumoModel> pesquisar(Pageable pageable, 
			PedidoFilter pedidoFilter);
	
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
				name = "campos", paramType = "query", type = "string")
	})
	@ApiOperation("Busca um pedido por codigo")
	@ApiResponses({
		@ApiResponse(responseCode = "400", 
				description = "codigo do pedido inválido", 
				content = @Content(schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", 
				description = "Pedido não encontrado",
				content = @Content(schema = @Schema(implementation = Problem.class)))
	})
	PedidoModel buscarPorCodigo(
			@ApiParam(value = "Codigo de um pedido", 
			example = "291daf5f-81aa-11ed-8327-025041000001")
			String codigo);

	@ApiOperation("Cadastra um novo pedido")
	@ApiResponses({
		 @ApiResponse(responseCode = "201", description = "Pedido cadastrado")
	})
	PedidoModel adicionar(
			@ApiParam(name = "Corpo", 
			value = "Representação de um novo pedido") PedidoInput pedidoInput);
	}
