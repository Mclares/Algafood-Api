package com.algaworks.algafood.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Component
public class AlgaSecurity {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public Long getUsuarioId() {
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		return jwt.getClaim("id_usuario");
	}

	public boolean isAutenticado() {
		return getAuthentication().isAuthenticated();
	}

	public boolean temEscopoEscrita() {
		return hasAuthority("SCOPE_WRITE");
	}

	public boolean temEscopoLeitura() {
		return hasAuthority("SCOPE_READ");
	}

	public boolean podeConsultarCozinhas() {
		return isAutenticado() && temEscopoLeitura();
	}

	public boolean podeConsultarRestaurantes() {
		return isAutenticado() && temEscopoLeitura();
	}
	
	public boolean gerenciaRestaurante(Long restauranteId) {

		if (restauranteId == null) {
			return false;
		}
		return restauranteRepository.existsResponsavel(restauranteId, getUsuarioId());
	}

	public boolean podeGerenciarFuncionamentoRestaurantes(Long restauranteId) {
		return temEscopoEscrita() && (hasAuthority("EDITAR_RESTAURANTES") || 
				gerenciaRestaurante(restauranteId));
	}

	public boolean podeGerenciarCadastroRestaurantes() {
		return temEscopoEscrita() && hasAuthority("EDITAR_RESTAURANTES");
	}

	public boolean podeConsultarPedidos() {
		return isAutenticado() && temEscopoLeitura();
	}

	public boolean usuarioAutenticadoIgual(Long usuarioId) {
		return getUsuarioId() != null && usuarioId != null && getUsuarioId()
				.equals(usuarioId);
	}

	public boolean podePesquisarVariosPedidos(Long clienteId, Long restauranteId) {
		return temEscopoLeitura() && (hasAuthority("CONSULTAR_PEDIDOS") ||
				usuarioAutenticadoIgual(clienteId) || 
				gerenciaRestaurante(restauranteId));
	}

	public boolean podeConsultarFormasDePagamento() {
		return isAutenticado() && temEscopoLeitura();
	}

	public boolean podeConsultarCidades() {
		return isAutenticado() && temEscopoLeitura();
	}

	public boolean podeConsultarEstados() {
		return isAutenticado() && temEscopoLeitura();
	}

	public boolean podeEditarUsuariosGruposPermissoes() {
		return temEscopoEscrita() && 
				hasAuthority("EDITAR_USUARIOS_GRUPOS_PERMISSOES");
	}

	public boolean podeConsultarUsuariosGruposPermissoes() {
		return temEscopoLeitura() && 
				hasAuthority("CONSULTAR_USUARIOS_GRUPOS_PERMISSOES");
	}
	
	public boolean podeConsultarEstatisticas() {
		return temEscopoLeitura() && hasAuthority("GERAR_RELATORIOS");
	}

	public boolean gerenciaRestauranteDoPedido(String codigo) {
		return pedidoRepository.isPedidoGerenciadoPor(codigo, getUsuarioId());
	}

	public boolean hasAuthority(String authorityName) {
		return getAuthentication().getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equals(authorityName));
	}

	public boolean podeGerenciarPedidosLinks(String codigo) {
		return hasAuthority("SCOPE_WRITE")
				&& (hasAuthority("GERENCIAR_PEDIDOS") || gerenciaRestauranteDoPedido(codigo));
	}
}