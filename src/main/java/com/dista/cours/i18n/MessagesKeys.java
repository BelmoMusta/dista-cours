package com.dista.cours.i18n;

public enum MessagesKeys {
	AUTH_USER_DISABLED("auth.user.disabled"),
	AUTH_USER_OR_PASSWORD_INCORRECT("auth.user.or.password.incorrect"),
	AUTH_USER_NOT_FOUND("auth.user.not.found"),
	AUTH_ERROR("auth.error"),
	RESOURCE_NOT_FOUND("resource.not.found"),
	INVALIDE_JWT_TOKEN("invalide.jwt.token"),
	JWT_TOKEN_EXPIRED("jwt.token.expired"),
	
	;
	String value;
	
	MessagesKeys(String s) {
		this.value = s;
	}
	
	public String getValue() {
		return value;
	}
}
