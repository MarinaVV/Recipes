package web.eng.recipes.utils;

public  class SQL {

	public static final String INSERT_RECIPE = "INSERT INTO `recipes`(`description`,`title`, `category`, `user_id`) SELECT  ?, ?,?, id FROM users WHERE `username`=?";
	
	public static final String GET_RECIPES_IMAGES_BY_USERNAME = "SELECT  images.id AS img_id, "
																	+ "	images.img_path, 	"
																	+ "	images.is_primary, "
																	+ "	recipes.id AS recipe_id,"
																	+ " recipes.description, "
																	+ " recipes.category,"
																	+ "	recipes.date, "
																	+ "	recipes.title, 	"
																	+ "	users.id AS user_id, "
																	+ "	users.username "
																+ "FROM `images` "
																	+ "	RIGHT OUTER JOIN `recipes` ON images.recipe_id=recipes.id AND images.is_primary<>0"
																	+ " INNER JOIN `users` ON recipes.user_id=users.id "
																+ "WHERE users.username=?";
	
	//Must close the bracket in the end!
	public static final String GET_RECIPES_IMAGES_BY_INGREDIENTS_LIST = "SELECT 	images.id AS img_id,   "
																				+ "images.img_path,  "
																				+ "images.is_primary,     "
																				+ "recipes.id AS recipe_id,     "
																				+ "recipes.description,     "
																				+ "recipes.category,     "
																				+ "recipes.date,     "
																				+ "recipes.title,     "
																				+ "users.id AS user_id,     "
																				+ "users.username "
																		+ "FROM `recipes` 	"
																				+ "LEFT OUTER join `images` on recipes.id=images.recipe_id 	"
																				+ "INNER JOIN `users` ON recipes.user_id=users.id "
																		+ "WHERE images.is_primary=1 AND recipes.id in "
																				+ "( SELECT DISTINCT recipe_id "
																				+ "	 FROM recipe_ingredients "
																				+ "  WHERE 	";

	//Used to add ingredient for the recipe search by ingredient list
	public static final String INGREDIENT_ROW_FOR_INGREDIENTS_LIST = "recipe_id in (SELECT recipe_id FROM recipe_ingredients WHERE ingredient=?) ";
}
