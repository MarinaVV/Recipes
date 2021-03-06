package web.eng.recipes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.eng.recipes.business_services.RecipeServiceImpl;
import web.eng.recipes.models.Image;
import web.eng.recipes.models.Ingredient;
import web.eng.recipes.models.Recipe;
import web.eng.recipes.models.Recipe_ingredient;
import web.eng.recipes.models.User;
import web.eng.recipes.utils.SQL;

public class RecipeDaoImpl extends Dao implements RecipeDao {

	private boolean isAutoCommit = true;

	public Recipe getRecipeByTitle(String title) {

		open();
		PreparedStatement stmt = null;
		ResultSet rs;

		try {
			stmt = con.prepareStatement(SQL.GET_RECIPE_BY_RECIPE_TITLE);

			stmt.setString(1, title.toLowerCase());
			rs = stmt.executeQuery();

			if (rs.next()) {
				Recipe recipe = new Recipe();
				recipe.setId(rs.getLong(1));
				recipe.setDescription(rs.getString(2));
				recipe.setDate(rs.getDate(3));
				recipe.setTitle(rs.getString(4));
				recipe.setCategory(rs.getString(5));
				User user = new User();
				user.setId(rs.getLong(6));
				recipe.setCreatingUser(user);
				return recipe;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (isAutoCommit) {
					close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public boolean createRecipe(Recipe recipe, List<String> imgPaths) {
		open();
		PreparedStatement stmt = null;

		try {
			con.setAutoCommit(false);
			isAutoCommit = false;
			stmt = con.prepareStatement(SQL.INSERT_RECIPE);

			stmt.setString(1, recipe.getDescription());
			stmt.setString(2, recipe.getTitle().toLowerCase());
			stmt.setString(3, recipe.getCategory());
			stmt.setString(4, recipe.getCreatingUser().getUserName().toLowerCase());
			stmt.execute();

			Recipe newRecipe = getRecipeByTitle(recipe.getTitle().toLowerCase());
			if (imgPaths != null && !imgPaths.isEmpty()) {
				insertImage(imgPaths, (short) 1, newRecipe.getId());
			}

			insertRecipeIngredients(recipe.getRecipe_ingredients(), newRecipe.getId());

			con.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
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

		return true;
	}

	public void insertRecipeIngredients(List<Recipe_ingredient> recipe_ingredients, Long recipe_id) {
		open();
		PreparedStatement stmt = null;
		String sql = "INSERT INTO recipe_ingredients (`recipe_id`, `ingredient`, `quantity`, `units` ) VALUES ";

		// change the insert query depending on number of ingredients
		for (int index = 0; index < recipe_ingredients.size(); index++) {
			if (index < recipe_ingredients.size() - 1) {
				sql += "(?,?,?,?),";
			} else {
				sql += "(?,?,?,?)";
			}

		}

		try {
			stmt = con.prepareStatement(sql);

			int indexNumber = 0;
			for (int index = 0; index < recipe_ingredients.size(); index++) {
				indexNumber++;
				stmt.setLong(indexNumber, recipe_id);
				indexNumber++;
				stmt.setString(indexNumber, recipe_ingredients.get(index).getIngredient().getName().toLowerCase());
				indexNumber++;
				stmt.setInt(indexNumber, recipe_ingredients.get(index).getQuantity());
				indexNumber++;
				stmt.setString(indexNumber, recipe_ingredients.get(index).getUnits());
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

				if (isAutoCommit) {
					close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insertImage(List<String> imgPaths, Short isPrimary, Long recipe_id) {
		open();
		PreparedStatement stmt = null;
		String sql = "INSERT INTO images ( `img_path`, `recipe_id`, `is_primary` ) VALUES ";

		// change the insert query depending on number of images
		for (int index = 0; index < imgPaths.size(); index++) {
			if (index < imgPaths.size() - 1) {
				sql += "(?,?,?),";
			} else {
				sql += "(?,?,?)";
			}

		}

		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1, imgPaths.get(0));
			stmt.setLong(2, recipe_id);
			stmt.setShort(3, isPrimary);

			int indexNumber = 3;
			for (int index = 1; index < imgPaths.size(); index++) {
				indexNumber++;
				stmt.setString(indexNumber, imgPaths.get(index));
				indexNumber++;
				stmt.setLong(indexNumber, recipe_id);
				indexNumber++;
				stmt.setShort(indexNumber, (short) 0);
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

				if (isAutoCommit) {
					close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Recipe> getRecipesPrimaryImageByUsername(String username) {
		open();
		PreparedStatement stmt = null;
		String sql = SQL.GET_RECIPES_IMAGES_BY_USERNAME;
		ResultSet rs;
		List<Recipe> foundRecipes = new ArrayList<>();

		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1, username.toLowerCase());
			rs = stmt.executeQuery();

			while (rs.next()) {
				Recipe recipe = mapResultToRecipe(rs);

				foundRecipes.add(recipe);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}

				if (isAutoCommit) {
					close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return foundRecipes;
	}

	public List<Recipe> getRecipesPrimaryImageByIngredientsList(List<String> ingredients) {

		open();
		PreparedStatement stmt = null;
		String sql = SQL.GET_RECIPES_IMAGES_BY_INGREDIENTS_LIST;

		// set search criteria depending from the number of ingredients
		for (int index = 0; index < ingredients.size(); index++) {
			if (index < ingredients.size() - 1) {
				sql += SQL.INGREDIENT_ROW_FOR_INGREDIENTS_LIST + " and ";
			} else {
				sql += SQL.INGREDIENT_ROW_FOR_INGREDIENTS_LIST + " ) ";
			}
		}

		ResultSet rs;
		List<Recipe> foundRecipes = new ArrayList<>();

		try {
			stmt = con.prepareStatement(sql);

			for (int index = 0; index < ingredients.size(); index++) {
				stmt.setString(index + 1, ingredients.get(index).toLowerCase());
			}

			rs = stmt.executeQuery();

			while (rs.next()) {
				Recipe recipe = mapResultToRecipe(rs);

				foundRecipes.add(recipe);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}

				if (isAutoCommit) {
					close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return foundRecipes;

	}

	public List<Recipe> getRecipesPrimaryImageByRecipeName(String recipeName) {
		open();
		PreparedStatement stmt = null;
		String sql = SQL.GET_RECIPES_IMAGES_BY_RECIPE_NAME;
		ResultSet rs;
		List<Recipe> foundRecipes = new ArrayList<>();

		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1, recipeName.toLowerCase());
			rs = stmt.executeQuery();

			while (rs.next()) {

				Recipe recipe = mapResultToRecipe(rs);

				foundRecipes.add(recipe);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}

				if (isAutoCommit) {
					close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return foundRecipes;
	}
	
	public List<Recipe> getFavoriteRecipes(String username){
		open();
		PreparedStatement stmt = null;
		String sql = SQL.GET_FAVORITE_RECIPES_IMAGES;
		ResultSet rs;
		List<Recipe> foundRecipes = new ArrayList<>();

		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1, username);
			rs = stmt.executeQuery();

			while (rs.next()) {

				Recipe recipe = mapResultToRecipe(rs);

				foundRecipes.add(recipe);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}

				if (isAutoCommit) {
					close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return foundRecipes;
	}

	public Recipe getSecondaryImagesIngredients(long recipeId) {

		open();

		PreparedStatement stmtImg = null;
		PreparedStatement stmtRecIngr = null;
		List<Image> imagesList = new ArrayList<>();
		List<Recipe_ingredient> recipeIngredientsList = new ArrayList<>();
		Recipe recipe = new Recipe();
		String sqlImages = SQL.GET_SECONDARY_IMAGES;
		String sqlRecipeIngredients = SQL.GET_RECIPEINGREDIENTS;
		ResultSet rs;

		try {
			con.setAutoCommit(false);
			isAutoCommit = false;

			stmtImg = con.prepareStatement(sqlImages);
			stmtImg.setLong(1, recipeId);
			rs = stmtImg.executeQuery();

			while (rs.next()) {
				Image image = new Image();
				image.setImgPath(rs.getString("img_path"));
				image.setId(rs.getLong("id"));
				image.setIs_primary(rs.getShort("is_primary"));

				imagesList.add(image);
			}
			recipe.setImages(imagesList);

			stmtRecIngr = con.prepareStatement(sqlRecipeIngredients);
			stmtRecIngr.setLong(1, recipeId);
			rs = stmtRecIngr.executeQuery();

			while (rs.next()) {
				Recipe_ingredient recipe_ingredient = new Recipe_ingredient();
				recipe_ingredient.setId(rs.getLong("id"));
				Ingredient ingredient = new Ingredient();
				ingredient.setName(rs.getString("ingredient"));
				recipe_ingredient.setIngredient(ingredient);
				recipe_ingredient.setQuantity(rs.getInt("quantity"));
				recipe_ingredient.setUnits(rs.getString("units"));

				recipeIngredientsList.add(recipe_ingredient);
			}
			recipe.setRecipe_ingredients(recipeIngredientsList);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (stmtImg != null) {
					stmtImg.close();
				}
				if (stmtRecIngr != null) {
					stmtRecIngr.close();
				}
				close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return recipe;

	}
	
	public boolean isRecipeFavorited(String recipeId, String username){
		open();
		PreparedStatement stmt = null;
		ResultSet rs;
		
		try {

			stmt = con.prepareStatement(SQL.GET_FAVORITE_RECIPE_BY_RECIPE_ID_USERNAME);

			stmt.setString(1, recipeId);
			stmt.setString(2, username);
			rs = stmt.executeQuery();

			if(rs.next()){
				return true;
			}
			
			return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (isAutoCommit) {
					close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	public boolean insertToFavorites(String recipeId, String username) {
		open();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement(SQL.INSERT_FAVORITE_RECIPE);

			stmt.setString(1, recipeId);
			stmt.setString(2, username);
			stmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (isAutoCommit) {
					close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return true;
	}
	
	public boolean deleteFromFavorites(String recipeId, String username) {
		open();
		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement(SQL.DELETE_FAVORITE_RECIPE);

			stmt.setString(1, recipeId);
			stmt.setString(2, username);
			stmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (isAutoCommit) {
					close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	public List<Image> deleteRecipeByRecipeId(long recipeId) {
		open();
		PreparedStatement stmtDelete = null;
		List<Image> images;
		
		try {
			con.setAutoCommit(false);
			isAutoCommit = false;
			
			images=getAllImagesByRecipeId(recipeId);
			
			stmtDelete = con.prepareStatement(SQL.DELETE_RECIPE);

			stmtDelete.setLong(1, recipeId);
			stmtDelete.execute();
			
			con.commit();
			return images;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (stmtDelete != null) {
					stmtDelete.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			close();

		}

	}
	
	public List<Image> getAllImagesByRecipeId(Long recipe_id) {
		open();
		PreparedStatement stmt = null;
		ResultSet rs;
		List<Image> images = new ArrayList<>();
		
		String sql = SQL.GET_ALL_IMAGES_RECIPE_ID;

		try {
			stmt = con.prepareStatement(sql);

			stmt.setLong(1, recipe_id);

			rs=stmt.executeQuery();
			
			while(rs.next()){
				Image image = new Image();
				image.setImgPath(rs.getString("img_path"));
				images.add(image);
			}
			
			return images;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}

				if (isAutoCommit) {
					close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean updateRecipe(String recipeId, String recipeTitle, String recipeCategory, String recipeDescr, List<Recipe_ingredient> recipeIngredList) {
		open();
		PreparedStatement stmt = null;

		
		String sql = SQL.UPDATE_RECIPE;

		try {
			con.setAutoCommit(false);
			isAutoCommit = false;
			
			this.deleteRecipeIngredients(recipeId);
			this.insertRecipeIngredients(recipeIngredList, Long.valueOf(recipeId));
			
			stmt = con.prepareStatement(sql);

			stmt.setString(1, recipeTitle);
			stmt.setString(2, recipeCategory);
			stmt.setString(3, recipeDescr);
			stmt.setString(4, recipeId);
			
			stmt.execute();
			
			con.commit();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
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
	
	public boolean deleteRecipeIngredients(String recipeId) {
		open();
		PreparedStatement stmt = null;

		
		String sql = SQL.DELETE_RECIPE_INGREDIENTS;

		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1, recipeId);
			
			stmt.execute();
			
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}

				if (isAutoCommit) {
					close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private Recipe mapResultToRecipe(ResultSet rs) throws SQLException {
		Recipe recipe = new Recipe();
		recipe.setCategory(rs.getString("category"));
		recipe.setDate(rs.getDate("date"));
		recipe.setDescription(rs.getString("description"));
		recipe.setId(rs.getLong("recipe_id"));
		recipe.setTitle(rs.getString("title"));

		User user = new User();
		user.setId(rs.getLong("user_id"));
		user.setUserName(rs.getString("username"));
		recipe.setCreatingUser(user);

		Image primary_image = new Image();
		primary_image.setId(rs.getLong("img_id"));
		primary_image.setImgPath(rs.getString("img_path"));
		primary_image.setIs_primary(rs.getShort("is_primary"));

		List<Image> images = new ArrayList<>();
		images.add(0, primary_image);
		recipe.setImages(images);

		return recipe;
	}

}
