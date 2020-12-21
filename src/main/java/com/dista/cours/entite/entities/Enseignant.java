package com.dista.cours.entite.entities;

import com.dista.cours.entite.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enseignant extends Personne  {
    private String cours;
    @OneToMany
    private List<Niveau> niveau;
}
