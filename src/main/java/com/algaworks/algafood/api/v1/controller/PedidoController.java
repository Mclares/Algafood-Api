package com.algaworks.algafood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.assembler.PedidoInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.PedidoModelAssembler;
import com.algaworks.algafood.api.v1.assembler.PedidoResumoModelAssembler;
import com.algaworks.algafood.api.v1.model.PedidoModel;
import com.algaworks.algafood.api.v1.model.PedidoResumoModel;
import com.algaworks.algafood.api.v1.model.input.PedidoInput;
import com.algaworks.algafood.api.v1.openapi.controller.PedidoControllerOpenApi;
import com.algaworks.algafood.core.data.PageWrapper;
import com.algaworks.algafood.core.data.PageableTranslator;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.service.CadastroPedidoService;
import com.algaworks.algafood.infrastructure.repository.spec.PedidoSpecs;
import com.google.common.collect.ImmutableMap;

@RestController
@RequestMapping("/v1/pedidos")
public class PedidoController implements PedidoControllerOpenApi {

	@Autowired
	private PedidoResumoModelAssembler pedidoResumoModelAssembler;
	
	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private CadastroPedidoService cadastroPedidoService;
	
	@Autowired
	private PedidoInputDisassembler pedidoInputDisassembler;
	
	@Autowired
	private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;

	@GetMapping
	public PagedModel<PedidoResumoModel> pesquisar(
			@PageableDefault(size = 10) Pageable pageable, PedidoFilter pedidoFilter) {
		
		Pageable pageableTraduzido = traduzirPageable(pageable);
		
		Page<Pedido> pedidosPage = pedidoRepository.findAll(
				PedidoSpecs.usandoFiltro(pedidoFilter), pageableTraduzido);
		pedidosPage = new PageWrapper<>(pedidosPage, pageable);
		
		PagedModel<PedidoResumoModel> pagedPedidosResumoModel = pagedResourcesAssembler
				.toModel(pedidosPage, pedidoResumoModelAssembler);
		
		return pagedPedidosResumoModel;
	}
	
	@GetMapping("/{codigo}")
	public PedidoModel buscarPorCodigo(@PathVariable String codigo) {
		return pedidoModelAssembler.toModel(cadastroPedidoService
				.buscarOuFalhar(codigo));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
		
		try {
			Pedido novoPedido = pedidoInputDisassembler.toDomainModel(pedidoInput);
			// Pegar usuário autenticado
			novoPedido.setCliente(new Usuario());
			novoPedido.getCliente().setId(1L);
			
			return pedidoModelAssembler.toModel(cadastroPedidoService.salvar(novoPedido));
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = ImmutableMap.of(
				"codigo", "codigo",
				"nomerestaurante", "restaurante.nome",
				"nomeCliente", "cliente.nome",
				"valorTotal", "ValorTotal" );
		
		return PageableTranslator.translate(apiPageable, mapeamento);
	}
}
