package br.ita.bdic3.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.ita.bdic3.enums.TelefoneTipo;

@Entity
@Table(name = "Telefone")
public class Telefone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tel_id")
	private Long id;
	
	private Cliente cliente;
	
	private String telefone;

	private TelefoneTipo tipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public TelefoneTipo getTipo() {
		return tipo;
	}

	public void setTipo(TelefoneTipo tipo) {
		this.tipo = tipo;
	}
}
