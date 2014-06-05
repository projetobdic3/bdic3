package br.com.ita.bdic3.hive.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.joda.time.Hours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ita.bdic3.factory.ConnectionFactoryHive;
import br.com.ita.bdic3.util.Haversine;
import br.com.ita.bdic3.vo.SuspeitaFraudeVO;
import br.com.ita.bdic3.util.Boxsplot;

@Component
public class AnaliseFraudesDao {

	@Autowired
	private ConnectionFactoryHive connectionFactoryHive;

	private List<SuspeitaFraudeVO> transacoesSuspeitas = new ArrayList<SuspeitaFraudeVO>();
	
	static String sqlLocalizacao = "SELECT c.cli_id, t.tra_data_hora, l.loc_latitude, l.loc_longitude "
			+ "FROM transacao t "
			+ "INNER JOIN localidade l ON t.loc_id = l.loc_id "
			+ "INNER JOIN cliente c ON t.cli_id = c.cli_id "
			+ "ORDER BY c.cli_id ASC, t.tra_data_hora DESC ";

	static Integer kmPorHoraAceitavel = 1000;


	public void fraudeLocalizacao() throws SQLException {

		ResultSet rs = getResultSet(sqlLocalizacao);

		SuspeitaFraudeVO atual = null;
		SuspeitaFraudeVO proxima = null;

		if (rs.next()) {
			atual = new SuspeitaFraudeVO(rs);
		}

		int transacao = 0;
		int transacaoSuspeita = 0;
		while (rs.next()) {
			transacao++;
			proxima = new SuspeitaFraudeVO(rs);

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
				transacaoSuspeita++;
				transacoesSuspeitas.add(atual);
			}

			atual = proxima;
		}
		System.out.println(transacao);
		System.out.println(transacaoSuspeita);
	}

	private ResultSet getResultSet(String sql) throws SQLException {
		@SuppressWarnings("static-access")
		Statement stmt = connectionFactoryHive.getConnection();

		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}

	public List<SuspeitaFraudeVO> getTransacoesSuspeitas() {
		return transacoesSuspeitas;
	}

}
