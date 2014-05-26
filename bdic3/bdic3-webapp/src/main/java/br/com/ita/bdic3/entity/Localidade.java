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
	
	@Column(name= "loc_lat")
	private Double latitude;
	
	@Column(name= "loc_long")
	private Double longitude;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}
