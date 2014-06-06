package br.com.ita.bdic3.factory;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.stereotype.Component;

@Component
public class ConnectionFactoryHive {
	
	public Connection getConnection(){
		try {
			Class.forName("org.apache.hadoop.hive.jdbc.HiveDriver");
			return DriverManager.getConnection("jdbc:hive://localhost:10000/bdic3", "", "");
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}

}
