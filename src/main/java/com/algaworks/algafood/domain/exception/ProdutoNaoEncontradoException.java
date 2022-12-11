package com.algaworks.algafood.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public ProdutoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public ProdutoNaoEncontradoException(Long restauranteId, Long produtoId) {
		super(String.format("Produto de código %d não encontrado "
				+ "para restaurante informado de código %d!", produtoId, restauranteId));
	}
}
