package com.WebSkin.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.WebSkin.demo.Repository.CategoryRepository;
import com.WebSkin.demo.models.Category;
import com.WebSkin.demo.models.ResponseOBJ;

@RestController
@RequestMapping(path = "/api/v1/category")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
	@Autowired
	CategoryRepository categoryrep;
	@GetMapping("/getAll")
	public ResponseEntity<ResponseOBJ> getAll(){
		try {
			List<Category> c= categoryrep.findAll();
			return ResponseEntity.ok(new ResponseOBJ("true", "tim thay chuyen muc thanh cong", c));
		} catch (Exception e) {
			return ResponseEntity.ok(new ResponseOBJ("false", "tim thay chuyen muc that bai", null));
		}
	}
}
