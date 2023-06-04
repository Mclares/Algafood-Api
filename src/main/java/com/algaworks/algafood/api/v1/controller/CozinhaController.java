package com.algaworks.algafood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
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

import com.algaworks.algafood.api.v1.assembler.CozinhaInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.CozinhaModelAssembler;
import com.algaworks.algafood.api.v1.model.CozinhaModel;
import com.algaworks.algafood.api.v1.model.input.CozinhaInput;
import com.algaworks.algafood.api.v1.openapi.controller.CozinhaControllerOpenApi;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

//@Slf4j
@RestController
@RequestMapping("/v1/cozinhas")
public class CozinhaController implements CozinhaControllerOpenApi {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	@Autowired
	private CozinhaModelAssembler cozinhaModelAssembler;

	@Autowired
	private CozinhaInputDisassembler cozinhaInputDisassembler;
	
	@Autowired
	private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public PagedModel<CozinhaModel> listar(@PageableDefault(size = 10) Pageable pageable) {
		
//		log.info("Consultando cozinhas com páginas de {} registros...", pageable.getPageSize());
//		if (true) {
//			throw new RuntimeException("Teste de Exception");
//		}
		
		Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);
	
		PagedModel<CozinhaModel> pagedCozinhasModel = pagedResourcesAssembler
				.toModel(cozinhasPage, cozinhaModelAssembler);
		
		return pagedCozinhasModel;
	}

	@GetMapping(value = "/{cozinhaId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CozinhaModel buscarPorId(@PathVariable Long cozinhaId) {
		return cozinhaModelAssembler
				.toModel(cadastroCozinhaService.buscarOuFalhar(cozinhaId));
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public CozinhaModel adicionar(
			@RequestBody 
			@Valid CozinhaInput cozinhaInput) {
		
			Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
			return cozinhaModelAssembler.toModel(cadastroCozinhaService.salvar(cozinha));
	}

	@PutMapping(value = "/{cozinhaId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CozinhaModel atualizar(
			@PathVariable Long cozinhaId, 
			@RequestBody 
			@Valid CozinhaInput cozinhaInput) {
		
		Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
		cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
		return cozinhaModelAssembler.toModel(cadastroCozinhaService.salvar(cozinhaAtual));
	}

	@DeleteMapping(value = "/{cozinhaId}", produces = {})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) {
		cadastroCozinhaService.excluir(cozinhaId);
	}
}