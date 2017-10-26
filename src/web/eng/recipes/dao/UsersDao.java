package web.eng.recipes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Singleton;

import web.eng.recipes.models.User;

@Singleton
public class UsersDao extends Dao {

	public boolean createUser(User user) {

		open();
		PreparedStatement stmt;

		try {
			stmt = con.prepareStatement("INSERT INTO users (username, password) VALUES(?,?)");

			stmt.setString(1, user.getUserName());

			stmt.setString(2, user.getPassword());
			stmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			System.out.println("vliza si");
			close();
		}

		return true;
	}

	public User findUserByUsername(String username) {

		open();
		User user;
		PreparedStatement stmt;
		ResultSet rs;

		try {
			stmt = con.prepareStatement("Select * from users where username = ?");
			stmt.setString(1, username);
			rs = stmt.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setRole(rs.getString(4));
				user.setIs_deleted(rs.getInt(5));

				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return null;
	}

}
