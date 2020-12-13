package com.dista.cours.service;

import com.dista.cours.entite.UserActivation;

public interface MailingService {
	void sendEmail(UserActivation userActivation);
}
