package com.algaworks.algafood.api.v2.controller;

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

import com.algaworks.algafood.api.ResourceUriHelper;
import com.algaworks.algafood.api.v2.assembler.CidadeInputDisassemblerV2;
import com.algaworks.algafood.api.v2.assembler.CidadeModelAssemblerV2;
import com.algaworks.algafood.api.v2.model.CidadeModelV2;
import com.algaworks.algafood.api.v2.model.input.CidadeInputV2;
import com.algaworks.algafood.api.v2.openapi.controller.CidadeControllerOpenApiV2;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/v2/cidades")
public class CidadeControllerV2 implements CidadeControllerOpenApiV2 {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidadeService;

	@Autowired
	private CidadeModelAssemblerV2 cidadeModelAssembler;
	
	@Autowired
	private CidadeInputDisassemblerV2 cidadeInputDisassembler;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public CollectionModel<CidadeModelV2> listar() {
		  		
		return cidadeModelAssembler
				.toCollectionModel(cidadeRepository.findAll());
	}

	@GetMapping(value = "/{cidadeId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CidadeModelV2 buscarPorId(@PathVariable Long cidadeId) {
		
		return cidadeModelAssembler
				.toModel(cadastroCidadeService.buscarOuFalhar(cidadeId));
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public CidadeModelV2 adicionar(
			@RequestBody @Valid CidadeInputV2 cidadeInput) {

		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
			CidadeModelV2 cidadeModel = cidadeModelAssembler
					.toModel(cadastroCidadeService.salvar(cidade));
			
			ResourceUriHelper.addUriInResponseHeader(cidadeModel.getIdCidade());
			
			return cidadeModel;
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping(value = "/{cidadeId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CidadeModelV2 atualizar(
			@PathVariable Long cidadeId, 
			@RequestBody @Valid CidadeInputV2 cidadeInput) {

		try {
			Cidade cidadeAtual = cadastroCidadeService.buscarOuFalhar(cidadeId);
			cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
			return cidadeModelAssembler
					.toModel(cadastroCidadeService.salvar(cidadeAtual));
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@DeleteMapping(value = "/{cidadeId}", produces = {})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(
			@PathVariable Long cidadeId) {
		cadastroCidadeService.excluir(cidadeId);
	}
}