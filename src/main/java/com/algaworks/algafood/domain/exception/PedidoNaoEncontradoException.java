package com.algaworks.algafood.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

//	public PedidoNaoEncontradoException(Long pedidoId) {
//		super(String.format("Pedido de código %d não encontrado!", pedidoId));
//	}
	
	public PedidoNaoEncontradoException(String codigo) {
		super(String.format("Pedido de código %s não encontrado!", codigo));
	}
}
