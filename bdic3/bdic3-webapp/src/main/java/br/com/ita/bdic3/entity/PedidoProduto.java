package br.com.ita.bdic3.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//@Entity
//@Table(name = "PEDIDO_HAS_PRODUTO")
public class PedidoProduto {
	
	@OneToOne(mappedBy = "ped_id")
	private Pedido pedido;
	
	@OneToOne(mappedBy = "prod_id")
	private Produto produto;
	
	@Column(name = "ped_has_prod_qtd")
	private Integer quantidade;
	
	@Column(name = "ped_has_prod_preco_unit_reg")
	private BigDecimal precoUnitario;

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
}
