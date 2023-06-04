package com.algaworks.algafood.api.v1.model.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoInput {

	@NotNull
	@Valid
	private RestauranteIdInput restaurante;
	
	@NotNull
	@Valid
	private FormaPagamentoIdInput formaPagamento;
	
	@NotNull
	@Valid
	private EnderecoInput enderecoEntrega;
	
	@Valid
	@Size(min = 1)
	@NotNull
	private List<ItemPedidoInput> itens;
}
