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
		cidades.add(new CidadeVO("Jacareí", 123f, 321f));
		cidades.add(new CidadeVO("São José dos Campos", 1221f, 12321f));
		return cidades;
	}

	public static EstadoVO getMG() {
		EstadoVO mg = new EstadoVO("MG", "Minas Gerais", getCidadesMG());
		return mg;
	}
	
	private static List<CidadeVO> getCidadesMG() {
		List<CidadeVO> cidades = new ArrayList<CidadeVO>();
		cidades.add(new CidadeVO("Belo Horizonte", 2311123f, 313321f));
		cidades.add(new CidadeVO("Juiz de Fora", 29823f, 3177821f));
		return cidades;
	}

	public static EstadoVO getRJ() {
		EstadoVO rj = new EstadoVO("RJ", "Rio de Janeiro", getCidadesRJ());
		return rj;
	}

	private static List<CidadeVO> getCidadesRJ() {
		List<CidadeVO> cidades = new ArrayList<CidadeVO>();
		cidades.add(new CidadeVO("Rio de Janeiro", 41112331f, 7121221f));
		cidades.add(new CidadeVO("Rezende", 1134112f, 1441222f));
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
			if (cid.getNome().equalsIgnoreCase(nome)){
				return cid;
			}
		}
		
		return null;
	}

}
