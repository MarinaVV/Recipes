package web.eng.recipes.business_services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import web.eng.recipes.dao.IngredientDao;
import web.eng.recipes.models.Ingredient;

public class IngredientServiceImpl implements IngredientService {

	@Inject
	IngredientDao ingredientDao;


	
	public void saveIngredients(List<String> ingredientsList) {
		
		if(ingredientsList!=null && !ingredientsList.isEmpty()) {
			ingredientDao.insertIngredients(ingredientsList);
		}
	}

}
