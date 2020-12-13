package com.dista.cours;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class Interceptor implements HandlerInterceptor {
	@Autowired
	private LocaleChangeInterceptor localeChangeInterceptor;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
		return localeChangeInterceptor.preHandle(request, response, handler);
	}
}