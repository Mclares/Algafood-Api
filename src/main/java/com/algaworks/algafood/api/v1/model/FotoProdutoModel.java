package com.algaworks.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "fotos")
@Setter
@Getter
public class FotoProdutoModel extends RepresentationModel<FotoProdutoModel>{

	@ApiModelProperty(example = "579dde2c-f244-4d83-9c1b-c634aa99d3f8_Doc_Marcelo.jpeg")
	private String nomeArquivo;
	
	@ApiModelProperty(example = "Prime Rib ao Ponto")
	private String descricao;
	
	@ApiModelProperty(example = "image/jpeg")
	private String contentType;
	
	@ApiModelProperty(example = "115383")
	private Long tamanho;
}
