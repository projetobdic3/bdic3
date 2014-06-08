package br.com.ita.bdic3.vo;

public class CidadeVO {
	
	private String nome;
	private Float latitude;
	private Float longitude;
	
	public CidadeVO(String nome, Float latitude, Float longitude) {
		this.nome = nome;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

}
