package br.com.ita.bdic3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NAVEGADOR")
public class Navegador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nav_id")
	private Long id;
	
	@Column(name = "nav_nome")
	private String nome;
	
	@Column(name = "nav_versao")
	private String versao;

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
}
