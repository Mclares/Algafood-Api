package com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoModel {

	@ApiModelProperty(example = "38400-000")
	private String cep;
	
	@ApiModelProperty(example = "Rua Floriano Peixoto")
	private String logradouro;
	
	@ApiModelProperty(example = "500")
	private String numero;
	
	@ApiModelProperty(example = "Apto. 801")
	private String complemento;
	
	@ApiModelProperty(example = "Centro")
	private String bairro;
	
	private CidadeResumoModel cidade;
}
