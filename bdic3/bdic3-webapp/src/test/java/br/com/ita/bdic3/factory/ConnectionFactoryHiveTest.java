package br.com.ita.bdic3.factory;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ConnectionFactoryHiveTest {
	
	private ConnectionFactoryHive connectionFactoryHive;

	@Test
	@Ignore
	public void testConectar() throws SQLException {
		
	    //connectionFactoryHive = new ConnectionFactoryHive();
		@SuppressWarnings("static-access")
		Statement stmt = connectionFactoryHive.getConnection();

		ResultSet rs = stmt.executeQuery("SELECT * FROM cliente");
		ResultSet rs2 = stmt.executeQuery("SELECT * FROM midia");
		
		connectionFactoryHive.con.close();

	}

}
