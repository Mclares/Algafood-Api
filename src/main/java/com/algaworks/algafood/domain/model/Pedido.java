package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import com.algaworks.algafood.domain.event.PedidoCanceladoEvent;
import com.algaworks.algafood.domain.event.PedidoConfirmadoEvent;
import com.algaworks.algafood.domain.exception.NegocioException;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class Pedido extends AbstractAggregateRoot<Pedido>{

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String codigo;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCriacao;

	@Column(columnDefinition = "datetime")
	private OffsetDateTime dataConfirmacao;

	@Column(columnDefinition = "datetime")
	private OffsetDateTime dataCancelamento;

	@Column(columnDefinition = "datetime")
	private OffsetDateTime dataEntrega;

	@Embedded
	private Endereco enderecoEntrega;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private FormaPagamento formaPagamento;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurante restaurante;

	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id", nullable = false)
	private Usuario cliente;

	@Enumerated(EnumType.STRING)
	public StatusPedido statusPedido = StatusPedido.CRIADO;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itensPedido = new ArrayList<>();

	public void calcularValorTotal() {
		getItensPedido().forEach(ItemPedido::calcularPrecoTotal);

		this.subtotal = getItensPedido().stream().map(item -> item.getPrecoTotal()).reduce(BigDecimal.ZERO,
				BigDecimal::add);

		this.valorTotal = this.subtotal.add(this.taxaFrete);
	}

	public void definirFrete() {
		setTaxaFrete(getRestaurante().getTaxaFrete());
	}

	public void atribuirPedidosAosItens() {
		getItensPedido().forEach(item -> item.setPedido(this));
	}

	public void confirmar() {
		setStatusPedido(StatusPedido.CONFIRMADO);
		setDataConfirmacao(OffsetDateTime.now());
		
		registerEvent(new PedidoConfirmadoEvent(this));
	}

	public void entregar() {
		setStatusPedido(StatusPedido.ENTREGUE);
		setDataEntrega(OffsetDateTime.now());
	}

	public void cancelar() {
		setStatusPedido(StatusPedido.CANCELADO);
		setDataCancelamento(OffsetDateTime.now());
		
		registerEvent(new PedidoCanceladoEvent(this));
	}
	
	public boolean podeSerConfirmado() {
		return getStatusPedido().podeAlterarPara(StatusPedido.CONFIRMADO);
	}
	
	public boolean podeSerEntregue() {
		return getStatusPedido().podeAlterarPara(StatusPedido.ENTREGUE);
	}
	
	public boolean podeSerCancelado() {
		return getStatusPedido().podeAlterarPara(StatusPedido.CANCELADO);
	}
	
	private void setStatusPedido(StatusPedido novoStatus) {
		if (getStatusPedido().naoPodeAlterarPara(novoStatus)) {
			throw new NegocioException(String.format(
					"Status do pedido %s não pode ser alterado de %s para %s!",
					getCodigo(), getStatusPedido().getDescricao(), 
					novoStatus.getDescricao()));
		}
		this.statusPedido = novoStatus;
	}
	
	// Método executado sempre antes da persistência de dados da JPA
	@PrePersist
	private void gerarCodigo() {
		setCodigo(UUID.randomUUID().toString());
	}
}
