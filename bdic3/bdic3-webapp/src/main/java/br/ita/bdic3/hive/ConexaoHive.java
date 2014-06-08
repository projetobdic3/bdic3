package br.ita.bdic3.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoHive {
	
	public Connection getConnection() throws SQLException {
		try {
			Class.forName("org.apache.hadoop.hive.jdbc.HiveDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = DriverManager.getConnection("jdbc:hive://localhost:10000/bdic3", "", "");

		Statement stmt = con.createStatement();

		stmt.executeQuery("use bdic3");

		return con;
	}
	
}
