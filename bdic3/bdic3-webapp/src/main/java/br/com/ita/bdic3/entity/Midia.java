package br.com.ita.bdic3.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MIDIA")
public class Midia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mid_id")
	private  Long id;
	
	@Column(name = "mid_name")
	private  String nome;
	
	@Column(name = "mid_description")
	private String descricao;
	
	@Column(name = "mid_number")
	private String numero;
	
	@Column(name = "mid_diafatura")
	private Integer diaFatura;
	
	@Column(name = "mid_validademes")
	private Integer validadeMes;
	
	@Column(name = "mid_validadeano")
	private Integer validadeAno;
	
	@Column(name = "mid_valormaximo")
	private BigDecimal  valorMaximo;
	
	@Column(name = "mid_codigoseguranca")
	private String codigoSeguranca;

//	@OneToOne(mappedBy = "odc_id")
//	private  OperadoraCartao operadoraCartao;
	
//	@OneToOne(mappedBy = "cli_id")
//	private  Cliente cliente;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Integer getDiaFatura() {
		return diaFatura;
	}

	public void setDiaFatura(Integer diaFatura) {
		this.diaFatura = diaFatura;
	}

	public Integer getValidadeMes() {
		return validadeMes;
	}

	public void setValidadeMes(Integer validadeMes) {
		this.validadeMes = validadeMes;
	}

	public Integer getValidadeAno() {
		return validadeAno;
	}

	public void setValidadeAno(Integer validadeAno) {
		this.validadeAno = validadeAno;
	}

	public BigDecimal getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(BigDecimal valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	public String getCodigoSeguranca() {
		return codigoSeguranca;
	}

	public void setCodigoSeguranca(String codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}

}
