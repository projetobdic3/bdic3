package br.com.ita.bdic3.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prd_id")
	private Long id;
	
	@Column(name = "prd_nome")
	private String nome;
	
	@Column(name = "prd_preco_normal")
	private BigDecimal precoNormal;
	
	public Produto() {
		super();
	}

	public Produto(Long id) {
		this.id = id;
	}

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

	public BigDecimal getPrecoNormal() {
		return precoNormal;
	}

	public void setPrecoNormal(BigDecimal precoNormal) {
		this.precoNormal = precoNormal;
	}
}
