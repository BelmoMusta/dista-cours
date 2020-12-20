package com.dista.cours.i18n;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
@Component
public class LocaleInterceptor implements HandlerInterceptor {
	private static final List<Locale> LOCALES = Arrays.asList(new Locale("en"), new Locale("fr"));
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Locale locale;
		String language = request.getHeader("Accept-Language");
		if (language == null || language.isEmpty()) {
			locale = Locale.getDefault();
		} else {
			List<Locale.LanguageRange> list = Locale.LanguageRange.parse(language);
			locale = Locale.lookup(list, LOCALES);
		}
		LocalHolder.setLocal(locale);
		return true;
	}
}
