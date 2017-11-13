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
	
	public List<Recipe> searchRecipesPrimaryImageByIngredientsList(List<String> ingredients){
		
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

	private String readImg(String imagePath) {

		try {
			Path path = Paths.get(imagePath);
			String image = new String(java.util.Base64.getEncoder().encode(Files.readAllBytes(path)));

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
