package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioInput {

	@ApiModelProperty(example = "Zé Ruela")
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "zeruela@gmail.com")
	@NotBlank
	@Email
	private String email;
}
