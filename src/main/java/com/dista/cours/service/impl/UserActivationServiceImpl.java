package com.dista.cours.service.impl;

import com.dista.cours.entite.User;
import com.dista.cours.entite.UserActivation;
import com.dista.cours.repository.UserActivationRepository;
import com.dista.cours.service.MailingService;
import com.dista.cours.service.UserActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Service
public class UserActivationServiceImpl implements UserActivationService {
	@Autowired
	private UserActivationRepository userActivationRepository;
	@Autowired
	private MailingService mailingService;
	
	@Override
	public UserActivation findbyToken(String token) {
		final EqualsSpecification<UserActivation> tokenSpec = new EqualsSpecification<>("token", token);
		final EqualsSpecification<UserActivation> consumedSpec = new EqualsSpecification<>("consumed", false);
		return userActivationRepository.findOne(tokenSpec.and(consumedSpec))
				.orElse(null);
	}
	
	@Override
	public void createActivationForUser(User user) {
		final UserActivation activation = new UserActivation();
		activation.setUser(user);
		String token = UUID.randomUUID().toString();
		
		activation.setToken(token);
		final LocalDateTime localDateTime = LocalDateTime.now().plusDays(1);
		final Date expiresAt = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		activation.setExpiresAt(expiresAt);
		userActivationRepository.save(activation);
		mailingService.sendEmail(activation);
		
	}
	
	@Override
	public void delete(UserActivation activation) {
		userActivationRepository.delete(activation);
	}
}
