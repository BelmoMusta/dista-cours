package com.dista.cours.repository;

import com.dista.cours.entite.Parameter;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParameterRepository extends AbstractRepository<Parameter> {
	
	Optional<Parameter> findByName(String name);
}

