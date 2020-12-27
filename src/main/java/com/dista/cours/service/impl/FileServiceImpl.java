package com.dista.cours.service.impl;

import com.dista.cours.config.ApplicationPropertiesHolder;
import com.dista.cours.entite.FileDescriber;
import com.dista.cours.entite.User;
import com.dista.cours.exception.FileStorageException;
import com.dista.cours.service.FileDescriberService;
import com.dista.cours.service.FileService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {
	public static final String HEADER_ATTACHEMENT = "attachment; filename=\"";
	
	@Autowired
	private FileDescriberService fileDescriberService;
	@Autowired
	private ApplicationPropertiesHolder propertiesHolder;
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor =
			FileStorageException.class)
	public void upload(MultipartFile multipartFile) {
		final String uploadLocation = propertiesHolder.getUploadLocation();
		FileDescriber fileDescriber = null;
		try {
			fileDescriber = createDescriber(multipartFile, uploadLocation);
			final File destinationFile = new File(fileDescriber.getFullPath());
			copyFileContent(multipartFile, destinationFile);
		} catch (IOException e) {
			fileDescriberService.delete(fileDescriber);
			throw new FileStorageException(multipartFile.getName(), e);
		}
	}
	
	private FileDescriber createDescriber(MultipartFile multipartFile, String uploadLocation) {
		FileDescriber fileDescriber = new FileDescriber();
		final String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
		
		fileDescriber.setExtension(extension);
		fileDescriber.setUplodLocation(uploadLocation);
		fileDescriber.setSize(multipartFile.getSize());
		fileDescriber.setContentType(multipartFile.getContentType());
		fileDescriber.setOriginalFileName(FilenameUtils.getBaseName(multipartFile.getOriginalFilename()));
		User details = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		fileDescriber.setUploader(details);
		fileDescriber = fileDescriberService.create(fileDescriber);
		return fileDescriber;
	}
	
	private void copyFileContent(MultipartFile file, File destinationFile) throws IOException {
		FileOutputStream output = new FileOutputStream(destinationFile);
		IOUtils.copy(file.getInputStream(), output);
		output.flush();
		output.close();
	}
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor =
			FileStorageException.class)
	@Override
	public void deleteLogically(Long id) {
		delete(id, false);
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor =
			FileStorageException.class)
	@Override
	
	public void delete(Long id) {
		delete(id, true);
	}
	
	private void delete(Long id, boolean deletePhysically) {
		Optional<FileDescriber> one = fileDescriberService.findOne(id);
		if (one.isPresent()) {
			FileDescriber fileDescriber = one.get();
			File file = new File(fileDescriber.getFullPath());
			if (deletePhysically) {
				try {
					FileUtils.forceDelete(file);
				} catch (IOException e) {
					throw new FileStorageException(file.getName(), e);
					
				}
				fileDescriberService.delete(id);
			} else {
				fileDescriber.setDeleted(true);
			}
		}
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor =
			FileStorageException.class)
	@Override
	public ResponseEntity<Resource> download(long id) {
		Optional<FileDescriber> optionalFileDescriber = fileDescriberService.findOne(id);
		if (optionalFileDescriber.isPresent() && !optionalFileDescriber.get().isDeleted()) {
			FileDescriber fileDescriber = optionalFileDescriber.get();
			String fullPath = fileDescriber.getFullPath();
			File file = new File(fullPath);
			FileInputStream fileInputStream;
			try {
				fileInputStream = FileUtils.openInputStream(file);
			} catch (IOException e) {
				throw new FileStorageException(file.getName(), e);
			}
			String fileName = fileDescriber.getFileName();
			Long downloadCount = fileDescriber.getDownloadCount();
			if (downloadCount == null) {
				downloadCount = 0L;
			}
			fileDescriber.setDownloadCount(downloadCount + 1L);
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(fileDescriber.getContentType()))
					.header(HttpHeaders.CONTENT_DISPOSITION, HEADER_ATTACHEMENT + fileName + "\"")
					.body(new InputStreamResource(fileInputStream));
		}
		return ResponseEntity.notFound().build();
	}
	
	@Override
	public ResponseEntity<Resource> metadata(long id) {
		return null;
	}
}
