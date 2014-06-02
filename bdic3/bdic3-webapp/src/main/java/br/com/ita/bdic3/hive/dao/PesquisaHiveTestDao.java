package br.com.ita.bdic3.hive.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ita.bdic3.factory.ConnectionFactoryHive;

@Component
public class PesquisaHiveTestDao {
	
	@Autowired
	private ConnectionFactoryHive connectionFactoryHive;

	

		public void testConectar() throws SQLException {
			

			Connection con = connectionFactoryHive.getConnection();

			Statement stmt = con.createStatement();
			stmt.executeQuery("use bdic3");

			ResultSet rs = stmt.executeQuery("show tables;");


		}

}
