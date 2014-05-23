package br.com.ita.bdic3.enums;

public enum Sexo {
	
	M("MASCULINO"), F("FEMININO");

	private String value;
	
	private Sexo(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
