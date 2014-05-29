package br.ita.bdic3.testes.us08;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.Hours;
import org.junit.Ignore;
import org.junit.Test;

public class ConexaoHiveTest {

	private List<Transacao> transacoesSuspeitas = new ArrayList<Transacao>();

	static String sql = "SELECT c.cli_id, t.tra_data_hora, l.loc_latitude, l.loc_longitude "
			+ "FROM transacao2 t "
			+ "INNER JOIN localidade l ON t.loc_id = l.loc_id "
			+ "INNER JOIN cliente c ON t.cli_id = c.cli_id "
			+ "ORDER BY c.cli_id ASC, t.tra_data_hora DESC " + " LIMIT 100000";

	static Integer kmPorHoraAceitavel = 1000;

	@Test
	@Ignore
	public void testConectar() throws SQLException {
		try {
			Class.forName("org.apache.hadoop.hive.jdbc.HiveDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = DriverManager.getConnection(
				"jdbc:hive://localhost:10000/bdic3", "", "");

		Statement stmt = con.createStatement();
		stmt.executeQuery("use bdic3");

		ResultSet rs = stmt.executeQuery(sql);

		Transacao atual = null;
		Transacao proxima = null;

		if (rs.next()) {
			atual = new Transacao(rs);
		}

		int count = 0;
		int countSuspeitas = 0;
		while (rs.next()) {
			count++;
			proxima = new Transacao(rs);

			// pula para a proxima iteracao,
			// caso as transacoes sejam referentes a clientes distintos
			if (atual.getCli_id() != proxima.getCli_id()) {
				atual = proxima;
				continue;
			}
			
			// calcula a distancia entre duas coordenadas
			Double distanciaEntreCoordenadas = Haversine
					.distanciaEntreCoordenadasEmKm(atual.getLoc_latitude(),
							atual.getLoc_longitude(),
							proxima.getLoc_latitude(),
							proxima.getLoc_longitude());

			// calcula o periodo de horas entre duas transacoes
			Integer horasEntreTransacoes = Hours.hoursBetween(
					proxima.getTra_data_hora(), atual.getTra_data_hora())
					.getHours();

			// verifica se uma transacao e uma suspeita de fraude
			Boolean suspeita = horasEntreTransacoes * kmPorHoraAceitavel < distanciaEntreCoordenadas;

			System.out.println(atual + ", " + proxima + ", "
					+ distanciaEntreCoordenadas + ", " + horasEntreTransacoes
					+ ", " + suspeita);
			
			// adiciona a uma lista, todos os registros que podem ser uma fraude
			if (suspeita) {
				countSuspeitas++;
				transacoesSuspeitas.add(atual);
			}
			
			atual = proxima;
		}
		System.out.println(count);
		System.out.println(countSuspeitas);
	}

}
