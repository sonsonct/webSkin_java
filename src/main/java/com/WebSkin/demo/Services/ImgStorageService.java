package com.WebSkin.demo.Services;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImgStorageService implements IStorageService{

	private final Path StoreFolder = Paths.get("upload");
	public ImgStorageService() {
		try {
			Files.createDirectories(StoreFolder);
			
		} catch (Exception e) {
			throw new RuntimeException("them folder that bai" + e.getMessage());
		}
	}
	private boolean isImgFile(MultipartFile file) {
		String fileEx = FilenameUtils.getExtension(file.getOriginalFilename());
		return Arrays.asList(new String[] {"png","jpg","bmp" }).contains(fileEx.trim().toLowerCase());
	}
	@Override
	public String storeFile(MultipartFile file) {
		// TODO Auto-generated method stub
		try {
			if (file.isEmpty()) {
				throw new RuntimeException("file da ton tai");		
			}
			if (!isImgFile(file)) {
				throw new RuntimeException("ban khong the tai loai anh nay");		
			}
			if (!isImgFile(file)) {
				throw new RuntimeException("ban khong the tai loai anh nay");		
			}
			float fileMP = file.getSize() / 1_000_000.0f;
			if (fileMP > 5.0f) {
				throw new RuntimeException("file phai nho hon 5Mb");
			}
			String FileEx = FilenameUtils.getExtension(file.getOriginalFilename());
			String generatedNameFile = UUID.randomUUID().toString().replace("-", "");
			generatedNameFile = generatedNameFile+"."+FileEx;
			Path path = this.StoreFolder.resolve(Paths.get(generatedNameFile)).normalize().toAbsolutePath();
			if (!path.getParent().equals(this.StoreFolder.toAbsolutePath())) {
				throw new RuntimeException("file da ton tai");
			}
			try(InputStream inputStream = file.getInputStream()){
				Files.copy(inputStream,  path, StandardCopyOption.REPLACE_EXISTING);
			}
			return generatedNameFile;
		} catch (Exception e) {
			throw new RuntimeException("lưu file that bai" + e.getMessage());
		}
	}

	@Override
	public Stream<Path> loadAll() {
		// TODO Auto-generated method stub
		try {
			return Files.walk(this.StoreFolder, 1).filter(path->!path.equals(this.StoreFolder)).map(this.StoreFolder::relativize);
		} catch (Exception e) {
			throw new RuntimeException("loi" + e.getMessage());
		}
	}

	@Override
	public byte[] readFile(String filename) {
		try {
			Path file = StoreFolder.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
				return bytes;
			}else {
				throw new RuntimeException("doc file that bai" + filename);
			}
		} catch (Exception e) {
			throw new RuntimeException("doc file that bai" + e.getMessage() + filename);
		}
	}

	@Override
	public void deleteFile() {
		// TODO Auto-generated method stub
		
	}

}
