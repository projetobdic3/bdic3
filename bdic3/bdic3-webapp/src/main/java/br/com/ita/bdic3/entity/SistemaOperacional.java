package br.com.ita.bdic3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SISTEMA_OPERACIONAL")
public class SistemaOperacional {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "sis_id")
	private Long id;
	
	@Column(name = "sis_nome")
	private String nome;

	@Column(name = "sys")
	private String versao;
	
	@Column(name = "sis_movel") 
	private String movel;

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

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public String getMovel() {
		return movel;
	}

	public void setMovel(String movel) {
		this.movel = movel;
	}
}