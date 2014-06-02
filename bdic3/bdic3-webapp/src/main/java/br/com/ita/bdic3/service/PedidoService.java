package br.com.ita.bdic3.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ita.bdic3.dao.MidiaDao;
import br.com.ita.bdic3.dao.PedidoDao;
import br.com.ita.bdic3.dao.ProdutoDao;
import br.com.ita.bdic3.entity.Cliente;
import br.com.ita.bdic3.entity.FormaPagamento;
import br.com.ita.bdic3.entity.Localidade;
import br.com.ita.bdic3.entity.Midia;
import br.com.ita.bdic3.entity.Pagamento;
import br.com.ita.bdic3.entity.Pedido;
import br.com.ita.bdic3.entity.PedidoHasProduto;
import br.com.ita.bdic3.entity.Produto;
import br.com.ita.bdic3.exception.APIException;
import br.com.ita.bdic3.vo.PedidoVO;

@Component
public class PedidoService {

	@Autowired
	private PedidoDao pedidoDao;
	
	@Autowired
	private MidiaDao midiaDao;
	
	@Autowired
	private ProdutoDao produtoDao;
	
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
		pedido.setProdutos(produtos);
		
		Cliente cliente = new Cliente(pedidoVO.getIdCliente());
		
		Pagamento pagamento = new Pagamento();
		pagamento.setDt(dataAtual);
		pagamento.setFormaPagamento(FormaPagamento.valueOf(pedidoVO.getFormaPagamento()));
		pagamento.setLocalidade(new Localidade(pedidoVO.getLatitude(), pedidoVO.getLongitude()));
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
		validarPedido(pedido);
		pedidoDao.save(pedido);
	}
	
	private void validarPedido(Pedido pedido) throws APIException {
		BigDecimal valorTotal = pedido.getValorTotal();
		
		Midia midia = midiaDao.findById(pedido.getPagamento().getMidia().getId());
		
		if (midia != null && midia.getValorMaximo().compareTo(valorTotal) == -1) {
			throw new APIException("A compra excedeu o limite máximo");
		}
	}
}
