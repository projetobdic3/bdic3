package br.com.ita.bdic3.entity;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pagamento")
public class Pagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pag_id")
	private Long id;
	
	@Column(name = "pag_forma_pagamento")
	@Enumerated(EnumType.STRING)
	private FormaPagamento formaPagamento;
	
	@Column(name = "pag_parcelas")
	private Integer parcelas;
	
	@Column(name = "pag_dt")
	private Calendar dt;
	
	@Column(name = "pag_valor_pagamento")
	private BigDecimal valorPagamento;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "loc_id")
	private Localidade localidade;
	
	@OneToOne
	@JoinColumn(name = "ped_id", updatable = false, insertable = false)
	private Pedido pedido;
	
	@OneToOne
	@JoinColumn(name = "cdc_id")
	private CartaoCredito cartaoCredito;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Integer getParcelas() {
		return parcelas;
	}

	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
	}

	public Calendar getDt() {
		return dt;
	}

	public void setDt(Calendar dt) {
		this.dt = dt;
	}

	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public CartaoCredito getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(CartaoCredito cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}
}