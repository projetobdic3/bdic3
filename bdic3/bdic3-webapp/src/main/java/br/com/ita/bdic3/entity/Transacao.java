package br.com.ita.bdic3.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.joda.time.DateTime;

import br.com.ita.bdic3.enums.StatusTransacao;
import br.com.ita.bdic3.enums.TransacaoTipo;

@Entity
@Table(name = "transacao")
@NamedQuery(name = "Transacao.findAll", query = "SELECT t FROM Transacao t")
public class Transacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tra_id")
	private Long id;
	
	@Enumerated
	private TransacaoTipo transacaoTipo;
	
	@Column(name = "tra_total")
	private BigDecimal total;
	
	@Column(name = "tra_data_hora")
	private DateTime data_hora;
	
	@Column(name = "tra_descricao_pagamento")
	private String descricaoPagamento;
	
	@Column(name = "tra_validade")
	private Calendar validade;
	
	@Column(name = "tra_url_pagamento")
	private String urlPagamento;
	
	@Enumerated
	private StatusTransacao status;
	
	@Column(name = "tra_quantidadeparcela")
	private int quantidadeParcelas;
	
	
//	@ManyToOne
//	private Cliente cliente;
	
//	@ManyToOne
//	private Localidade localidade;
	
//	@ManyToOne
//	private Estabelecimento estabelecimento;
	
//	@ManyToOne
//	private Midia midia;
	
//	@ManyToOne
//	private Sessao sessao;
	
//	@ManyToOne
//	private MeioPagamento meioPagamento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TransacaoTipo getTransacaoTipo() {
		return transacaoTipo;
	}

	public void setTransacaoTipo(TransacaoTipo transacaoTipo) {
		this.transacaoTipo = transacaoTipo;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getDescricaoPagamento() {
		return descricaoPagamento;
	}

	public void setDescricaoPagamento(String descricaoPagamento) {
		this.descricaoPagamento = descricaoPagamento;
	}

	public Calendar getValidade() {
		return validade;
	}

	public void setValidade(Calendar validade) {
		this.validade = validade;
	}

	public String getUrlPagamento() {
		return urlPagamento;
	}

	public void setUrlPagamento(String urlPagamento) {
		this.urlPagamento = urlPagamento;
	}

	public StatusTransacao getStatus() {
		return status;
	}

	public void setStatus(StatusTransacao status) {
		this.status = status;
	}

	public int getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(int quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}
	
	public DateTime getTra_data_hora() {
		return data_hora;
	}

	public void setTra_data_hora(DateTime data_hora) {
		this.data_hora = data_hora;
	}
	
/*
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public Midia getMidia() {
		return midia;
	}

	public void setMidia(Midia midia) {
		this.midia = midia;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public MeioPagamento getMeioPagamento() {
		return meioPagamento;
	}

	public void setMeioPagamento(MeioPagamento meioPagamento) {
		this.meioPagamento = meioPagamento;
	}*/
}