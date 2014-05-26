package br.com.ita.bdic3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.ita.bdic3.enums.EstabelecimentoTipo;

@Entity
@Table(name = "estabelecimento")
public class Estabelecimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "etb_id")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "etb_tipo")
	private EstabelecimentoTipo tipo;
	
	@Column(name = "etb_nome")
	private String nome;
	
	@Column(name = "etb_cnpj")
	private String cnpj;
	
//	@OneToOne(mappedBy = "id", cascade = CascadeType.ALL)
//	private EnderecoEstabelecimento endereco;
	
	@Column(name = "etb_email")
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EstabelecimentoTipo getTipo() {
		return tipo;
	}

	public void setTipo(EstabelecimentoTipo tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}