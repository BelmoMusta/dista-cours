package com.dista.cours.i18n;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocalHolder {
	private static final ThreadLocal<Locale> threadLocal = new ThreadLocal<>();
	
	public static void setLocal(Locale local) {
		threadLocal.set(local);
	}
	
	public static Locale getLocale() {
		return threadLocal.get();
	}
}
