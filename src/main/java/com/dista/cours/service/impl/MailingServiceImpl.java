package com.dista.cours.service.impl;

import com.dista.cours.config.ActivationMailTemplate;
import com.dista.cours.config.MailConfiguration;
import com.dista.cours.entite.User;
import com.dista.cours.entite.UserActivation;
import com.dista.cours.service.MailingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class MailingServiceImpl implements MailingService {
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private ActivationMailTemplate activationMailTemplate;
	@Autowired
	private MailConfiguration mailConfiguration;
	
	@Override
	public void sendEmail(UserActivation activation) {
		User user = activation.getUser();
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		activationMailTemplate.setTo(user.getEmail());
		activationMailTemplate.setFrom("contact@distacours.com");
		activationMailTemplate.setSubject("Activation de compte");
		activationMailTemplate.setExpirationDate(activation.getExpiresAt());
		activationMailTemplate.setFullName(user.getName() + " " + user.getLastName());
		activationMailTemplate.setToken(activation.getToken());
		try {
			mimeMessage.setContent(activationMailTemplate.getText(), "text/html; charset=utf-8");
		} catch (MessagingException e) {
			log.error("", e);
		}
		if (mailConfiguration.isMailingActive()) {
			activationMailTemplate.setText("");
			javaMailSender.send(activationMailTemplate);
		} else {
			log.info(activationMailTemplate.getText());
		}
	}
}
