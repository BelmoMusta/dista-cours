package com.dista.cours.security;

import com.dista.cours.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
	@Autowired
	private LocaleChangeInterceptor localeChangeInterceptor;
	@Autowired
	private Interceptor interceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor);
		registry.addInterceptor(interceptor);
	}
}
