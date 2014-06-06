package br.ita.bdic3.hive.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ita.bdic3.vo.ContestacaoVO;
import br.ita.bdic3.hive.ConexaoHive;

public class ContestacaoDao {

	ConexaoHive con = new ConexaoHive();

	public boolean findTransacaoByValorOrData(ContestacaoVO contestacaoVO) {
		try {
			Statement stmt = con.getConnection().createStatement();

			String sql = "SELECT tra_data, tra_total " 
					+ "FROM transacao "
					+ "WHERE tra_data = '" + contestacaoVO.getDataTransacao() + "' " 
					+ "AND tra_total = " + contestacaoVO.getValorTransacao() + " " 
					+ "LIMIT 1000";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}
	
	public List<ContestacaoVO> findTransacaoByIntervaloDataAndIntervaloValor(ContestacaoVO contestacaoVO) {
		List<ContestacaoVO> contestacoes = new ArrayList<ContestacaoVO>();
		try {
			Statement stmt = con.getConnection().createStatement();

			// bug na query e nos campos do rs
			String sql = "SELECT * " 
						+ "FROM transacao "
						+ "INNER JOIN cliente c "
						+ "ON c.cli_id = transacao.cli_id"
						+ "WHERE tra_data > '" + contestacaoVO.getDataInicial() + "' " 
						+ "OR tra_data < '" + contestacaoVO.getDataFinal() + "' " 
						+ "AND tra_total > " + contestacaoVO.getValorInicial() + " " 
						+ "OR tra_total < " + contestacaoVO.getValorFinal() + " "
						+ "LIMIT 1000";
			 
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()) {
				ContestacaoVO contestacao = new ContestacaoVO();
				
				contestacao.setNomeCliente(rs.getString("c.cli_nome"));
				contestacao.setCpfCliente(rs.getString("c.cli_cpf"));
				contestacao.setValorTransacao(rs.getDouble("transacao.tra_total"));
				contestacao.setDataTransacao(rs.getString("transacao.tra_data"));
				
				contestacoes.add(contestacao);
			} 
			
			return contestacoes;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
