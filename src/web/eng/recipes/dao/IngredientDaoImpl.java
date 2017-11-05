package web.eng.recipes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.eng.recipes.models.User;

public class IngredientDaoImpl extends Dao implements IngredientDao {

	public List<String> getAllIngredientNames(){
		open();
		PreparedStatement stmt=null;
		ResultSet rs;
		List<String> ingredientNames = new ArrayList<>();

		try {
			stmt = con.prepareStatement("Select * from ingredients");
			rs = stmt.executeQuery();

			while (rs.next()) {
				ingredientNames.add(rs.getString(2));
			}
			
			return ingredientNames;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close();
		}
	}
}
