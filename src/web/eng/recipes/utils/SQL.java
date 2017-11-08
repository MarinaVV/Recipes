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
																	+ "	Inner JOIN `recipes` ON images.recipe_id=recipes.id 	"
																	+ " Inner JOIN `users` ON recipes.user_id=users.id "
																+ "WHERE users.username=? AND images.is_primary=1";
	
}
