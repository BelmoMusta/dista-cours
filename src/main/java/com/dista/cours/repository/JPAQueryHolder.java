package com.dista.cours.repository;

import com.querydsl.core.dml.InsertClause;
import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
public class JPAQueryHolder {
	@Autowired
	private EntityManager entityManager;
	private static EntityManager entityManagerHolder;
	
	@PostConstruct
	public void init() {
		entityManagerHolder = entityManager;
	}
	
	public static <R> JPAQuery<R> createSelectQuery() {
		return new JPAQuery<>(entityManagerHolder);
	}
	
	public static <R> JPADeleteClause createDeleteQuery(EntityPath<R> entityPath) {
		return new JPADeleteClause(entityManagerHolder, entityPath);
	}
	
	
}
