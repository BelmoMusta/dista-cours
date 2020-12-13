package com.dista.cours.service;

import com.dista.cours.entite.User;
import com.dista.cours.entite.UserActivation;

public interface UserActivationService {
	
	UserActivation findbyToken(String token);
	
	void createActivationForUser(User user);
	
	void delete(UserActivation activation);
}
