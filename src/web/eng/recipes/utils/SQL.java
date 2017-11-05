package web.eng.recipes.utils;

public  class SQL {

	public static final String INSERT_RECIPE = "INSERT INTO `recipes`(`description`,`title`, `category`, `user_id`) SELECT  ?, ?,?, id FROM users WHERE `username`=?";
	
	
}
