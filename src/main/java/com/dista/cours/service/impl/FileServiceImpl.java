package com.dista.cours.service.impl;

import com.dista.cours.config.ApplicationPropertiesHolder;
import com.dista.cours.service.FileService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {
	@Autowired
	private ApplicationPropertiesHolder propertiesHolder;
	@Override
	public void upload(MultipartFile file) throws IOException {
		final String uploadLocation = propertiesHolder.getUploadLocation();
		IOUtils.copy(file.getInputStream(), new FileOutputStream(new File(uploadLocation, file.getName())));
	}
}
