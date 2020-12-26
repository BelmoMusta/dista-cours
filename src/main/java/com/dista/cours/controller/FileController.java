package com.dista.cours.controller;

import com.dista.cours.entite.dto.CustomizedValueDTO;
import com.dista.cours.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
	
	@GetMapping("/{id}/download")
	public ResponseEntity<List<CustomizedValueDTO>> downloadFile(Long id) {
		return ResponseEntity.ok().build();
	}
	
}
