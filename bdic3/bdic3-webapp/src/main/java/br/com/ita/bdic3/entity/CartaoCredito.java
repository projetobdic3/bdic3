package br.com.ita.bdic3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cartao_credito")
public class CartaoCredito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cdc_id")
	private Long id;
	
	@Column(name = "cdc_numero")
	private String numero;
	
	@Column(name = "cdc_codigo")
	private String codigo;
	
	@Column(name = "cdc_val_mes")
	private String validadeMes;
	
	@Column(name = "cdc_val_ano")
	private String validadeAno;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "cdc_bandeira")
	private BandeiraCartao bandeira;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getValidadeMes() {
		return validadeMes;
	}

	public void setValidadeMes(String validadeMes) {
		this.validadeMes = validadeMes;
	}

	public String getValidadeAno() {
		return validadeAno;
	}

	public void setValidadeAno(String validadeAno) {
		this.validadeAno = validadeAno;
	}

	public BandeiraCartao getBandeira() {
		return bandeira;
	}

	public void setBandeira(BandeiraCartao bandeira) {
		this.bandeira = bandeira;
	}

}
