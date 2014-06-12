package br.ita.bdic3.testes.us62;

import static org.junit.Assert.*;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

/**
 * Busca dados no Hive via JDBC.
 * Para executar é necessário ter no classpath /usr/lib/hive/lib/*.jar e /usr/lib/hadoop/*.jar
 * O driver hive-jdbc conecta-se no Thrift (HiveServer2). 
 * Para iniciar o Thrift (HiveServer2) execute: sudo hive --service hiveserver
 * Certifique-se que está no ar com: sudo netstat -tulpn | grep 10000
 * (deve retornar algo como: tcp  0  0 0.0.0.0:10000  0.0.0.0:*  LISTEN  4862/java)    
 *
 * @author
 * Airton Lastori
 */
public class HiveJdbcTest {

	  static final String driver = "org.apache.hadoop.hive.jdbc.HiveDriver";
	  static final int LIMIT = 10;

	  static final String hiveHostIp = "localhost";
	  static final String hiveServerPort = "10000";
	  static final String hiveUser = "";
	  static final String hivePassword = "";

	  static final String defaultDatabase = "bdic3";
	  
	  @Test
	  public void test() {
	    
		Connection con = getConnection();
		
		// queries de exemplo
	    execQuery(con, "show databases");
		execQuery(con, "show tables");
		
	  }

	  /**
	   * Cria conexão com Hive
	   * @return objeto Connection 
	   */
	  public Connection getConnection() {
	    
		// verifica se o driver está disponível no classpath
		try {
	      Class.forName(driver);
	    } catch (ClassNotFoundException e) {
	      fail("Verificar se o driver está disponível no classpath! " + e.toString());
	    }
	    
		// cria objeto de conexão com Hive
		String connString = "jdbc:hive://"+hiveHostIp+":"+hiveServerPort+"/default";
		Connection con = null;
		try {
	      con = DriverManager.getConnection(connString, hiveUser, hivePassword);
		} catch(SQLException e) {
	      String errMsg = "";
		  if(e.getErrorCode() == 0 ) {
			 errMsg += "Falha ao conectar-se com Hive! \n" +
					 	"Verifique se o Thrift está no ar (HiveServer2). \n" + 
					 	"Para iniciar o Thrift execute: sudo hive --service hiveserver \n" +
					 	"Certifique-se que está no ar com: sudo netstat -tulpn | grep " + hiveServerPort + " \n";
		  }
		  errMsg = "Falha ao criar objeto de conexão com Hive! Error Code: " + e.getErrorCode() + "\n" + 
		              errMsg + connString + e.toString(); 
	      fail(errMsg);
		}
		
		return con;
		
	  }
	  
	  /**
		 * Executa uma Query no Hive.
		 * 
		 * @param con Objeto com a conexao JDBC
		 * @param query
		 * @return Exibe o resultado da query 
		 */
	  public ResultSet execQuery(Connection con, String query) {
	    
		Statement stmt = null;
		try {
		  stmt = con.createStatement();	
		  // define máximo de linhas
		  stmt.setFetchSize(HiveJdbcTest.LIMIT);  //TODO parece não estar implementado no JDBC Hive		  
        } catch(SQLException e) {
		  fail("Falha ao criar objeto Statement! \n" + e.toString());
		}
		
		try {
		  stmt.executeQuery("use " + defaultDatabase);
        } catch(SQLException e) {
		  fail("Falha ao definir Default Database: " + defaultDatabase + e.toString());
		}

		ResultSet rs = null;
	    try {
		  rs = stmt.executeQuery(query);
		} catch(SQLException e) {
		  fail("Falha ao executar query e criar objeto ResultSet! \n" + query + e.toString());
		}
		
		// Fecha a conexão
		//stmt.close();
		
		return rs;
		
	  }  

}
