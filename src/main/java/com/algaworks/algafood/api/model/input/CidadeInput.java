package com.algaworks.algafood.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.algaworks.algafood.domain.model.Estado;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeInput {

	@NotBlank
	private String nome;
	
	@Valid
	@NotNull
	private Estado estado;
}
