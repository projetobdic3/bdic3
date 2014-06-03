package br.com.ita.bdic3.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="pedido_has_produto")
public class PedidoHasProduto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ped_has_prod_id")
	private Long id;
	
	@OneToOne
	@JoinColumn(name="ped_id", insertable=true , updatable=true)
	private Pedido pedido;
	
	@OneToOne
	@JoinColumn(name="prd_id", insertable=true , updatable=true)
	private Produto produto;
	
	@Column(name="ped_has_prod_qtd")
	private Integer quantidade;
	
	@Column(name="ped_has_prod_preco_unit_reg")
	private BigDecimal preco;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

}
