package com.algaworks.algafood.api.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProdutoInput {

	@ApiModelProperty(example = "Pizza")
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "Muçarela de búfala")
	@NotBlank
	private String descricao;
	
	@ApiModelProperty(example = "60.00")
	@NotNull
	@PositiveOrZero
	private BigDecimal preco;
	
	@ApiModelProperty(example = "true")
	@NotNull
	private Boolean ativo;
}