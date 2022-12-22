package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.PedidoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.PedidoRepository;

@Service
public class CadastroPedidoService {

	private static final String MSG_FORMA_PAGTO_NAO_ACEITA = "Forma de pagamento "
			+ "%s não é aceita por este restaurante";

//	private static final String MSG_PEDIDO_EM_USO = "Pedido de código %d não "
//			+ "pode ser removido, pois está em uso!";
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;

	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamentoService;
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private CadastroProdutoService cadastroProdutoService;

	@Transactional
	public Pedido salvar(Pedido pedido) {
		validarPedido(pedido);
		validarItens(pedido);
		pedido.definirFrete();
		pedido.calcularValorTotal();
		return pedidoRepository.save(pedido);
	}
	
//	@Transactional
//	public void excluir(Long pedidoId) {
//		
//		try {
//			pedidoRepository.deleteById(pedidoId);
//			pedidoRepository.flush();
//		} catch (EmptyResultDataAccessException e) {
//			throw new PedidoNaoEncontradoException(pedidoId);
//		} catch (DataIntegrityViolationException e) {
//			String.format(MSG_PEDIDO_EM_USO, pedidoId);
//		}
//	}
	
	public Pedido buscarOuFalhar(String codigo) {
		return pedidoRepository.findByCodigo(codigo).orElseThrow(
				() -> new PedidoNaoEncontradoException(codigo));
	}
	
	private void validarPedido(Pedido pedido) {
		Restaurante restaurante = cadastroRestauranteService
				.buscarOuFalhar(pedido.getRestaurante().getId());
		FormaPagamento formaPagamento = cadastroFormaPagamentoService
				.buscarOuFalhar(pedido.getFormaPagamento().getId());
		Usuario cliente = cadastroUsuarioService
				.buscarOuFalhar(pedido.getCliente().getId());
		Cidade cidade = cadastroCidadeService
				.buscarOuFalhar(pedido.getEnderecoEntrega().getCidade().getId());

		pedido.setRestaurante(restaurante);
		pedido.setFormaPagamento(formaPagamento);
		pedido.setCliente(cliente);
		pedido.getEnderecoEntrega().setCidade(cidade);
		
		if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
			throw new NegocioException(
					String.format(MSG_FORMA_PAGTO_NAO_ACEITA, formaPagamento));
		}
	}
	
	private void validarItens(Pedido pedido) {
		pedido.getItensPedido().forEach(item -> {
			Produto produto = cadastroProdutoService.buscarOuFalhar(
					pedido.getRestaurante().getId(), item.getProduto().getId());
			
			item.setPedido(pedido);
			item.setProduto(produto);
			item.setPrecoUnitario(produto.getPreco());
		});
	}
}