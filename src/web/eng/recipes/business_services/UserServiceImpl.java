package web.eng.recipes.business_services;

import javax.inject.Inject;

import web.eng.recipes.dao.UserDao;
import web.eng.recipes.dao.UserDaoImpl;
import web.eng.recipes.models.User;

public class UserServiceImpl implements UserService {

	@Inject UserDao dao;
	
	@Override
	public boolean login(User user){
		
		User userFromDB = dao.findUserByUsername(user.getUserName());
		if(userFromDB.getPassword().equals(user.getPassword())) {
			return true;
		}
		
		return false;
	}

	@Override
	public String register(User user) {

		if(dao.findUserByUsername(user.getUserName()) != null){
			return "DUPLICATE_NAME";
		}
		else{
			if(dao.createUser(user)){
				return "ACC_CREATED";
			}else{
				return "ERROR";
			}
		}

	}
	
}
