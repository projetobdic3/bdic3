package br.ita.bdic3.testes.us62;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class TransacoesSuspeitasValorTest {
	
	@Test
	public void test() {
	
		String limiteMaior = "990.00";
		String sql1 = "SELECT fra.fra_id, tra.tra_id, etb.etb_nome, cli.cli_nome, tra.tra_total, tra.tra_data_hora " + 
						"FROM fraude fra " +
						"JOIN transacao tra ON (fra.tra_id = tra.tra_id) " + 
						"JOIN cliente cli ON (tra.cli_id = cli.cli_id) " +
						"JOIN estabelecimento etb ON (tra.etb_id = etb.etb_id) " + 
						"WHERE (tra.tra_total > " + limiteMaior + ") " +
						"LIMIT " + HiveJdbcTest.LIMIT;
		
		//executa sql1
		HiveJdbcTest hiveTest = new HiveJdbcTest();
		Connection con = hiveTest.getConnection();
		ResultSet rs = hiveTest.execQuery(con, sql1);
			
		//verifica se retorna o número de registros esperados da sql1
		int cont1 = 0;
		try {
			while (rs.next()) {
				cont1++;
			}
			assertTrue(cont1 == 10);
			//rs.first(); //retornaria para primeiro registro, porém gera SQLException: Method not supported. Ainda não implementado no Hive JDBC!
		} catch(SQLException e) {
		  fail("Falha ao iterar no ResultSet! Iteracao: " + cont1 + "\n Query: " + sql1 + e.toString());
		}
		
		//lista resultados - depende do rs.first(), porém ainda não implementado no JDBC Hive
		//HiveRelatorioTest hiveRel = new HiveRelatorioTest();
		//hiveRel.printResultQuery(rs);
			
	}
}
