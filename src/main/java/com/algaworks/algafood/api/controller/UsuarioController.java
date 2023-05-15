package com.algaworks.algafood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.UsuarioInputDisassembler;
import com.algaworks.algafood.api.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.api.model.input.SenhaInput;
import com.algaworks.algafood.api.model.input.UsuarioComSenhaInput;
import com.algaworks.algafood.api.model.input.UsuarioInput;
import com.algaworks.algafood.api.openapi.controller.UsuarioControllerOpenApi;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;
import com.algaworks.algafood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController implements UsuarioControllerOpenApi {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;
	
	@Autowired
	private UsuarioInputDisassembler usuarioInputDisassembler;
	
	@GetMapping
	public CollectionModel<UsuarioModel> listar() {
		return usuarioModelAssembler
				.toCollectionModel(usuarioRepository.findAll());
	}
	
	@GetMapping("/{usuarioId}")
	public UsuarioModel buscarPorId(@PathVariable Long usuarioId) {
		return usuarioModelAssembler
				.toModel(cadastroUsuarioService.buscarOuFalhar(usuarioId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel adicionar(
			@RequestBody 
			@Valid UsuarioComSenhaInput usuarioComSenhaInput) {
		
		Usuario usuario = usuarioInputDisassembler
				.toDomainModel(usuarioComSenhaInput);
		return usuarioModelAssembler.toModel(cadastroUsuarioService.salvar(usuario));
	}
	
	@PutMapping("/{usuarioId}")
	public UsuarioModel atualizar(
			@PathVariable Long usuarioId,
			@RequestBody 
			@Valid UsuarioInput usuarioInput) {
		
		Usuario usuarioAtual = cadastroUsuarioService.buscarOuFalhar(usuarioId); 
		usuarioInputDisassembler
				.copyToDomainModel(usuarioInput, usuarioAtual);
		return usuarioModelAssembler
				.toModel(cadastroUsuarioService.salvar(usuarioAtual));
	}
	
	@PutMapping("/{usuarioId}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarSenha(
			@PathVariable Long usuarioId,
			@RequestBody @Valid SenhaInput senhaInput) {
		
		cadastroUsuarioService.salvarSenha(
				usuarioId, senhaInput.getSenhaAtual(), senhaInput.getNovaSenha());
	}
	
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long usuarioId) {
		cadastroUsuarioService.excluir(usuarioId);
	}
}
