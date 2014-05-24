import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class ConexaoHiveTest {

	@Test
	public void testConectar() throws SQLException {
		try {
			Class.forName("org.apache.hadoop.hive.jdbc.HiveDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = DriverManager.getConnection(
				"jdbc:hive://localhost:10000/bdic3", "", "");

		Statement stmt = con.createStatement();
		stmt.executeQuery("use bdic3");
		ResultSet rs = stmt
		.executeQuery("SELECT tra_id, tra_data, tra_hora FROM transacao ORDER BY tra_data DESC, tra_hora DESC LIMIT 50");
			

		while (rs.next()) {
			 System.out.println(rs.getInt("tra_id"));
			 System.out.println(rs.getString("tra_data"));
			 System.out.println(rs.getString("tra_hora"));
		}

	}

}
