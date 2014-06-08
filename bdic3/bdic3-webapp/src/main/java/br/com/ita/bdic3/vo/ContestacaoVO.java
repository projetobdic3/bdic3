package br.com.ita.bdic3.vo;

public class ContestacaoVO {

	private Long idContestacao;
	private String nomeCliente;
	private String cpfCliente;
	private Double valorTransacao;
	private String dataTransacao;
	private Double valorInicial;
	private Double valorFinal;
	private String dataInicial;
	private String dataFinal;

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

	public Double getValorTransacao() {
		return valorTransacao;
	}

	public void setValorTransacao(Double valorTransacao) {
		this.valorTransacao = valorTransacao;
	}

	public String getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(String dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	public Double getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(Double valorInicial) {
		this.valorInicial = valorInicial;
	}

	public Double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(Double valorFinal) {
		this.valorFinal = valorFinal;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

}
