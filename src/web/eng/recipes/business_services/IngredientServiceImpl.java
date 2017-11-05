package web.eng.recipes.business_services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import web.eng.recipes.dao.IngredientDao;

public class IngredientServiceImpl implements IngredientService {

	@Inject
	IngredientDao ingredientDao;

	public List<String> getAllIngredientNames() {
		
		List<String> ingredientNames=ingredientDao.getAllIngredientNames();
		
		if(ingredientNames!=null) {
			return ingredientNames;
		}else {
			return new ArrayList<String>();
		}
		
	}

}
