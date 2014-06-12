package br.com.ita.bdic3.fixture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.ita.bdic3.vo.CidadeVO;
import br.com.ita.bdic3.vo.EstadoVO;

public class LocalidadeFixture {

	private static List<EstadoVO> estados;

	static {
		estados = new ArrayList<EstadoVO>();
		estados.add(getSP());
		estados.add(getMG());
		estados.add(getRJ());
	}

	public static EstadoVO getSP() {
		EstadoVO sp = new EstadoVO("SP", "São Paulo", getCidadesSP());
		return sp;
	}

	private static List<CidadeVO> getCidadesSP() {
		List<CidadeVO> cidades = new ArrayList<CidadeVO>();
		cidades.add(new CidadeVO("Jacareí", -23.305f, -45.966f));
		cidades.add(new CidadeVO("São José dos Campos", -23.179f, -45.887f));
		return cidades;
	}

	public static EstadoVO getMG() {
		EstadoVO mg = new EstadoVO("MG", "Minas Gerais", getCidadesMG());
		return mg;
	}

	private static List<CidadeVO> getCidadesMG() {
		List<CidadeVO> cidades = new ArrayList<CidadeVO>();
		cidades.add(new CidadeVO("Belo Horizonte", -19.817f, -43.956f));
		cidades.add(new CidadeVO("Juiz de Fora", -21.764f, -43.35f));
		return cidades;
	}

	public static EstadoVO getRJ() {
		EstadoVO rj = new EstadoVO("RJ", "Rio de Janeiro", getCidadesRJ());
		return rj;
	}

	private static List<CidadeVO> getCidadesRJ() {
		List<CidadeVO> cidades = new ArrayList<CidadeVO>();
		cidades.add(new CidadeVO("Rio de Janeiro", -22.903f, -43.208f));
		cidades.add(new CidadeVO("Rezende", -22.469f, -44.447f));
		return cidades;
	}

	public static List<EstadoVO> getEstadosComCidades() {
		return estados;
	}

	public static List<CidadeVO> getCidadesPorSiglaEstado(String sigla) {
		switch (sigla) {
		case "SP":
			return getCidadesSP();
		case "RJ":
			return getCidadesRJ();
		case "MG":
			return getCidadesMG();
		default:
			return Collections.emptyList();
		}
	}

	public static List<CidadeVO> getTodasCidades() {
		List<CidadeVO> cidades = new ArrayList<CidadeVO>();

		for (EstadoVO estado : estados) {
			cidades.addAll(getCidadesPorSiglaEstado(estado.getSigla()));
		}

		return cidades;
	}

	public static CidadeVO getCidadePorNome(String nome) {

		for (CidadeVO cid : getTodasCidades()) {
			if (cid.getNome().equalsIgnoreCase(nome)) {
				return cid;
			}
		}

		return null;
	}

}
