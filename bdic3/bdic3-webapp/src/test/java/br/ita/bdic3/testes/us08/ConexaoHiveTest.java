package br.ita.bdic3.testes.us08;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.Hours;
import org.junit.Test;

public class ConexaoHiveTest {

	private List<Transacao> transacoesSuspeitas = new ArrayList<Transacao>();
	
	static String sql = "SELECT c.cli_id, t.tra_data_hora, l.loc_latitude, l.loc_longitude "
			+ "FROM transacao2 t "
			+ "INNER JOIN localidade l ON t.loc_id = l.loc_id "
			+ "INNER JOIN cliente c ON t.cli_id = c.cli_id "
			+ "ORDER BY c.cli_id ASC, t.tra_data_hora DESC " + "LIMIT 500";

	static Integer kmPorHoraAceitavel = 300;

	@Test
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

		while (rs.next()) {
			proxima = new Transacao(rs);

			Double distanciaEntreCoordenadas = Haversine
					.distanciaEntreCoordenadasEmKm(atual.getLoc_latitude(),
							atual.getLoc_longitude(),
							proxima.getLoc_latitude(),
							proxima.getLoc_longitude());

			Integer horasEntreTransacoes = Hours.hoursBetween(
					proxima.getTra_data_hora(), atual.getTra_data_hora())
					.getHours();

			Boolean suspeita = horasEntreTransacoes * kmPorHoraAceitavel < distanciaEntreCoordenadas;

			if (suspeita) {
				transacoesSuspeitas.add(atual);
			}

			// System.out.println(atual + "\t" + proxima + "\t" +
			// distanciaEntreCoordenadas + "\t" + horasEntreTransacoes + "\t" +
			// suspeita);

			atual = proxima;
		}

		for (Transacao transacao : transacoesSuspeitas) {
			System.out.println(transacao.toString());
		}

	}

}
