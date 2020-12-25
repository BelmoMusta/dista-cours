package com.dista.cours.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

@Getter
@Setter
@ApplicationProperties
public class ApplicationPropertiesHolder {
	private static ApplicationPropertiesHolder holder;
	
	@Value("${upload.location}")
	private String uploadLocation;
	
	@PostConstruct
	public void init() {
		holder = this;
	}
	
	public static ApplicationPropertiesHolder get() {
		return holder;
	}
}
