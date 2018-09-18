package web.eng.recipes.dao;

import java.util.List;

import web.eng.recipes.models.Ingredient;

public interface IngredientDao {
	List<Ingredient> getAllIngredientNames();
	void insertIngredients(List<String> ingredientsList);
}
