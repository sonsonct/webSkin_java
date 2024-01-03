package com.WebSkin.demo.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.WebSkin.demo.Services.IStorageService;
import com.WebSkin.demo.models.ResponseOBJ;

@Controller
@RequestMapping("/api/v1/upload")
public class UploadfileController {
	@Autowired
	private IStorageService iStorageService;
	@PostMapping("")
	public ResponseEntity<ResponseOBJ> uploadfile(@RequestParam("file")MultipartFile file ){
		try {
			String filename =  iStorageService.storeFile(file);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseOBJ("ok", "them thanh cong file ", filename));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseOBJ("false", "them that bai ", ""));

		}
	}
	@GetMapping("/files/{filename:..+}")
	public ResponseEntity<byte[]> readfile(@PathVariable String filename){
		try {
			byte[] b = iStorageService.readFile(filename);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(b);
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
		
	}
	@GetMapping("")
	public ResponseEntity<ResponseOBJ> readallfile(){
		try {
			List<String> url = iStorageService.loadAll().map(path->{
				String urlpath = MvcUriComponentsBuilder.fromMethodName(UploadfileController.class, "readfile", path.getFileName().toString()).build().toUri().toString();
				return urlpath;
			}).collect(Collectors.toList());
			return ResponseEntity.ok(new ResponseOBJ("ok", "thanh cong", url));
		} catch (Exception e) {
			return ResponseEntity.ok(new ResponseOBJ("false", "loi load all anh", new String[] {}));
		}
		
	}

}
