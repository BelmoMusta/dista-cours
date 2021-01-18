package com.dista.cours.config;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
public class AntMatchersHolder {
	private Set<String> routes = new TreeSet<>();
	
	public void add(String... routes) {
		this.routes.addAll(Arrays.asList(routes));
	}
	
	public boolean isPermittedRoute(String route) {
		return this.routes.contains(route);
	}
}
