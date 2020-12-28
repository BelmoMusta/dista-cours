package com.dista.cours.entite.dto;

import com.dista.cours.entite.AbstractEntity;
import com.dista.cours.entite.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.io.File;

@Getter
@Setter
public class FileDescriberDTO extends AbstractEntity {
	private String originalFileName;
	private String extension;
	private String uplodLocation;
	private String contentType;
	private boolean deleted;
	private Long size;
	private Long downloadCount;
	
	@Transient
	public String getFileName() {
		return originalFileName + "." + extension;
	}
	
	@Transient
	public String getFullPath() {
		return new File(uplodLocation, id + "." + extension).getAbsolutePath();
	}
}
