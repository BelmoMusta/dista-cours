package com.dista.cours.config;

import com.dista.cours.config.MailConfiguration;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Component
@Getter
@Setter
public class ActivationMailTemplate extends SimpleMailMessage {
	private ActivationMailTemplate(){
		super.setText("fake text");
	}
	@Autowired
	private MailConfiguration mailConfiguration;
	private String fullName;
	private String token;
	private Date expirationDate;
	
	@Override
	public void setText(String text) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyy à HH:mm:ss", Locale.FRANCE);
		super.setText(String.format(mailConfiguration.getActivationMailTemplate(), fullName, token, dateFormat.format(expirationDate)));
	}
}
