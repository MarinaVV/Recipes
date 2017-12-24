package web.eng.recipes.business_services;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import web.eng.recipes.dao.RecipeDao;
import web.eng.recipes.models.Image;
import web.eng.recipes.models.Recipe;
import web.eng.recipes.models.Recipe_ingredient;

public class RecipeServiceImpl implements RecipeService {

	@Inject
	RecipeDao recipeDao;

	public static final String IMAGE_FOLDER = "C:\\Users\\Stanislav\\Documents\\uni\\WebIngPlaning\\images\\";

	public String createRecipe(Recipe recipe, List<Part> images) {

		List<String> imgPaths = new ArrayList<>();

		if (recipe == null) {
			return "ERROR";
		}
		if (recipe.getRecipe_ingredients() == null || recipe.getRecipe_ingredients().isEmpty()) {
			return "NO_INGREDIENTS";
		}
		if (recipeDao.getRecipeByTitle(recipe.getTitle()) != null) {
			return "DUPLICATE_TITLE";
		}
		if (images != null && !images.isEmpty()) {
			try {
				imgPaths.add(0, writeImg(images.get(0), recipe.getTitle(), "primary_img"));
				for (int index = 1; index < images.size(); index++) {
					imgPaths.add(index, writeImg(images.get(index), recipe.getTitle(), "secondary_img_" + index));
				}

			} catch (IOException ex) {
				return "IMAGES_ERROR";
			}
		} else {
			imgPaths.add(0, "no_image_provided");
		}

		if (recipeDao.createRecipe(recipe, imgPaths)) {
			return "RECIPE_CREATED";
		} else {
			return "ERROR";
		}
	}

	public List<Recipe> searchRecipesPrimaryImageByUsername(String username) {

		List<Recipe> foundRecipes = new ArrayList<>();

		foundRecipes = recipeDao.getRecipesPrimaryImageByUsername(username);
		for (Recipe recipe : foundRecipes) {
			if (recipe.getImages().get(0).getImgPath() != null) {
				String img = readImg(recipe.getImages().get(0).getImgPath());
				recipe.getImages().get(0).setImage(img);
			}
		}
		return foundRecipes;

	}

	public List<Recipe> searchRecipesPrimaryImageByIngredientsList(List<String> ingredients) {

		List<Recipe> foundRecipes = new ArrayList<>();

		foundRecipes = recipeDao.getRecipesPrimaryImageByIngredientsList(ingredients);
		for (Recipe recipe : foundRecipes) {
			if (recipe.getImages().get(0).getImgPath() != null) {
				String img = readImg(recipe.getImages().get(0).getImgPath());
				recipe.getImages().get(0).setImage(img);
			}
		}

		return foundRecipes;
	}

	public List<Recipe> searchRecipesPrimaryImageByRecipeName(String recipeName) {

		List<Recipe> foundRecipes = new ArrayList<>();

		foundRecipes = recipeDao.getRecipesPrimaryImageByRecipeName(recipeName);
		for (Recipe recipe : foundRecipes) {
			if (recipe.getImages().get(0).getImgPath() != null) {
				String img = readImg(recipe.getImages().get(0).getImgPath());
				recipe.getImages().get(0).setImage(img);
			}
		}
		return foundRecipes;

	}

	public Recipe getSecondaryImagesRecipeIngredients(String recipeIdString) {

		Recipe recipe = new Recipe();
		long recipeIdLong = Long.parseLong(recipeIdString);

		recipe = recipeDao.getSecondaryImagesIngredients(recipeIdLong);
		if (recipe.getImages() != null) {
			for (Image image : recipe.getImages()) {
				if (image != null) {
					String img = readImg(image.getImgPath());
					image.setImage(img);
				}
			}
		}

		return recipe;

	}

	public String addFavoriteRecipe(String recipeId, String username) {
		
		if(recipeDao.isRecipeFavorited(recipeId, username)){
			return "RECIPE_FAVORITED";
		}
		if (recipeDao.insertToFavorites(recipeId, username)) {
			return "OK";
		} else {
			return "ERROR";
		}
	}
	
	public String removeFavoriteRecipe(String recipeId, String username) {
		if (recipeDao.deleteFromFavorites(recipeId, username)) {
			return "REMOVED";
		} else {
			return "ERROR";
		}
	}
	
	public List<Recipe> getFavoriteRecipes(String username){
		
		List<Recipe> foundRecipes = new ArrayList<>();

		foundRecipes = recipeDao.getFavoriteRecipes(username);
		for (Recipe recipe : foundRecipes) {
			if (recipe.getImages().get(0).getImgPath() != null) {
				String img = readImg(recipe.getImages().get(0).getImgPath());
				recipe.getImages().get(0).setImage(img);
			}
		}
		return foundRecipes;
	}

	public String deleteRecipeByRecipeId(String recipeId) {
		long recipeIdLong = Long.parseLong(recipeId);

		List<Image> images = recipeDao.deleteRecipeByRecipeId(recipeIdLong);

		if (images != null) {

			for (Image image : images) {
				if (!image.getImgPath().equals("no_image_provided")) {
					deleteImage(image.getImgPath());
				}
			}
			return "OK";
		} else {
			return "ERROR";
		}
	}

	private void deleteImage(String imgPath) {
		File file = new File(imgPath);

		file.delete();
	}

	private String readImg(String imagePath) {

		String image;

		try {
			if (!imagePath.equals("no_image_provided")) {
				Path path = Paths.get(imagePath);
				image = new String(java.util.Base64.getEncoder().encode(Files.readAllBytes(path)));
			} else {
				image = "no_image_provided";
			}
			return image;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String writeImg(Part img, String title, String imgName) throws IOException {
		OutputStream out = null;
		InputStream filecontent = null;
		String directory = IMAGE_FOLDER;
		String name = imgName + ".png";

		new File(directory + title).mkdirs();

		String pathToDir = directory + title + "\\";
		String imgPath = pathToDir + name;

		try {
			out = new FileOutputStream(new File(imgPath));
			filecontent = img.getInputStream();
			int read = 0;
			// final byte[] bytes = new byte[1024];

			while ((read = filecontent.read()) != -1) {
				out.write(read);
			}

			return imgPath;
		} catch (FileNotFoundException e) {
			return null;
		} finally {
			out.close();
		}
	}

}
