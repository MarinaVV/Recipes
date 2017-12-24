package web.eng.recipes.utils;

public  class SQL {

	public static final String INSERT_RECIPE = "INSERT INTO `recipes`(`description`,`title`, `category`, `user_id`) SELECT  ?, ?,?, id FROM users WHERE `username`=?";
	
	public static final String INSERT_FAVORITE_RECIPE = "INSERT INTO `favorites`(`recipe_id`,`user_id`) SELECT  ?, id FROM users WHERE `username`=?";
	
	
	public static final String DELETE_FAVORITE_RECIPE = "DELETE FROM `favorites` WHERE recipe_id = ? and user_id in (SELECT id FROM users WHERE username=?)";
	
	public static final String DELETE_RECIPE = "DELETE FROM recipes WHERE id=?";
	
	
	
	public static final String GET_FAVORITE_RECIPE_BY_RECIPE_ID_USERNAME = "SELECT * "
																		 + "FROM `favorites` "
																		 + "WHERE recipe_id=? and user_id in "
																		 		+ "(SELECT id "
																		 		+ "FROM users "
																		 		+ "WHERE username=?)";
	
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
																	+ "	INNER JOIN `recipes` ON images.recipe_id=recipes.id AND images.is_primary<>0"
																	+ " INNER JOIN `users` ON recipes.user_id=users.id "
																+ "WHERE images.is_primary=1 AND users.username=?";
	
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
																				+ "INNER JOIN `images` on recipes.id=images.recipe_id 	"
																				+ "INNER JOIN `users` ON recipes.user_id=users.id "
																		+ "WHERE images.is_primary=1 AND recipes.id in "
																				+ "( SELECT DISTINCT recipe_id "
																				+ "	 FROM recipe_ingredients "
																				+ "  WHERE 	";

	//Used to add ingredient for the recipe search by ingredient list
	public static final String INGREDIENT_ROW_FOR_INGREDIENTS_LIST = "recipe_id in (SELECT recipe_id FROM recipe_ingredients WHERE ingredient=?) ";

	public static final String GET_RECIPES_IMAGES_BY_RECIPE_NAME = "SELECT  images.id AS img_id, "
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
			+ "	INNER JOIN `recipes` ON images.recipe_id=recipes.id AND images.is_primary<>0"
			+ " INNER JOIN `users` ON recipes.user_id=users.id "
		+ "WHERE images.is_primary=1 AND recipes.title=?";
	
	public static final String GET_FAVORITE_RECIPES_IMAGES = "SELECT  images.id AS img_id, "
				+ "images.img_path, 	 "
				+ "images.is_primary, "
				+ "recipes.id AS recipe_id, "
				+ "recipes.description, "
				+ "recipes.category, "
				+ "recipes.date, "
				+ "recipes.title, 	 "
				+ "users.id AS user_id, "
				+ "users.username "
			+ "FROM `images` "
				+ "INNER JOIN `recipes` ON images.recipe_id=recipes.id AND images.is_primary<>0 "
				+ "INNER JOIN `users` ON recipes.user_id=users.id "
				+ "INNER JOIN `favorites` ON recipes.id=favorites.recipe_id "
			+ "WHERE images.is_primary=1 AND users.username=?";
}
