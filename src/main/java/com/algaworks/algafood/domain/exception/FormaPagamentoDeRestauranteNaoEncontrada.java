package com.algaworks.algafood.domain.exception;

public class FormaPagamentoDeRestauranteNaoEncontrada extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public FormaPagamentoDeRestauranteNaoEncontrada(String mensagem) {
		super(mensagem);
	}
	
	public FormaPagamentoDeRestauranteNaoEncontrada(
			Long restauranteId, Long formaPagamentoId) {
		this(String.format("Forma de pagamento de código %d não encontrada "
				+ "para código %d de restaurante!", 
				restauranteId, formaPagamentoId));
	}
}
