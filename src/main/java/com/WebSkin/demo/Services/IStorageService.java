package com.WebSkin.demo.Services;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
	public String storeFile(MultipartFile file);
	public Stream<Path> loadAll();
	public byte[] readFile(String filename);
	public void deleteFile();
}
