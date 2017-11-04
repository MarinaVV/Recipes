package web.eng.recipes.business_services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.Part;

import web.eng.recipes.dao.RecipeDao;
import web.eng.recipes.models.Recipe;
import web.eng.recipes.models.Recipe_ingredient;

public class RecipeServiceImpl implements RecipeService {
	
	@Inject RecipeDao recipeDao;
	
	public String createRecipe(Recipe recipe, List<Part> images) {
		
		
		if(recipeIngredList == null || recipeIngredList.isEmpty()) {
			return "NO_INGREDIENTS";
		}
		if(recipeDao.checkIfRecipeNameExist(title)) {
			return "DUPLICATE_TITLE";
		}
		
		return "";
	}
	
	
	

	private String writeImg(Part img, String title, String imgName) throws IOException {
		OutputStream out = null;
		InputStream filecontent = null;
		String directory = "C:\\Users\\Stanislav\\Documents\\uni\\INfoVOrgan\\9\\";
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
			return "";
		} finally {
			out.close();
		}
	}
	
}
