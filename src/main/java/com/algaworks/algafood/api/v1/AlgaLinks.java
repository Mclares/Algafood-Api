package com.algaworks.algafood.api.v1;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.controller.CidadeController;
import com.algaworks.algafood.api.v1.controller.CozinhaController;
import com.algaworks.algafood.api.v1.controller.EstadoController;
import com.algaworks.algafood.api.v1.controller.EstatisticasController;
import com.algaworks.algafood.api.v1.controller.FluxoPedidoController;
import com.algaworks.algafood.api.v1.controller.FormaPagamentoController;
import com.algaworks.algafood.api.v1.controller.GrupoController;
import com.algaworks.algafood.api.v1.controller.GrupoPermissaoController;
import com.algaworks.algafood.api.v1.controller.PedidoController;
import com.algaworks.algafood.api.v1.controller.PermissaoController;
import com.algaworks.algafood.api.v1.controller.RestauranteController;
import com.algaworks.algafood.api.v1.controller.RestauranteFormaPagamentoController;
import com.algaworks.algafood.api.v1.controller.RestauranteProdutoController;
import com.algaworks.algafood.api.v1.controller.RestauranteProdutoFotoController;
import com.algaworks.algafood.api.v1.controller.RestauranteResponsavelController;
import com.algaworks.algafood.api.v1.controller.UsuarioController;
import com.algaworks.algafood.api.v1.controller.UsuarioGrupoController;

@Component
public class AlgaLinks {

	public static final TemplateVariables PAGINACAO_VARIABLES = new TemplateVariables(
		new TemplateVariable("page", VariableType.REQUEST_PARAM),
		new TemplateVariable("size", VariableType.REQUEST_PARAM),
		new TemplateVariable("sort", VariableType.REQUEST_PARAM));
	
	private static final TemplateVariables PROJECAO_VALUE = new TemplateVariables(
			new TemplateVariable("projecao", VariableType.REQUEST_PARAM));
	
	public Link linkToPedidos(String rel) {
		
		TemplateVariables filtroVariables = new TemplateVariables(
				new TemplateVariable("clienteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM));
		String pedidosUrl = WebMvcLinkBuilder.linkTo(PedidoController.class)
				.toUri().toString();
		
		return Link.of(UriTemplate.of(pedidosUrl, 
				PAGINACAO_VARIABLES.concat(filtroVariables)),rel);
	}
	
	public Link linkToPedidos() {
		return linkToPedidos(IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToRestaurantes(String rel) {
		String restaurantesUrl = WebMvcLinkBuilder.linkTo(RestauranteController.class)
				.toUri().toString();
		return Link.of(UriTemplate.of(restaurantesUrl,PROJECAO_VALUE),rel);
	}
	
	public Link linkToRestaurantes() {
		return linkToRestaurantes(IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToConfirmacaoPedido(String codigoPedido, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				FluxoPedidoController.class).confirmar(codigoPedido)).withRel(rel);
	}
	
	public Link linkToEntregaPedido(String codigoPedido, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				FluxoPedidoController.class).entregar(codigoPedido)).withRel(rel);
	}
	
	public Link linkToCancelamentoPedido(String codigoPedido, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				FluxoPedidoController.class).cancelar(codigoPedido)).withRel(rel);
	}
	
