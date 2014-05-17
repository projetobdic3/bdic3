package br.ita.bdic3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CARTAO_CREDITO")
public class CartaoCredito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cdc_id")
	private Long id;
	
	@Column(name = "cdc_numero")
	private Long numero;
	
	@Column(name = "cdc_codigo")
	private Integer codigo;
	
	@Column(name = "cdc_val_mes")
	private Integer validadeMes;
	
	@Column(name = "cdc_val_ano")
	private Integer validadeAno;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "cdc_bandeira")
	private BandeiraCartao bandeira;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
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

	public BandeiraCartao getBandeira() {
		return bandeira;
	}

	public void setBandeira(BandeiraCartao bandeira) {
		this.bandeira = bandeira;
	}
}
