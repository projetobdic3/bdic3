package br.com.ita.bdic3.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "fraude")
public class Fraude {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fra_id")
	private Long id;

	@Column(name = "fra_nome")
	private String nome;

	@Column(name = "fra_tipo")
	private FraudeTipo tipo;

	@Column(name = "fra_forma_deteccao")
	private String formaDeteccao;

	@Column(name = "fra_data_deteccao")
	private Date dataDeteccao;
	
	@ManyToOne
	@JoinColumn(name = "tra_id")
	private Transacao transacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public FraudeTipo getFraudeTipo() {
		return tipo;
	}

	public void setFraudeTipo(FraudeTipo tipo) {
		this.tipo = tipo;
	}

	public String getFormaDeteccao() {
		return formaDeteccao;
	}

	public void setFormaDeteccao(String formaDeteccao) {
		this.formaDeteccao = formaDeteccao;
	}

	public Transacao getTransacao() {
		return transacao;
	}

	public void setTransacao(Transacao transacao) {
		this.transacao = transacao;
	}

	public void setData(Date dataDeteccao) {
		this.dataDeteccao = dataDeteccao;
	}
	
	public Date getData(){
		return this.dataDeteccao;
	}

	public FraudeTipo getTipo() {
		return tipo;
	}

	public void setTipo(FraudeTipo tipo) {
		this.tipo = tipo;
	}

}