	public Link linkToRestaurante(Long restauranteId, String rel) {	
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				RestauranteController.class).buscarPorId(restauranteId)).withRel(rel);
	}
	
	public Link linkToRestaurante(Long restauranteId) {
		return linkToRestaurante(restauranteId, IanaLinkRelations.SELF_VALUE);
	}
		
	public Link linkToRestauranteAbertura(Long restauranteId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				RestauranteController.class).abrir(restauranteId)).withRel(rel);
	}
	
	public Link linkToRestauranteFechamento(Long restauranteId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				RestauranteController.class).fechar(restauranteId)).withRel(rel);
	}
	
	public Link linkToRestauranteAtivacao(Long restauranteId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				RestauranteController.class).ativar(restauranteId)).withRel(rel);
	}
	
	public Link linkToRestauranteInativacao(Long restauranteId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				RestauranteController.class).inativar(restauranteId)).withRel(rel);
	}
	
	public Link linkToRestauranteProdutoFoto(Long restauranteId, Long produtoId,
			String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				RestauranteProdutoFotoController.class).recuperarFoto(
						restauranteId, produtoId)).withRel(rel);
	}
	
	public Link linkToRestauranteProdutoFoto(Long restauranteId, Long produtoId) {
		return linkToRestauranteProdutoFoto(restauranteId, produtoId,
				IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToRestauranteProdutos(Long restauranteId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				RestauranteProdutoController.class).listar(restauranteId, null))
				.withRel(rel);
	}
	
	public Link linkToRestauranteProdutos(Long restauranteId) {
		return linkToRestauranteProdutos(restauranteId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToUsuario(Long usuarioId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				UsuarioController.class).buscarPorId(usuarioId)).withRel(rel); 
	}
		
	public Link linkToUsuario(Long usuarioId) {
		return linkToUsuario(usuarioId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToUsuarios(String rel) {
		return WebMvcLinkBuilder.linkTo(UsuarioController.class).withRel(rel);
	}
	
	public Link linkToUsuarios() {
		return linkToUsuarios(IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToGrupos(String rel) {
		return WebMvcLinkBuilder.linkTo(GrupoController.class).withRel(rel);
	}
	
	public Link linkToGrupos() {
		return linkToGrupos(IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToGrupoPermissoes(Long grupoId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				GrupoPermissaoController.class).listar(grupoId)).withRel(rel);
	}
	
	public Link linkToGrupoPermissoes(Long grupoId) {
		return linkToGrupoPermissoes(grupoId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToGrupoPermissaoDesassociar(
			Long grupoId, Long permissaoId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				GrupoPermissaoController.class).desassociarPermissao(
						grupoId, permissaoId)).withRel(rel);
	}
	
	public Link linkToGrupoPermissaoAssociar(
			Long grupoId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				GrupoPermissaoController.class).associarPermissao(
						grupoId, null)).withRel(rel);
	}
	
	public Link linkToUsuarioGrupoDesassociar(
			Long grupoId, Long usuarioId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				UsuarioGrupoController.class).desassociarGrupo(usuarioId, grupoId))
				.withRel(rel);
	}

	public Link linkToUsuarioGrupoAssociar(
			Long usuarioId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				UsuarioGrupoController.class).associarGrupo(usuarioId,null))
				.withRel(rel);
	}
	
	public Link linkToGruposUsuario(Long usuarioId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				UsuarioGrupoController.class).listar(usuarioId)).withRel(rel);
	}
	
	public Link linkToGruposUsuario(Long usuarioId) {
		return linkToGruposUsuario(usuarioId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToPermissoes(String rel) {
		return WebMvcLinkBuilder.linkTo(PermissaoController.class).withRel(rel);
	}
	
	public Link linkToPermissoes() {
		return linkToPermissoes(IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToResponsaveisRestauranteDesassociar(
			Long restauranteId, Long usuarioId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				RestauranteResponsavelController.class).desassociarResponsavel(
						restauranteId, usuarioId)).withRel(rel);
	}
	
	public Link linkToResponsaveisRestauranteAssociar(
			Long restauranteId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				RestauranteResponsavelController.class).associarResponsavel(
						restauranteId, null)).withRel(rel);
	}

	public Link linkToResponsaveisRestaurante(Long restauranteId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestauranteResponsavelController.class)
				.listar(restauranteId)).withRel(rel);
	}
	
	public Link linkToResponsaveisRestaurante(Long restauranteId) {
		return linkToResponsaveisRestaurante(restauranteId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToFormaPagamento(Long formaPagamentoId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				FormaPagamentoController.class).buscarporId(formaPagamentoId, null))
				.withRel(rel);
	}
	
	public Link linkToFormaPagamento(Long formaPagamentoId) {
		return linkToFormaPagamento(formaPagamentoId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToFormasPagamento(String rel) {
		return WebMvcLinkBuilder.linkTo(FormaPagamentoController.class).withRel(rel);
	}
	
	public Link linkToFormasPagamento() {
		return linkToFormasPagamento(IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToRestauranteFormasPagamento(Long restauranteId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				RestauranteFormaPagamentoController.class)
				.listar(restauranteId)).withRel(rel);
	}
	
	public Link linkToRestauranteFormasPagamento(Long restauranteId) {
		return linkToRestauranteFormasPagamento(restauranteId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToRestauranteFormasPagamentoDesassociacao(
			Long restauranteId, Long formaPagamentoId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				RestauranteFormaPagamentoController.class)
				.desassociar(restauranteId, formaPagamentoId)).withRel(rel);
	}
	
	public Link linkToRestauranteFormasPagamentoAssociacao(
			Long restauranteId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				RestauranteFormaPagamentoController.class)
				.associar(restauranteId, null)).withRel(rel);
	}
	
	public Link linkToCidade(Long cidadeId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class)
						.buscarPorId(cidadeId)).withRel(rel);
	}
	
	public Link linkToCidade(Long cidadeId) {
		return linkToCidade(cidadeId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToCidades(String rel) {
		return WebMvcLinkBuilder.linkTo(CidadeController.class).withRel(rel);
	}
	
	public Link linkToCidades() {
		return linkToCidades(IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToCozinha(Long cozinhaId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				CozinhaController.class).buscarPorId(cozinhaId)).withRel(rel);
	}
	
	public Link linkToCozinha(Long cozinhaId) {
		return linkToCozinha(cozinhaId, IanaLinkRelations.SELF_VALUE);
	}

	public Link linkToCozinhas(String rel) {
		return WebMvcLinkBuilder.linkTo(CozinhaController.class).withRel(rel);
	}
	
	public Link linkToCozinhas() {
		return linkToCozinhas(IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToEstado(Long estadoId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EstadoController.class)
				.buscarPorId(estadoId)).withRel(rel);
	}
	
	public Link linkToEstado(Long estadoId) {
		return linkToEstado(estadoId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToEstados(String rel) {
		return WebMvcLinkBuilder.linkTo(EstadoController.class).withRel(rel);
	}
	
	public Link linkToEstados() {
		return linkToEstados(IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToItens(Long restauranteId, Long produtoId, String rel) {
		return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				RestauranteProdutoController.class).buscarPorId(
						restauranteId, produtoId)).withRel(rel);
	}
	
	public Link linkToItens(Long restauranteId, Long produtoId) {
		return linkToItens(restauranteId, produtoId, IanaLinkRelations.SELF_VALUE);
	}
	
	public Link linkToEstatisticas(String rel) {
		return WebMvcLinkBuilder.linkTo(EstatisticasController.class).withRel(rel);
	}
	
	public Link linkToEstatisticasVendasDiarias(String rel) {
		
		TemplateVariables filtroVariables = new TemplateVariables(
			new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
			new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
			new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM),
			new TemplateVariable("timeOffSet", VariableType.REQUEST_PARAM));
			
		String pedidosUrl = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
				EstatisticasController.class).consultarVendasDiarias(null, null))
				.toUri().toString();
		
		return Link.of(UriTemplate.of(pedidosUrl, filtroVariables), rel);
	}
}
