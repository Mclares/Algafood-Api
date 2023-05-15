package com.algaworks.algafood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.GrupoInputDisassembler;
import com.algaworks.algafood.api.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.api.openapi.controller.GrupoControllerOpenApi;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import com.algaworks.algafood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController implements GrupoControllerOpenApi {

	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired 
	private CadastroGrupoService cadastroGrupoService;
	
	@Autowired
	private GrupoModelAssembler grupoModelAssembler;
	
	@Autowired
	private GrupoInputDisassembler grupoInputDisassembler;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public CollectionModel<GrupoModel> listar() {
		return grupoModelAssembler.toCollectionModel(grupoRepository.findAll());
	}
	
	@GetMapping(value = "/{groupId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public GrupoModel buscarPorId(@PathVariable Long groupId) {
		return grupoModelAssembler
				.toModel(cadastroGrupoService.buscarOuFalhar(groupId));
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoModel adicionar(
			@RequestBody 
			@Valid GrupoInput grupoInput) {
		
		Grupo grupo = grupoInputDisassembler.toDomainModel(grupoInput);
		return grupoModelAssembler.toModel(cadastroGrupoService.salvar(grupo));
	}
	
	@PutMapping(value = "/{grupoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public GrupoModel atualizar(
			@PathVariable Long grupoId,
			@RequestBody
			@Valid GrupoInput grupoInput) {
		
		Grupo grupoAtual = cadastroGrupoService.buscarOuFalhar(grupoId);
		grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);
		return grupoModelAssembler.toModel(cadastroGrupoService.salvar(grupoAtual));
	}
	
	@DeleteMapping(value = "/{grupoId}", produces = {})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long grupoId) {
		cadastroGrupoService.excluir(grupoId);
	}
}
