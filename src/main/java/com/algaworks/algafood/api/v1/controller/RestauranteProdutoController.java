package com.algaworks.algafood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.assembler.ProdutoInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.ProdutoModelAssembler;
import com.algaworks.algafood.api.v1.model.ProdutoModel;
import com.algaworks.algafood.api.v1.model.input.ProdutoInput;
import com.algaworks.algafood.api.v1.openapi.controller.RestauranteProdutoControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.service.CadastroProdutoService;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/v1/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController implements RestauranteProdutoControllerOpenApi {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CadastroProdutoService cadastroProdutoService;
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;
	
	@Autowired
	private ProdutoInputDisassembler produtoInputDisassembler;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@CheckSecurity.Restaurantes.PodeConsultar
	@Override
	@GetMapping
	public CollectionModel<ProdutoModel> listar(
			@PathVariable Long restauranteId,
			@RequestParam(required = false) Boolean incluirInativos){
		
		Restaurante restaurante = 
				cadastroRestauranteService.buscarOuFalhar(restauranteId);
		
		List<Produto> todosProdutos = null;
		
		if (incluirInativos) {
			todosProdutos = produtoRepository.findTodosByRestaurante(restaurante);
		} else {
			todosProdutos = produtoRepository.findAtivosByRestaurante(restaurante);
		}
		
		return produtoModelAssembler.toCollectionModel(todosProdutos)
				.add(algaLinks.linkToRestauranteProdutos(restauranteId));
	}
	
	@CheckSecurity.Restaurantes.PodeConsultar
	@Override
	@GetMapping("/{produtoId}")
	public ProdutoModel buscarPorId(
			@PathVariable Long restauranteId,
			@PathVariable Long produtoId) {
		
		return produtoModelAssembler
				.toModel(cadastroProdutoService
						.buscarOuFalhar(restauranteId, produtoId));
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel adicionar(
			@PathVariable Long restauranteId,
			@RequestBody @Valid ProdutoInput produtoInput) {
		
		Restaurante restaurante = cadastroRestauranteService
				.buscarOuFalhar(restauranteId);
		Produto produto = produtoInputDisassembler.toDomainModel(produtoInput);
		produto.setRestaurante(restaurante);
		return produtoModelAssembler.toModel(cadastroProdutoService.salvar(produto));
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
	@Override
	@PutMapping("/{produtoId}")
	public ProdutoModel atualizar(
			@PathVariable Long restauranteId,
			@PathVariable Long produtoId,
			@RequestBody @Valid ProdutoInput produtoInput) {
		
		Restaurante restaurante = cadastroRestauranteService
				.buscarOuFalhar(restauranteId);
		Produto produtoAtual = cadastroProdutoService
				.buscarOuFalhar(restaurante.getId(), produtoId);
		produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);
		return produtoModelAssembler
				.toModel(cadastroProdutoService.salvar(produtoAtual));
	}
}
