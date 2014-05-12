package br.com.ita.bdic3.entity;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PAGAMENTO")
public class Pagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pag_id")
	private Long id;
	
	@Column(name = "pag_forma_pagamento")
	private FormaPagamento formaPagamento;
	
	@Column(name = "pag_parcelas")
	private Integer parcelas;
	
	@Column(name = "pag_dt")
	private Calendar dt;
	
	@Column(name = "pag_valor_pagamento")
	private BigDecimal valorPagamento;
	
//	@OneToOne(mappedBy = "loc_id")
//	private Localidade localidade;
	
//	@Column(name = "cdc_id")
//	private Cliente cliente;
	
//	@Column(name = "ped_id")
//	private Pedido pedido;

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
}