package com.dista.cours.service;

import com.dista.cours.entite.dto.FileDescriberDTO;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	void upload(MultipartFile file);
	
	void delete(Long id);
	
	void deleteLogically(Long id);
	
	ResponseEntity<Resource> download(long id);
	
	ResponseEntity<FileDescriberDTO> metadata(Long id);
}