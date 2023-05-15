package com.algaworks.algafood.api.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "produtos")
@Setter
@Getter
public class ProdutoModel extends RepresentationModel<ProdutoModel>{

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Pizza")
	private String nome;
	
	@ApiModelProperty(example = "Muçarela de búfala")
	private String descricao;
	
	@ApiModelProperty(example = "60.00")
	private BigDecimal preco;
	
	@ApiModelProperty(example = "true")
	private Boolean ativo;
}