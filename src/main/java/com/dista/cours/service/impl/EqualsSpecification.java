package com.dista.cours.service.impl;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class EqualsSpecification<T> implements Specification<T> {
	final String field;
	final Object value;
	
	public EqualsSpecification(String field, Object value) {
		this.field = field;
		this.value = value;
	}
	
	public static <T> EqualsSpecification<T> create(String field, Object value) {
		return new EqualsSpecification<>(field, value);
	}
	
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery,
								 CriteriaBuilder criteriaBuilder) {
		String[] split = field.split("\\.");
		Path path = root.get(split[0]);
		for (int i = 1; i < split.length; i++) {
			path = path.get(split[i]);
		}
		return criteriaBuilder.equal(path, value);
		
	}
}
