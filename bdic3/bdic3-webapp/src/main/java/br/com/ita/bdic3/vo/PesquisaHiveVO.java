package br.com.ita.bdic3.vo;

import org.apache.commons.lang.StringUtils;

import br.com.ita.bdic3.util.DateUtil;

public class PesquisaHiveVO {
	
	private String valorInicial;
	private String dataInicial;
	private String dataFinal;
	private String localidade;
	private String valorFinal;
	
	public String getValorInicial() {
		return valorInicial;
	}
	public void setValorInicial(String valorInicial) {
		this.valorInicial = valorInicial;
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
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getValorFinal() {
		return valorFinal;
	}
	public void setValorFinal(String valorFinal) {
		this.valorFinal = valorFinal;
	}
	public String getDataIncialConvertida(){
		if(hasDataInical()){
			return DateUtil.dateToHive(dataInicial);
		}
		return null;
	}
	public String getDataFinalConvertida(){
		if(hasDataFinal()){
			return DateUtil.dateToHive(dataFinal);
		}
		return null;
	}
	public Double getValorInicialConvertido(){
		if(hasValorInicial()){
			return new Double(valorInicial);
		}
		return null;
	}
	
	public Double getValorFinalConvertido(){
		if(hasValorFinal()){
			return new Double(valorFinal);
		}
		return null;
	}
	public boolean hasValorFinal(){
		if(StringUtils.isNotBlank(valorFinal)){
			return true;
		}
		return false;
	}
	
	public boolean hasValorInicial(){
		if(StringUtils.isNotBlank(valorInicial)){
			return true;
		}
		return false;
	}
	public boolean hasDataInical(){
		if(StringUtils.isNotBlank(dataInicial)){
			return true;
		}
		return false;
	}
	public boolean hasDataFinal(){
		if(StringUtils.isNotBlank(dataFinal)){
			return true;
		}
		return false;
	}
	public boolean hasLocalidade(){
		if(StringUtils.isNotBlank(localidade)){
			return true;
		}
		return false;
	}
}
