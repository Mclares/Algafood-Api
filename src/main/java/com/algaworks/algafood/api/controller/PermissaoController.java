package com.algaworks.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.PermissaoModelAssembler;
import com.algaworks.algafood.api.model.PermissaoModel;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController implements PermissaoControllerOpenApi {

	@Autowired 
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public CollectionModel<PermissaoModel> listar() {
		return permissaoModelAssembler.toCollectionModel(permissaoRepository.findAll());
	}
}