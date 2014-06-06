package br.com.ita.bdic3.factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Ignore;
import org.junit.Test;

public class ConnectionFactoryHiveTest {
	

	@Test
	@Ignore
	public void testConectar() throws SQLException {
		
	    Connection connection = new ConnectionFactoryHive().getConnection();
		Statement stmt = connection.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT * FROM cliente");
		ResultSet rs2 = stmt.executeQuery("SELECT * FROM midia");
		
		connection.close();

	}

}
