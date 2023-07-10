package com.algaworks.algafood.api.v1.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.api.v1.assembler.FotoProdutoModelAssembler;
import com.algaworks.algafood.api.v1.model.FotoProdutoModel;
import com.algaworks.algafood.api.v1.model.input.FotoProdutoInput;
import com.algaworks.algafood.api.v1.openapi.controller.RestauranteProdutoFotoControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.service.CadastroProdutoService;
import com.algaworks.algafood.domain.service.CatalogoFotoProdutoService;
import com.algaworks.algafood.domain.service.FotoStorageService;
import com.algaworks.algafood.domain.service.FotoStorageService.FotoRecuperada;

@RestController
@RequestMapping("/v1/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController implements RestauranteProdutoFotoControllerOpenApi {

	@Autowired
	private CadastroProdutoService cadastroProdutoService;
	
	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProdutoService;
	
	@Autowired
	private FotoProdutoModelAssembler fotoProdutoModelAssembler;
	
	@Autowired
	private FotoStorageService fotoStorageService;
	
	@CheckSecurity.Restaurantes.PodeConsultar
	@Override
	@GetMapping(produces = {MediaType.IMAGE_JPEG_VALUE,
							MediaType.IMAGE_PNG_VALUE,
							MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> buscarFoto(
			@PathVariable Long restauranteId,
			@PathVariable Long produtoId,
			@RequestHeader(name = "accept") String acceptHeader) 
					throws HttpMediaTypeNotAcceptableException {
		
		try {
			FotoProduto fotoProduto = catalogoFotoProdutoService
					.buscarOuFalhar(restauranteId, produtoId);
			
			MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
			List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);
			
			verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);
			
			FotoRecuperada fotoRecuperada = fotoStorageService
					.recuperar(fotoProduto.getNomeArquivo());
			
			if (fotoRecuperada.temUrl()) {
				return ResponseEntity.status(HttpStatus.FOUND)
					.header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
					.build();
			} else {
				return ResponseEntity.ok()
					.contentType(mediaTypeFoto)
					.body(new InputStreamResource(fotoRecuperada.getInputStream()));
		 	}
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<?> recuperarFoto(
			@PathVariable Long restauranteId,
			@PathVariable Long produtoId) {
		
		FotoProduto fotoProduto = catalogoFotoProdutoService
				.buscarOuFalhar(restauranteId, produtoId);
		FotoProdutoModel fotoProdutoModel = fotoProdutoModelAssembler
				.toModel(fotoProduto); 
		
		return ResponseEntity.ok(fotoProdutoModel);
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
	@Override
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoModel atualizarFoto(
			@PathVariable Long restauranteId,
			@PathVariable Long produtoId, 
			@Valid FotoProdutoInput fotoProdutoInput,
			@RequestPart(required = true) MultipartFile arquivo) throws IOException {
		
		Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
		
		FotoProduto fotoProduto = new FotoProduto();
		fotoProduto.setProduto(produto);
		fotoProduto.setDescricao(fotoProdutoInput.getDescricao());
		fotoProduto.setContentType(fotoProdutoInput.getArquivo().getContentType());
		fotoProduto.setNomeArquivo(fotoProdutoInput.getArquivo().getOriginalFilename());
		fotoProduto.setTamanho(fotoProdutoInput.getArquivo().getSize());
		
		return fotoProdutoModelAssembler.toModel(catalogoFotoProdutoService.salvar(
				fotoProduto, 
				fotoProdutoInput.getArquivo().getInputStream()));
	}
	
	@CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
	@Override
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(
			@PathVariable Long restauranteId,
			@PathVariable Long produtoId) {
		
		catalogoFotoProdutoService.excluir(restauranteId, produtoId);
	}
	
	private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto, 
			List<MediaType> mediaTypesAceitas) throws HttpMediaTypeNotAcceptableException {
		
		boolean compativel = mediaTypesAceitas.stream()
				.anyMatch(mediaTypeAceita -> mediaTypeAceita
						.isCompatibleWith(mediaTypeFoto));
		
		if (!compativel) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
		}
	}
}
