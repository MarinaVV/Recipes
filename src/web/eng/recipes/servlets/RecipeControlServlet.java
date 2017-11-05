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

	public RecipeControlServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");

		switch(action) {
		case "create_recipe": 
			response.getWriter().write(createRecipeAction(request));
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

}
