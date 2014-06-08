package br.com.ita.bdic3.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.ita.bdic3.enums.Sexo;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = -6199970951870939653L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cli_id")
	private Long id;
	
	@Column(name = "cli_nome")
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "cli_sexo")
	private Sexo sexo;
	
	@Column(name = "cli_dt_nasc")
	private String datanascimento;
	
	@Column(name = "cli_upper_limit")
	private BigDecimal upperLimit;

	public Cliente(Long id) {
		this.id = id;
	}

	public Cliente() { }

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

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(String datanascimento) {
		this.datanascimento = datanascimento;
	}

	public BigDecimal getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(BigDecimal upperLimit) {
		this.upperLimit = upperLimit;
	}
}