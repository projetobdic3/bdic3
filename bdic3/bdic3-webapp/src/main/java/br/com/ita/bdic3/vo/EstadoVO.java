package br.com.ita.bdic3.vo;

import java.util.List;

public class EstadoVO {
	
	private String sigla;
	private String nome;
	private List<CidadeVO> cidades;
	
	public EstadoVO() {	}

	public EstadoVO(String sigla, String nome, List<CidadeVO> cidades) {
		this.sigla = sigla;
		this.nome = nome;
		this.cidades = cidades;
	}

	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<CidadeVO> getCidades() {
		return cidades;
	}

	public void setCidades(List<CidadeVO> cidades) {
		this.cidades = cidades;
	}
}