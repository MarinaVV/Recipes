package web.eng.recipes.business_services;

import java.util.List;

import javax.servlet.http.Part;

import web.eng.recipes.models.Recipe;
import web.eng.recipes.models.Recipe_ingredient;

public interface RecipeService {

	String createRecipe(Recipe recipe, List<Part> images);
	List<Recipe> searchRecipesPrimaryImageByUsername(String username);
	List<Recipe> searchRecipesPrimaryImageByIngredientsList(List<String> ingredients);
	List<Recipe> searchRecipesPrimaryImageByRecipeName(String recipe_name);
	Recipe getSecondaryImagesRecipeIngredients(String recipeId);
}
