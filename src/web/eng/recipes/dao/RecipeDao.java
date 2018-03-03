package web.eng.recipes.dao;

import java.util.List;

import web.eng.recipes.models.Comment;
import web.eng.recipes.models.Image;
import web.eng.recipes.models.Recipe;
import web.eng.recipes.models.Recipe_ingredient;

public interface RecipeDao {

	Recipe getRecipeByTitle(String title);
	boolean createRecipe(Recipe recipe,List<String> imgPaths);
	List<Recipe> getRecipesPrimaryImageByUsername(String username);
	List<Recipe> getRecipesPrimaryImageByIngredientsList(List<String> ingredients);
	List<Recipe> getRecipesPrimaryImageByRecipeName(String recipeName);
	Recipe getSecondaryImagesIngredients(long recipeId);
	boolean isRecipeFavorited(String recipeId, String username);
	boolean insertToFavorites(String recipeId, String username);
	boolean deleteFromFavorites(String recipeId, String username);
	List<Image> deleteRecipeByRecipeId(long recipeId);
	List<Recipe> getFavoriteRecipes(String username);
	boolean updateRecipe(String recipeId, String recipeTitle, String recipeCategory, String recipeDescr, List<Recipe_ingredient> recipeIngredList);
	boolean insertComment(String username, String recipeId, String comment );
	List<Comment> getAllComments(String recipeId);
	boolean isCommentFromUser(String userName, String commentId);
	boolean deleteComment(String commentId);
	boolean updateComment(String commentId, String comment);
}
