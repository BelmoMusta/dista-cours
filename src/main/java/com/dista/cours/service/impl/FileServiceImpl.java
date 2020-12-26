package com.dista.cours.service.impl;

import com.dista.cours.config.ApplicationPropertiesHolder;
import com.dista.cours.entite.FileDescriber;
import com.dista.cours.exception.FileStorageException;
import com.dista.cours.service.FileDescriberService;
import com.dista.cours.service.FileService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {
	@Autowired
	private FileDescriberService fileDescriberService;
	@Autowired
	private ApplicationPropertiesHolder propertiesHolder;
	
	@Override
	public void upload(MultipartFile file) {
		final String uploadLocation = propertiesHolder.getUploadLocation();
		try {
			final File destinationFile = new File(uploadLocation, StringUtils.defaultString(file.getOriginalFilename()));
			final FileDescriber fileDescriber = new FileDescriber();
			fileDescriber.setExtension("pdf");
			fileDescriber.setFullPath(destinationFile.getAbsolutePath());
			fileDescriber.setOriginalFileName(file.getName());
			fileDescriberService.create(fileDescriber);
			
			FileOutputStream output = new FileOutputStream(destinationFile);
			IOUtils.copy(file.getInputStream(), output);
			output.flush();
			output.close();
			
			
			
		} catch (IOException e) {
			throw new FileStorageException(file.getName(), e);
		}
	}
}
