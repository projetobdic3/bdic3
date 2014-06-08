package br.com.ita.bdic3.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ita.bdic3.dao.ClienteDao;
import br.com.ita.bdic3.dao.MidiaDao;
import br.com.ita.bdic3.dao.PedidoDao;
import br.com.ita.bdic3.dao.ProdutoDao;
import br.com.ita.bdic3.dao.TransacaoDao;
import br.com.ita.bdic3.entity.Cliente;
import br.com.ita.bdic3.entity.FormaPagamento;
import br.com.ita.bdic3.entity.Localidade;
import br.com.ita.bdic3.entity.Midia;
import br.com.ita.bdic3.entity.Pagamento;
import br.com.ita.bdic3.entity.Pedido;
import br.com.ita.bdic3.entity.PedidoHasProduto;
import br.com.ita.bdic3.entity.Produto;
import br.com.ita.bdic3.entity.Transacao;
import br.com.ita.bdic3.enums.TransacaoTipo;
import br.com.ita.bdic3.exception.APIException;
import br.com.ita.bdic3.fixture.LocalidadeFixture;
import br.com.ita.bdic3.vo.CidadeVO;
import br.com.ita.bdic3.vo.PedidoVO;

@Component
public class PedidoService {

	@Autowired
	private PedidoDao pedidoDao;
	
	@Autowired
	private MidiaDao midiaDao;
	
	@Autowired
	private ProdutoDao produtoDao;
	
	@Autowired
	private TransacaoDao transacaoDao;
	
	@Autowired
	private ClienteDao clienteDao;
	
	@Autowired
	private FraudeService fraudeService;
	
	public void save(Pedido Pedido) {
		pedidoDao.save(Pedido);
	}
	
	public void update(Pedido Pedido) {
		pedidoDao.update(Pedido);
	}
	
	public void delete(Pedido Pedido) {
		pedidoDao.delete(Pedido);
	}
	
	public void delete(Long id) {
		pedidoDao.delete(id);
	}
	
	public List<Pedido> findAll() {
		return pedidoDao.findAll();
	}
	
	public Pedido findById(Long id) {
		return pedidoDao.findById(id);
	}

	public void efetuarCompra(PedidoVO pedidoVO) throws Exception {
		Pedido pedido = converterVo(pedidoVO);
		salvarPedido(pedido);
	}

	private Pedido converterVo(PedidoVO pedidoVO) throws Exception {
		
		Calendar dataAtual = Calendar.getInstance();
		
		Pedido pedido = new Pedido();
		
		Produto produto = produtoDao.findById(pedidoVO.getIdProduto());
		
		List<PedidoHasProduto> produtos = new ArrayList<PedidoHasProduto>();
		
		PedidoHasProduto produtoHas = new PedidoHasProduto();
		produtoHas.setPedido(pedido);
		produtoHas.setProduto(produto);
		produtoHas.setPreco(produto.getPrecoNormal());
		produtoHas.setQuantidade(pedidoVO.getQuantidade());
		produtos.add(produtoHas);
		
		pedido.setProdutos(produtos);
		
		Cliente cliente = clienteDao.findById(pedidoVO.getIdCliente());
		
		if (cliente == null) {
			throw new Exception("Cliente não encontrado");
		}
		
		CidadeVO cidade = LocalidadeFixture.getCidadePorNome(pedidoVO.getCidade());
		
		Pagamento pagamento = new Pagamento();
		pagamento.setDt(dataAtual);
		pagamento.setFormaPagamento(FormaPagamento.valueOf(pedidoVO.getFormaPagamento()));
		pagamento.setLocalidade(new Localidade(cidade.getLatitude(), cidade.getLongitude()));
		pagamento.setParcelas(pedidoVO.getQuantidadeParcelas());
		pagamento.setValorPagamento(pedido.getValorTotal());
		
		Midia midia = midiaDao.findByClienteENumero(cliente.getId(), pedidoVO.getNumeroCartao());
		
		if (midia == null) {
			throw new Exception("Cartão de Crédito não encontrado");
		}
				
		pagamento.setMidia(midia);
		
		pedido.setCliente(cliente);
		pedido.setData(dataAtual);
		pedido.setPagamento(pagamento);
		
		return pedido;
	}

	public void salvarPedido(Pedido pedido) throws APIException {
		validar(pedido);
		pedido.setTransacao(criarTransacao(pedido));
		pedidoDao.save(pedido);
	}
	
	private Transacao criarTransacao(Pedido pedido) {
		Transacao transacao = new Transacao();
		transacao.setData(pedido.getData());
		transacao.setHora(pedido.getData());
		transacao.setTotal(pedido.getValorTotal());
		transacao.setTotal(pedido.getValorTotal());
		transacao.setTransacaoTipo(TransacaoTipo.COMPRA);
		return transacao;
	}
	
	private void validar(Pedido pedido) throws APIException {
		Midia midia = midiaDao.findById(pedido.getPagamento().getMidia().getId());
		BigDecimal valorTotal = pedido.getValorTotal();
		
		Cliente cliente = clienteDao.findById(pedido.getCliente().getId());
		
		validarLimiteMidia(valorTotal, midia.getValorMaximo());
		validarFraude(valorTotal, cliente.getUpperLimit(), pedido);
	}

	private void validarLimiteMidia(BigDecimal valorTotal, BigDecimal valorLimiteMidia) {
		if (valorLimiteMidia.compareTo(valorTotal) == -1) {
			throw new APIException("A compra excedeu o limite máximo");
		}
	}
	
	private void validarFraude(BigDecimal valorTotal, BigDecimal valorUpperLimit, Pedido pedido) {
		
		int valorFraude = valorUpperLimit.multiply(BigDecimal.TEN).compareTo(valorTotal);
		
		if (valorFraude == 0 || valorFraude == 1) {
			fraudeService.notificarFraude(pedido);
			return;
		}
		
		if (valorUpperLimit.compareTo(valorTotal) == -1) {
			fraudeService.notificarSuspeitaDeFraude(pedido);
			return;
		}
	}

}
