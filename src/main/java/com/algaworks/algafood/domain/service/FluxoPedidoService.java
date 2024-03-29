package com.algaworks.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;

@Service
public class FluxoPedidoService {

	@Autowired
	private CadastroPedidoService cadastroPedidoService;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Transactional
	public void confirmar(String codigo) {
		Pedido pedido = cadastroPedidoService.buscarOuFalhar(codigo);
		pedido.confirmar();
		pedidoRepository.save(pedido);
	}
	
	@Transactional
	public void entregar(String codigo) {
		Pedido pedido = cadastroPedidoService.buscarOuFalhar(codigo);
		pedido.entregar();
	}
	
	@Transactional
	public void cancelar(String codigo) {
		Pedido pedido = cadastroPedidoService.buscarOuFalhar(codigo);
		pedido.cancelar();
		pedidoRepository.save(pedido);
	}
}
