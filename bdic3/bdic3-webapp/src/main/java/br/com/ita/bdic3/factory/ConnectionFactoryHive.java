package br.com.ita.bdic3.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

@Component
public class ConnectionFactoryHive {

	public static ConnectionFactoryHive singleton;
	
	public static Connection con;
	
	public static Statement getConnection() {
		if (singleton == null) {
			singleton = new ConnectionFactoryHive();
		}
		try {
			Class.forName("org.apache.hadoop.hive.jdbc.HiveDriver");
			con = DriverManager.getConnection(
					"jdbc:hive://localhost:10000/bdic3", "", "");
			Statement stmt = con.createStatement();
			stmt.executeQuery("use bdic3");
			return stmt;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}
