package com.algaworks.algafood.domain.exception;

public class FormaPagamentoNaoEncontrada extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public FormaPagamentoNaoEncontrada(String mensagem) {
		super(mensagem);
	}
	
	public FormaPagamentoNaoEncontrada(Long formaPagamentoId) {
		this(String.format("Forma de pagamento de código %d não encontrada!", 
				formaPagamentoId));
	}
}
