package br.com.ita.bdic3.entity;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ped_id")
	private Long id;
	
	@Column(name = "ped_dt")
	private Calendar data;

	@ManyToOne
	@JoinColumn(name = "cli_id")
	private Cliente cliente;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "pedido" )
	private List<PedidoHasProduto> produtos;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy= "pedido")
	private Pagamento pagamento;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "tra_id")
	private Transacao transacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<PedidoHasProduto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<PedidoHasProduto> produtos) {
		for (PedidoHasProduto pedHas : produtos){
			pedHas.setPedido(this);
		}
		this.produtos = produtos;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Transacao getTransacao() {
		return transacao;
	}

	public void setTransacao(Transacao transacao) {
		this.transacao = transacao;
	}

	public BigDecimal getValorTotal() {
		BigDecimal valorTotal = BigDecimal.ZERO;
		List<PedidoHasProduto> produtos = getProdutos();
		
		for (PedidoHasProduto produto : produtos) {
			valorTotal = valorTotal.add(
							produto.getPreco().multiply(
								new BigDecimal(produto.getQuantidade()))); 
		}
		
		return valorTotal;
	}
}
