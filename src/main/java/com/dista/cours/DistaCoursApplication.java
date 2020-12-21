package com.dista.cours;

import com.dista.cours.entite.entities.Enseignant;
import com.dista.cours.repository.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class DistaCoursApplication implements CommandLineRunner {
	@Autowired
	private EnseignantRepository enseignantRepository;

	public static void main(String[] args) {
		SpringApplication.run(DistaCoursApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
Enseignant enseignant1=enseignantRepository.save(new Enseignant("Physique1",null));

		Enseignant enseignant2=enseignantRepository.save(new Enseignant( "Physique2",null));
		Enseignant enseignant3=enseignantRepository.save(new Enseignant("Physique3",null));

		enseignantRepository.findAll().forEach(Enseignant -> {
			System.out.println(enseignant1.getCours());


		});
	}
}
