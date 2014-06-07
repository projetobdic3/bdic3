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
	@Column(name = "tra_tipo")
	private TransacaoTipo transacaoTipo;
	
	@Column(name = "tra_total")
	private BigDecimal total;
	
	@Column(name = "tra_data")
	private Calendar data;
	
	@Column(name = "tra_hora")
	private Calendar hora;

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

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Calendar getHora() {
		return hora;
	}

	public void setHora(Calendar hora) {
		this.hora = hora;
	}
}