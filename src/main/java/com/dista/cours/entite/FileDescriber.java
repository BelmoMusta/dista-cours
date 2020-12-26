package com.dista.cours.entite;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class FileDescriber extends AbstractEntity{
	private String originalFileName;
	private String fullPath;
	private String extension;
}
