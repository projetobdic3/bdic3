package br.ita.bdic3.testes.us63;

import org.junit.Test;

import br.com.ita.bdic3.entity.FraudeTipo;

public class EnumFraudeTipoTeste {

	@Test
	public void test() {
		FraudeTipo tipo = FraudeTipo.ESTELIONATO;
		
		System.out.println("ESTELIONATO".equals(FraudeTipo.ESTELIONATO.toString()));
	}

}
