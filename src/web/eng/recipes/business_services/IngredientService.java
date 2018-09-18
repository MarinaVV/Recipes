package web.eng.recipes.business_services;

import java.util.List;

public interface IngredientService {
	//List<String> getAllIngredientNames();
	void saveIngredients(List<String> ingredientsList);
}
