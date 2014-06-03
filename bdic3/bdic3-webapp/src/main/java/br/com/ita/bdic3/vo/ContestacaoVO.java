package br.com.ita.bdic3.vo;

public class ContestacaoVO {
	
	private Long idContestacao;
	private String nomeCliente;
	private String cpfCliente;

	public Long getIdContestacao() {
		return idContestacao;
	}

	public void setIdContestacao(Long idContestacao) {
		this.idContestacao = idContestacao;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

}
