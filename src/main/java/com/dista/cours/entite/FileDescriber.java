package com.dista.cours.entite;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.io.File;

@Getter
@Setter
@Entity
public class FileDescriber extends AbstractEntity {
	private String originalFileName;
	private String extension;
	private String uplodLocation;
	private String contentType;
	@Column(columnDefinition = "tinyint default 0")
	private boolean deleted;
	private Long size;
	private Long downloadCount;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User uploader;
	
	@Transient
	public String getFileName() {
		return originalFileName + "." + extension;
	}
	
	@Transient
	public String getFullPath() {
		return new File(uplodLocation, id + "." + extension).getAbsolutePath();
	}
}
