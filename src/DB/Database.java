package DB;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class Database {
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		String hostName = "localhost";
		String database = "qlsieuthi";
		String userName = "root";
		String password = "";
		return getConnection(hostName, database, userName, password);
	}

	
	public static Connection getConnection(String hostName, String database,
			String userName, String password) throws ClassNotFoundException, SQLException {
	
		Class.forName("com.mysql.cj.jdbc.Driver");
		String connectionURL = "jdbc:mysql://" + hostName + ":/qlsieuthi";

		Connection conn = DriverManager.getConnection(connectionURL, userName, password);
		return conn;
	}
	
}

