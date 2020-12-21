package com.dista.cours.entite.entities;

import com.dista.cours.entite.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Niveau extends AbstractEntity {
    @ManyToOne
    private Enseignant enseignant;
}
