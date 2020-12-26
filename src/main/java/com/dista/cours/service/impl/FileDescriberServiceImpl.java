package com.dista.cours.service.impl;

import com.dista.cours.entite.FileDescriber;
import com.dista.cours.repository.FileDescriberRepository;
import com.dista.cours.service.FileDescriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@Service
public class FileDescriberServiceImpl implements FileDescriberService {
	@Autowired
	private FileDescriberRepository fileDescriberRepository;
	
	@Override
	public FileDescriber create(FileDescriber fileDescriber) {
		return fileDescriberRepository.save(fileDescriber);
	}
	
	@Override
	public Optional<FileDescriber> findOne(Long id) {
		return fileDescriberRepository.findById(id);
	}
	
	@Override
	public Set<FileDescriber> findAll() {
		return new TreeSet<>(fileDescriberRepository.findAll());
	}
	
	@Override
	public FileDescriber update(FileDescriber fileDescriber) {
		if (fileDescriber.getId() != null) {
			return fileDescriberRepository.saveAndFlush(fileDescriber);
		}
		return fileDescriber;
	}
	
	@Override
	public FileDescriber update(Long id, FileDescriber fileDescriber) {
		fileDescriber.setId(id);
		return update(fileDescriber);
	}
	
	@Override
	public void delete(FileDescriber fileDescriber) {
		fileDescriberRepository.delete(fileDescriber);
	}
	
	@Override
	public void delete(Long id) {
		fileDescriberRepository.deleteById(id);
	}
}
