package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.RestauranteProdutoFotoController;
import com.algaworks.algafood.api.v1.model.FotoProdutoModel;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.FotoProduto;

@Component
public class FotoProdutoModelAssembler 
	extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoModel>{

	public FotoProdutoModelAssembler() {
		super(RestauranteProdutoFotoController.class, 
				FotoProdutoModel.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	@Override
	public FotoProdutoModel toModel(FotoProduto fotoProduto) {
		
		FotoProdutoModel fotoProdutoModel = 
		modelMapper.map(fotoProduto, FotoProdutoModel.class);
		
		if (algaSecurity.podeConsultarRestaurantes()) {
			fotoProdutoModel.add(algaLinks.linkToRestauranteProdutoFoto(
					fotoProduto.getRestauranteId(), fotoProduto.getProduto().getId()));
			
			fotoProdutoModel.add(algaLinks.linkToItens(fotoProduto.getRestauranteId(), 
					fotoProduto.getProduto().getId(), "produto"));
		}
		
		return fotoProdutoModel;
	}
}