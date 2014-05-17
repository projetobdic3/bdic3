package br.ita.bdic3.entity;

import java.util.Calendar;

public class Sessao {

	private Long id;
//	ses_id	int(11) PK
	
	private Calendar data;
//	ses_data	varchar(45) 

	private Cliente cliente;
//	cli_id	int(11) PK 

	private SistemaOperacional sistemaOperacional;
//	sis_id	int(11) PK 
	
	private Navegador navegador;
//	nav_id	int(11) PK 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public SistemaOperacional getSistemaOperacional() {
		return sistemaOperacional;
	}

	public void setSistemaOperacional(SistemaOperacional sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}

	public Navegador getNavegador() {
		return navegador;
	}

	public void setNavegador(Navegador navegador) {
		this.navegador = navegador;
	}
}