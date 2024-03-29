package com.algaworks.algafood.api.v2.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("CidadeInput")
@Setter
@Getter
public class CidadeInputV2 {

	@ApiModelProperty(example = "São Paulo")
	@NotBlank
	private String nomeCidade;
	
	@ApiModelProperty(value = "1")
	@NotNull
	private Long idEstado;
}
