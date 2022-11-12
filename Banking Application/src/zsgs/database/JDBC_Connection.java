package zsgs.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Connection {
	
	public static final String DRIVER_CLASS = "com.mysql.cj.jdbc.driver";     //Not Manditory for musql connector above version 8.
	public static final String URL = "jdbc:mysql://localhost:3306/bank_of_zsgs";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "Incorrectmys24@!";

	Connection con = null;

	private Connection getConnection() {

		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.err.println("Sorry Unable to Connect.");
		}
		return con;
	}

	public void closer() {

		try {
			con.close();
		} catch (Exception e) {
			System.err.println("Unable to close your Database.");
		}
	}

	public Connection connector() {

		return getConnection();
	}
}