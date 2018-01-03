package web.eng.recipes.servlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import web.eng.recipes.business_services.IngredientService;
import web.eng.recipes.business_services.RecipeService;
import web.eng.recipes.models.Ingredient;
import web.eng.recipes.models.Recipe;
import web.eng.recipes.models.Recipe_ingredient;
import web.eng.recipes.models.User;

@WebServlet("/RecipeControlServlet")
@MultipartConfig
public class RecipeControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	RecipeService recipeService;

	@Inject
	IngredientService ingredientService;

	public RecipeControlServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		switch (action) {
		case "get_ingredients_list":
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(getAllIngredientsAction());
			break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		switch (action) {
		case "create_recipe":
			response.getWriter().write(createRecipeAction(request));
			break;
		case "save_ingredients":
			saveIngredientsAction(request);
			break;
		case "search_recipe_username":
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(searchRecipesPrimaryImageByUsername(request));
			break;
		case "search_recipe_ingredients_list":
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(searchRecipesPrimaryImageByIngredientsList(request));
			break;
		case "search_recipe_recipe_name":
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(searchRecipesPrimaryImageByRecipeName(request));
			break;
		case "get_secondary_images_ingredients":
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(getSecondaryImagesIngredients(request));
			break;
		case "add_recipe_favorites":
			response.getWriter().write(addFavoriteRecipe(request));
			break;
		case "delete_recipe_title":
			response.getWriter().write(deleteRecipeByRecipeId(request));
			
			break;
		case "get_favorite_recipes":
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(getFavoriteRecipes(request));
			
			break;
		case "remove_recipe_favorites":
			response.getWriter().write(removeFavoriteRecipe(request));
			break;
		case "update_recipe":
			response.getWriter().write(updateRecipe(request));
			break;
		}
		
		
		

	}

	private String createRecipeAction(HttpServletRequest request) throws ServletException, IOException {

		List<Recipe_ingredient> recipeIngredList = new ArrayList<>();
		Recipe recipe = new Recipe();

		User user = new User();
		user.setUserName(request.getParameter("username"));
		recipe.setCreatingUser(user);

		List<Part> images = new ArrayList<>(); // images[0] is primary img
		if (request.getPart("primary_img").getContentType() != null
				&& request.getPart("primary_img").getContentType().startsWith("image/")) {
			images.add(0, request.getPart("primary_img"));
		}

		if (request.getPart("secondary_img_1").getContentType() != null
				&& request.getPart("secondary_img_1").getContentType().startsWith("image/")) {
			images.add(1, request.getPart("secondary_img_1"));
		}
		if (request.getPart("secondary_img_2").getContentType() != null
				&& request.getPart("secondary_img_2").getContentType().startsWith("image/")) {
			images.add(2, request.getPart("secondary_img_2"));
		}
		if (request.getPart("secondary_img_3").getContentType() != null
				&& request.getPart("secondary_img_3").getContentType().startsWith("image/")) {
			images.add(3, request.getPart("secondary_img_3"));
		}
		if (request.getPart("secondary_img_4").getContentType() != null
				&& request.getPart("secondary_img_4").getContentType().startsWith("image/")) {
			images.add(4, request.getPart("secondary_img_4"));
		}

		recipe.setTitle(request.getParameter("title"));
		recipe.setCategory(request.getParameter("category"));
		recipe.setDescription(request.getParameter("description"));

		JSONArray jArr = null;

		try {
			jArr = new JSONArray(request.getParameter("recie_ingredients"));
			for (int index = 0; index < jArr.length(); index++) {
				JSONObject obj = jArr.getJSONObject(index);
				Ingredient ingredient = new Ingredient();
				ingredient.setName(obj.getString("ingredient"));
				recipeIngredList.add(index, new Recipe_ingredient());
				recipeIngredList.get(index).setIngredient(ingredient);
				recipeIngredList.get(index).setQuantity(obj.getInt("quantity"));
				recipeIngredList.get(index).setUnits(obj.getString("units"));
			}

			recipe.setRecipe_ingredients(recipeIngredList);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String responseMsg = recipeService.createRecipe(recipe, images);

		return responseMsg;
	}

	private String getAllIngredientsAction() {

		List<String> ingredientNames = ingredientService.getAllIngredientNames();
		return new JSONArray(ingredientNames).toString();

	}

	private void saveIngredientsAction(HttpServletRequest request) {

		JSONArray jArr = null;

		try {
			jArr = new JSONArray(request.getParameter("ingredients"));
			List<String> ingredientsList = new ArrayList<>();

			for (int index = 0; index < jArr.length(); index++) {
				ingredientsList.add(jArr.getString(index));
			}

			ingredientService.saveIngredients(ingredientsList);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String searchRecipesPrimaryImageByUsername(HttpServletRequest request) {

		String username = request.getParameter("username");

		List<Recipe> foundIngredients = recipeService.searchRecipesPrimaryImageByUsername(username);

		return new JSONArray(foundIngredients).toString();

	}

	private String searchRecipesPrimaryImageByIngredientsList(HttpServletRequest request) {

		String regex = ",";

		String ingredients = request.getParameter("ingredients_list");
		String[] ingredientsArray = ingredients.split(regex);

		List<String> ingredientsList = new ArrayList<>();

		for (int index = 0; index < ingredientsArray.length; index++) {
			ingredientsList.add(ingredientsArray[index]);
		}

		List<Recipe> foundIngredients = recipeService.searchRecipesPrimaryImageByIngredientsList(ingredientsList);

		return new JSONArray(foundIngredients).toString();

	}
	
	private String searchRecipesPrimaryImageByRecipeName(HttpServletRequest request) {

		String recipe_name = request.getParameter("recipe_name");

		List<Recipe> foundIngredients = recipeService.searchRecipesPrimaryImageByRecipeName(recipe_name);

		return new JSONArray(foundIngredients).toString();

	}
	
	private String getSecondaryImagesIngredients(HttpServletRequest request) {
		
		String recipeId = request.getParameter("recipe_id");
		Recipe recipe = recipeService.getSecondaryImagesRecipeIngredients(recipeId);
		JSONObject jObj = new JSONObject(recipe);
		return jObj.toString();
	}
	
	private String addFavoriteRecipe(HttpServletRequest request){
		String responseMsg;
		
		String recipeId = request.getParameter("recipe_id");
		String username = request.getParameter("username");
		
		responseMsg=recipeService.addFavoriteRecipe(recipeId,username);
		
		return responseMsg;
	}
	
	private String removeFavoriteRecipe(HttpServletRequest request) {
		String responseMsg;
		
		String recipeId = request.getParameter("recipe_id");
		String username = request.getParameter("username");
		
		responseMsg=recipeService.removeFavoriteRecipe(recipeId,username);
		
		return responseMsg;
	}
	
	private String deleteRecipeByRecipeId(HttpServletRequest request){
		String responseMsg;
		
		String recipeId = request.getParameter("recipe_id");
		
		responseMsg=recipeService.deleteRecipeByRecipeId(recipeId);
		
		return responseMsg;
	}
	
	private String getFavoriteRecipes(HttpServletRequest request){

		String username = request.getParameter("username");
		
		List<Recipe> foundIngredients = recipeService.getFavoriteRecipes(username);

		return new JSONArray(foundIngredients).toString();
	}
	
	private String updateRecipe(HttpServletRequest request) {
		String responseMsg;
		
		String recipeId = request.getParameter("recipe_id");
		String recipeDescr = request.getParameter("recipe_description");
		String recipeTitle = request.getParameter("recipe_title");
		String recipeCategory = request.getParameter("recipe_category");
		
		responseMsg=recipeService.updateRecipe(recipeId,recipeTitle,recipeCategory,recipeDescr);
		
		return responseMsg;
	}

}
