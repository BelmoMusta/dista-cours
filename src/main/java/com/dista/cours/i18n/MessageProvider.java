package com.dista.cours.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MessageProvider {
	private static MessageProvider messageProvider;
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	public String getMessage(String message, Object... args) {
		return messageSource.getMessage(message, args, LocalHolder.getLocale());
	}
	
	@PostConstruct
	public void init() {
		messageProvider = this;
	}
	
	public static MessageProvider get() {
		return messageProvider;
	}
}
