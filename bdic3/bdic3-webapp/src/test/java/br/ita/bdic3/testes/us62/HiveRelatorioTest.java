package br.ita.bdic3.testes.us62;

import static org.junit.Assert.*;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;


public class HiveRelatorioTest {
	
	static final String DELIMITER = " | ";
	
	@Test
	public void test() {
	
		HiveJdbcTest hive = new HiveJdbcTest();
		ResultSet rs = hive.execQuery(hive.getConnection(), "desc transacao");
		
	    printCol(rs);
	    printResultQuery(rs);
	    
	}
	
	/**
	 * Lista nomes das colunas
	 * 
	 * @param rs Objeto com a ResultSet. 
	 *  
	 */
	public void printCol(ResultSet rs) {
		int columnsNumber = -1;
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			columnsNumber = rsmd.getColumnCount();
		    for(int i=1; i<=columnsNumber; i++) {
		      System.out.print(rsmd.getColumnName(i));
		      System.out.print(HiveRelatorioTest.DELIMITER);
			}
			System.out.println();
		} catch (SQLException e) {
			fail("Erro ao listar o nome das colunas! \n" + e.toString());
		}
	}
	
	/**
	 * Lista resultado da query
	 * 
	 * @param rs Objeto com a ResultSet. 
	 *  
	 */
	public void printResultQuery(ResultSet rs) {
		
		int columnsNumber = -1;
		
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			columnsNumber = rsmd.getColumnCount();
			int count = 1;
			while (rs.next()) {
			  for(int i=1; i<=columnsNumber; i++) {
				System.out.print(rs.getString(i));
				System.out.print(HiveRelatorioTest.DELIMITER);
			  }
			  System.out.println();
			  count++;
		    }
			System.out.print("rows#"+count);
			System.out.println(" columns#"+columnsNumber);
		} catch (SQLException e) {
			fail("Erro ao listar o resultado da query! \n" + e.toString());
		}
		
	}	
	
}
