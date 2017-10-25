package web.eng.recipes.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao {

	Connection con;
	private boolean isConnON = false;

	private final String URL = "jdbc:mysql://localhost:3306/recipes_app";
	private final String USER = "root";
	private final String PASS = "123";

	protected void open() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		if (isConnON == false) {
			try {
				con = DriverManager.getConnection(URL, USER, PASS);
				isConnON = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void close() {
		if (isConnON == true) {
			try {
				con.close();
				isConnON = false;
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
}
