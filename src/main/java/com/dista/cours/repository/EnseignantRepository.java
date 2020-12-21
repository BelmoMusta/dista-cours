package com.dista.cours.repository;

import com.dista.cours.entite.entities.Enseignant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnseignantRepository extends JpaRepository<Enseignant,Long> {
    public Page<Enseignant> findByCoursContains(String kw, Pageable pageable);
}