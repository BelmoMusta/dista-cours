package com.dista.cours.repository;

import com.dista.cours.entite.UserParameter;
import com.dista.cours.entite.UserParameterPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserParameterRepository extends JpaRepository<UserParameter, UserParameterPK> {
	Optional<UserParameter> findByUserIdAndParameterId(Long parameterId, Long userId);
}

