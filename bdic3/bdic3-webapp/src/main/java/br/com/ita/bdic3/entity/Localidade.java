package br.com.ita.bdic3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "localidade")
public class Localidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "loc_id")
	private Long id;
	
	@Column(name= "loc_latitude")
	private Float latitude;
	
	@Column(name= "loc_longitude")
	private Float longitude;
	
	@Column(name= "loc_endereco")
	private String endereco;
	
	@Column(name= "loc_cidade")
	private String cidade;
	
	@Column(name= "loc_estado")
	private String estado;
	
	@Column(name= "loc_pais")
	private String pais;
	
	@Column(name= "loc_cep")
	private String cep;

	public Localidade() {
		super();
	}

	public Localidade(Float lat, Float lng) {
		this.latitude = lat;
		this.longitude = lng;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	
	
}
