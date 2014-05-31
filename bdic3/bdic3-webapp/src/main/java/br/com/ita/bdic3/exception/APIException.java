package br.com.ita.bdic3.exception;


/** 
 * Classe responsável pela resposta de erro ocorridos na API.
 */
public class APIException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	 
	private String codigo;
	private String mensagem;
	
	public APIException(String codigo, String mensagem) {
		super(mensagem);
		this.codigo = codigo;
		this.mensagem = mensagem;
	}
	
	public APIException(String mensagem) {
		this.mensagem = mensagem;
	}
 
	public String getErrCode() {
		return codigo;
	}
 
	public void setErrCode(String errCode) {
		this.codigo = errCode;
	}
 
	public String getErrMsg() {
		return mensagem;
	}
 
	public void setErrMsg(String errMsg) {
		this.mensagem = errMsg;
	}
}