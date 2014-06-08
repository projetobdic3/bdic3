package br.ita.bdic3.testes.us64;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Ignore;
import org.junit.Test;

public class ConexaoHiveTest {

	static String sql = "SELECT tra_data, tra_total "
			+ "FROM transacao "
			+ "WHERE tra_data = '2013-01-10 08:35:56' "
			+ "AND tra_total = 597.743 " 
			+ "LIMIT 10";

	@Test
	@Ignore
	public void testConectar() throws SQLException {
		try {
			Class.forName("org.apache.hadoop.hive.jdbc.HiveDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = DriverManager.getConnection("jdbc:hive://localhost:10000/bdic3", "", "");

		Statement stmt = con.createStatement();

		stmt.executeQuery("use bdic3");

		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			System.out.println(rs.getString("tra_data") + " - "	+ rs.getString("tra_total"));
		}
	}

}
