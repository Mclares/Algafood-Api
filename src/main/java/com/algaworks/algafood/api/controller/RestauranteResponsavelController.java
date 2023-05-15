package com.algaworks.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.api.openapi.controller.RestauranteResponsavelControllerOpenApi;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/responsaveis")
public class RestauranteResponsavelController implements RestauranteResponsavelControllerOpenApi {

	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@GetMapping
	public CollectionModel<UsuarioModel> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestauranteService
				.buscarOuFalhar(restauranteId);
		
		CollectionModel<UsuarioModel> usuariosModel = 
		usuarioModelAssembler.toCollectionModel(restaurante.getUsuarios())
				.removeLinks()
				.add(algaLinks.linkToResponsaveisRestaurante(restauranteId))
				.add(algaLinks.linkToResponsaveisRestauranteAssociar(
						restauranteId, "associar"));
		
		usuariosModel.getContent().forEach(usuarioModel -> {
			usuarioModel.add(algaLinks.linkToResponsaveisRestauranteDesassociar(
					restauranteId, restauranteId, "desassociar"));
		});
		
		return usuariosModel;
	}
	
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociarResponsavel(
			@PathVariable Long restauranteId,
			@PathVariable Long usuarioId) {
		
		cadastroRestauranteService.desassociarResponsavel(restauranteId, usuarioId);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associarResponsavel(
			@PathVariable Long restauranteId,
			@PathVariable Long usuarioId) {
		
		cadastroRestauranteService.associarResponsavel(restauranteId, usuarioId);
		
		return ResponseEntity.noContent().build();
	}
}
