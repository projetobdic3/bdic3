package br.ita.bdic3.testes.us62;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class ConsultaIntervaloDatasTest {
	
	@Test
	public void test() {
	
		String dataInicial = "2013-02-02";
		String dataFinal = "2013-02-08";
		String sql1 = "SELECT tra.tra_id, etb.etb_nome, cli.cli_nome, tra.tra_total, tra.tra_data_hora " +
				"FROM transacao tra " + 
				"JOIN cliente cli ON (tra.cli_id = cli.cli_id) " + 
				"JOIN estabelecimento etb ON (tra.etb_id = etb.etb_id) " + 
				"WHERE (tra.tra_data_hora BETWEEN unix_timestamp('" + dataInicial + " 00:00:00') AND unix_timestamp('" + dataFinal + " 23:59:59')) " +
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
			
	}
}
