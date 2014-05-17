package br.ita.bdic3.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.ita.bdic3.enums.Sexo;

@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = -6199970951870939653L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cli_id")
	private Long id;
	
	@Column(name = "cli_nome")
	private String nome;
	
//	@Column(name = "cli_documento")
//	private String documento;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "cli_sexo")
	private Sexo sexo;
	
	@Column(name = "cli_dt_nasc")
	private String datanascimento;
	
//	@Column(name = "cli_rg")
//	private String rg;
	
//	@Column(name = "cli_renda")
//	private String renda;
	
//	@Column(name = "cli_biometria")
//	private String biometria;
	
//	@Column(name = "cli_token")
//	private String token;
	
//	@OneToMany(mappedBy = "cli_id", cascade = CascadeType.ALL)
//	private List<EnderecoCliente> enderecos;
	
//	@OneToMany(mappedBy = "cli_id", cascade = CascadeType.ALL)
//	private List<Telefone> telefones;

//	@OneToOne(mappedBy = "id")
//	private Usuario usuario;

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
}