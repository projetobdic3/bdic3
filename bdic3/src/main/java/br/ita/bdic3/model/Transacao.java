package br.ita.bdic3.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "TRANSACAO")
@NamedQuery(name = "Transacao.findAll", query = "SELECT t FROM Transacao t")
public class Transacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "ano_validade")
	private String anoValidade;

	private String bandeira;

	@Column(name = "codigo_seguranca")
	private String codigoSeguranca;

	@Column(name = "data_hora")
	private Timestamp dataHora;

	@Column(name = "identificacao_receita_federal")
	private String identificacaoReceitaFederal;

	private double latitude;

	private double longitude;

	@Column(name = "meio_pagamento")
	private String meioPagamento;

	@Column(name = "mes_validade")
	private String mesValidade;

	private String moeda;

	@Column(name = "numero_cartao")
	private String numeroCartao;

	private int parcelas;

	private String senha;

	@Column(name = "tipo_operacao")
	private String tipoOperacao;

	private double valor;

	public Transacao() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnoValidade() {
		return this.anoValidade;
	}

	public void setAnoValidade(String anoValidade) {
		this.anoValidade = anoValidade;
	}

	public String getBandeira() {
		return this.bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public String getCodigoSeguranca() {
		return this.codigoSeguranca;
	}

	public void setCodigoSeguranca(String codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}

	public Timestamp getDataHora() {
		return this.dataHora;
	}

	public void setDataHora(Timestamp dataHora) {
		this.dataHora = dataHora;
	}

	public String getIdentificacaoReceitaFederal() {
		return this.identificacaoReceitaFederal;
	}

	public void setIdentificacaoReceitaFederal(
			String identificacaoReceitaFederal) {
		this.identificacaoReceitaFederal = identificacaoReceitaFederal;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getMeioPagamento() {
		return this.meioPagamento;
	}

	public void setMeioPagamento(String meioPagamento) {
		this.meioPagamento = meioPagamento;
	}

	public String getMesValidade() {
		return this.mesValidade;
	}

	public void setMesValidade(String mesValidade) {
		this.mesValidade = mesValidade;
	}

	public String getMoeda() {
		return this.moeda;
	}

	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}

	public String getNumeroCartao() {
		return this.numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public int getParcelas() {
		return this.parcelas;
	}

	public void setParcelas(int parcelas) {
		this.parcelas = parcelas;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipoOperacao() {
		return this.tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return String.valueOf(this.id);
	}

}