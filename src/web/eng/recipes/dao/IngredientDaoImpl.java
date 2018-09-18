package web.eng.recipes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.eng.recipes.models.Ingredient;
import web.eng.recipes.models.User;
import web.eng.recipes.utils.SQL;

public class IngredientDaoImpl extends Dao implements IngredientDao {

	public List<Ingredient> getAllIngredientNames() {
		open();
		PreparedStatement stmt = null;
		ResultSet rs;
		List<Ingredient> ingredients = new ArrayList<>();

		try {
			stmt = con.prepareStatement(SQL.GET_ALL_INGREDIENTS);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Ingredient ingredient = new Ingredient();
				ingredient.setId(rs.getLong("id"));
				ingredient.setName(rs.getString("name"));
			}

			return ingredients;

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

	public void insertIngredients(List<String> ingredientsList) {
		open();
		PreparedStatement stmt = null;
		String sql = SQL.INSERT_INGREDIENT;

		// change the insert query depending on number of ingredients
		for (int index = 0; index < ingredientsList.size(); index++) {
			if (index < ingredientsList.size() - 1) {
				sql += "(?),";
			} else {
				sql += "(?)";
			}

		}

		try {
			stmt = con.prepareStatement(sql);

			for (int index = 0; index < ingredientsList.size(); index++) {
				stmt.setString(index + 1, ingredientsList.get(index));
			}

			stmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
