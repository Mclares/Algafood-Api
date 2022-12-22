package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.PedidoInputDisassembler;
import com.algaworks.algafood.api.assembler.PedidoModelAssembler;
import com.algaworks.algafood.api.assembler.PedidoResumoModelAssembler;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.service.CadastroPedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

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
	
	@GetMapping
	public List<PedidoResumoModel> listar() {
		return pedidoResumoModelAssembler
				.toCollectionModel(pedidoRepository.findAll());
	}
	
	@GetMapping("/{codigo}")
	public PedidoModel buscarPorId(@PathVariable String codigo) {
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
}
