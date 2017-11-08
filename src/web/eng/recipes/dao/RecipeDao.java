package web.eng.recipes.dao;

import java.util.List;

import web.eng.recipes.models.Recipe;

public interface RecipeDao {

	Recipe getRecipeByTitle(String title);
	boolean createRecipe(Recipe recipe,List<String> imgPaths);
	
}
