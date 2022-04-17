package com.dista.cours.controller;

import com.dista.cours.dtos.FileDescriberDTO;
import com.dista.cours.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
@CrossOrigin
public class FileController {
	@Autowired
	private FileService fileService;
	
	@PostMapping("/upload")
	public void upload(@RequestParam(name = "file", required = true) MultipartFile file) {
		fileService.upload(file);
	}
	
	@DeleteMapping("/{id}/delete")
	public void delete(@PathVariable Long id,
					   @RequestParam(name = "logically", defaultValue = "false") boolean logically) {
		if (logically) {
			fileService.deleteLogically(id);
		} else {
			fileService.delete(id);
		}
	}
	
	@GetMapping("/{id}/download")
	public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
		return fileService.download(id);
	}
	
	@GetMapping("/{id}/metadata")
	public ResponseEntity<FileDescriberDTO> metadata(@PathVariable Long id) {
		return fileService.metadata(id);
	}
}
