package web.eng.recipes.dao;

import java.util.List;

public interface IngredientDao {
	List<String> getAllIngredientNames();
	void insertIngredients(List<String> ingredientsList);
}
