package web.eng.recipes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import web.eng.recipes.models.Recipe;

public class RecipeDaoImpl extends Dao implements RecipeDao {

	public boolean checkIfRecipeNameExist(String title) {

		open();
		PreparedStatement stmt;
		ResultSet rs;

		try {
			stmt = con.prepareStatement("Select * from recipes where title=?");

			stmt.setString(1, title);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return false;
	}
}
