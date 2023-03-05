package com.algaworks.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoResumoModel {

//  Alterando o modelo de representação para 
//  não trazer mais o ID do pedido
//	private Long id;
	
	@ApiModelProperty(example = "291daf5f-81aa-11ed-8327-025041000001")
	private String codigo;
	
	@ApiModelProperty(example = "298.90")
	private BigDecimal subtotal;
	
	@ApiModelProperty(example = "10.00")
	private BigDecimal taxaFrete;
	
	@ApiModelProperty(example = "308.90")
	private BigDecimal valorTotal;
	
	@ApiModelProperty(example = "CRIADO")
	private String statusPedido;
	
	@ApiModelProperty(example = "2023-02-19T23:26:01Z")
	private OffsetDateTime dataCriacao;
	
	private RestauranteResumoModel restaurante;

	private UsuarioModel cliente;
}