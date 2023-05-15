package com.algaworks.algafood.api.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.model.PermissaoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Permissoes")
public interface PermissaoControllerOpenApi {

	@ApiOperation("Lista os grupos cadastrados")
	CollectionModel<PermissaoModel> listar();
}
