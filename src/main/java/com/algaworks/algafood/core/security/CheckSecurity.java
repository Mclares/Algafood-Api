package com.algaworks.algafood.core.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

	public @interface Cozinhas {

		@PreAuthorize("@algaSecurity.podeConsultarCozinhas()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {}

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and "
				+ "hasAuthority('EDITAR_COZINHAS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar {}
	}

	public @interface Restaurantes {

		@PreAuthorize("@algaSecurity.podeConsultarRestaurantes()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {}

		@PreAuthorize("@algaSecurity."
				+ "podeGerenciarFuncionamentoRestaurantes(#restauranteId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciarFuncionamento {}

		@PreAuthorize("@algaSecurity.podeGerenciarCadastroRestaurantes()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciarCadastro {}
	}

	public @interface Pedidos {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeCriar{}

		@PreAuthorize("@algaSecurity.podePesquisarVariosPedidos("
				+ "#pedidoFilter.clienteId, #pedidoFilter.restauranteId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodePesquisar {}

		@PreAuthorize("@algaSecurity.podeConsultarPedidos()")
		@PostAuthorize("hasAuthority('CONSULTAR_PEDIDOS') or "
                + "@algaSecurity.usuarioAutenticadoIgual(returnObject.cliente.id) or "
                + "@algaSecurity.gerenciaRestaurante(returnObject.restaurante.id)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeBuscar {}
		
		@PreAuthorize("@algaSecurity.podeGerenciarPedidosLinks(#codigo)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciarPedidos{}
	}
	
	public @interface FormasPagamento {

		@PreAuthorize("@algaSecurity.podeConsultarFormasDePagamento()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {}

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and "
				+ "hasAuthority('EDITAR_FORMAS_PAGAMENTO')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciarFormaPagamento {}
	}
	
	public @interface Cidades {

		@PreAuthorize("@algaSecurity.podeConsultarCidades()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {}

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and "
				+ "hasAuthority('EDITAR_CIDADES')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciarCidades {}
	}
	
	public @interface Estados {	

		@PreAuthorize("@algaSecurity.podeConsultarEstados()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {}

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and "
				+ "hasAuthority('EDITAR_ESTADOS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciarEstados {}
	}
	
	public @interface UsuariosGruposPermissoes {

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and "
				+ "@algaSecurity.usuarioAutenticadoIgual(#usuarioId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAlterarPropriaSenha {}
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and "
				+ "(hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES') or "
				+ "@algaSecurity.usuarioAutenticadoIgual(#usuarioId))")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAlterarUsuario {}
		
		@PreAuthorize("@algaSecurity.podeEditarUsuariosGruposPermissoes()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar {}
		
		@PreAuthorize("@algaSecurity.podeConsultarUsuariosGruposPermissoes()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {}
	}
	
	public @interface Estatisticas {

		@PreAuthorize("@algaSecurity.podeConsultarEstatisticas()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {}
	}
}	