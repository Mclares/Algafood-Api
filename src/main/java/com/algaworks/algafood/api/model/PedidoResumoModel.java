package com.algaworks.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoResumoModel {

//  Alterando o modelo de representação para 
//  não trazer mais o ID do pedido
//	private Long id;
	private String codigo;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private String statusPedido;
	private OffsetDateTime dataCriacao;
	private RestauranteResumoModel restaurante;
//	private UsuarioModel cliente;
	private String nomeCliente;
}
