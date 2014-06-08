package br.com.ita.bdic3.hive.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.Hours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ita.bdic3.factory.ConnectionFactoryHive;
import br.com.ita.bdic3.util.Haversine;
import br.com.ita.bdic3.vo.PesquisaHiveVO;
import br.com.ita.bdic3.vo.SuspeitaFraudeVO;

@Component
public class AnaliseFraudesDao {

	@Autowired
	private ConnectionFactoryHive connectionFactoryHive;
	
	static Integer kmPorHoraAceitavel = 1000;

	public List<SuspeitaFraudeVO> fraudeLocalizacao(PesquisaHiveVO pesquisaHiveVO) {
		List<SuspeitaFraudeVO> transacoesSuspeitas = new ArrayList<SuspeitaFraudeVO>();
		Connection connection = connectionFactoryHive.getConnection();
		
		try{
		ResultSet rs = getResultSet(connection, pesquisaHiveVO);
		

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
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return transacoesSuspeitas;
	}

	private ResultSet getResultSet(Connection con, PesquisaHiveVO pesquisaHiveVO) throws SQLException {
		Statement  stmt = con.createStatement();
		stmt.executeQuery("use bdic3"); 
		ResultSet rs = stmt.executeQuery(getSql(pesquisaHiveVO));
		return rs;
	}
	
	private String getSql(PesquisaHiveVO pesquisaHiveVO){
		String sqlLocalizacao = "SELECT c.cli_id, t.tra_data_hora, l.loc_latitude, l.loc_longitude, l.loc_cidade, t.tra_total "
				+ "FROM transacao t "
				+ "INNER JOIN localidade l ON (t.loc_id = l.loc_id) "
				+ "INNER JOIN cliente c ON (t.cli_id = c.cli_id) ";
				
		String separador = " WHERE ";
		
		if(pesquisaHiveVO.hasValorInicial()){
			sqlLocalizacao += separador + "t.tra_total >= '" + pesquisaHiveVO.getValorInicialConvertido() + "'";
			separador = " AND ";
		}
		if(pesquisaHiveVO.hasValorFinal()){
			sqlLocalizacao += separador + "t.tra_total <= '" + pesquisaHiveVO.getValorFinalConvertido() + "'";
			separador = " AND ";
		}
		if(pesquisaHiveVO.hasDataInical()){
			sqlLocalizacao += separador + "t.tra_data_hora >=  unix_timestamp('" + pesquisaHiveVO.getDataIncialConvertida() + "')";
			separador = " AND ";
		}
		if(pesquisaHiveVO.hasDataFinal()){
			sqlLocalizacao += separador + "t.tra_data_hora <= unix_timestamp('" + pesquisaHiveVO.getDataFinalConvertida() + "')";
			separador = " AND ";
		}
		if(pesquisaHiveVO.hasLocalidade()){
			sqlLocalizacao += separador + "l.loc_cidade = '" + pesquisaHiveVO.getLocalidade() + "'";
		}
		sqlLocalizacao += " ORDER BY c.cli_id ASC, t.tra_data_hora DESC";
		return sqlLocalizacao;
	}

	public void setConnectionFactoryHive(ConnectionFactoryHive connectionFactoryHive) {
		this.connectionFactoryHive = connectionFactoryHive;
	}

}
