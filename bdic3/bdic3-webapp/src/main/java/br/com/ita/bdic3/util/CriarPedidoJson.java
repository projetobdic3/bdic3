package br.com.ita.bdic3.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import br.com.ita.bdic3.entity.*;


public class CriarPedidoJson {
	
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		Pedido pedido = new Pedido();
		pedido.setData(Calendar.getInstance());
		
		Cliente cliente = new Cliente(1L);
		pedido.setCliente(cliente);
		
		Pagamento pagamento = new Pagamento();
		pagamento.setDt(Calendar.getInstance());
		pagamento.setFormaPagamento(FormaPagamento.CARTAO_CREDITO);
		pagamento.setParcelas(1);
		pagamento.setValorPagamento(new BigDecimal("123.00"));
		//pagamento.setPedido(pedido);
		pedido.setPagamento(pagamento);
		
		Midia cartaoCredito = new Midia();
		cartaoCredito.setId(1l);
		pagamento.setMidia(cartaoCredito);
		
		Localidade localidade = new Localidade(new Float("4321421.0432"), new Float("-2314321421.0432"));
		pagamento.setLocalidade(localidade );
		
		ArrayList<PedidoHasProduto> produtos = new ArrayList<PedidoHasProduto>();
		
		Produto produto = new Produto(1L);
		
		PedidoHasProduto pedidoHasProduto = new PedidoHasProduto();
		pedidoHasProduto.setProduto(produto);
		pedidoHasProduto.setQuantidade(10);
		pedidoHasProduto.setPreco(new BigDecimal("12.30"));
		produtos.add(pedidoHasProduto);
		
		pedido.setProdutos(produtos);
		
		ObjectMapper ow = new ObjectMapper();
		String json = ow.writeValueAsString(pedido);
		System.out.println(json);
	}

}
