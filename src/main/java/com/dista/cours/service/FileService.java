package com.dista.cours.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService  {
	void upload(MultipartFile file);
}