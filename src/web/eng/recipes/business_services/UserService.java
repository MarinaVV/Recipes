package web.eng.recipes.business_services;

import web.eng.recipes.models.User;

public interface UserService {

	boolean login(User user);
	
	String register(User user);
}
