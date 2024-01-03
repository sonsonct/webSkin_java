package com.WebSkin.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.WebSkin.demo.Repository.ProductRepository;
import com.WebSkin.demo.models.Product;
import com.WebSkin.demo.models.ResponseOBJ;

@RestController
@RequestMapping(path = "/api/v1/product")
@CrossOrigin(origins = "http://localhost:3000")

public class ProductContronller {
//	@GetMapping("/hello")
//	public String hello() {
//		
//		return "helooo";
//	}
	@Autowired
	private ProductRepository prdRepository;
	
	@GetMapping("/getallproduct")
	public ResponseEntity<ResponseOBJ> getAllproduct(){
		
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseOBJ("ok", "thanh cong", prdRepository.findAll())
		);
	}
	@GetMapping("/{productname}")
	public ResponseEntity<ResponseOBJ> getProductByName(@PathVariable String productname){
		List<Product> list = prdRepository.findByProductNameContaining(productname);
		
		if(list.size()>0	) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseOBJ("ok", "tim thanh cong", list)
			);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseOBJ("false", "không tim thay sản phẩm có tên "+productname, "")
			);
		}
	}
	@GetMapping("/prd/{id}")
	public ResponseEntity<ResponseOBJ> getProductById(@PathVariable Integer id){
		Optional<Product> list = prdRepository.findById(id);
		if(list.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseOBJ("ok", "tim thanh cong", list)
			);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseOBJ("false", "không tim thay sản phẩm", "")
			);
		}
	}
	@GetMapping("/category/{id}")
	public ResponseEntity<ResponseOBJ> getProductByCategoryId(@PathVariable Integer id){
		List<Product> list = prdRepository.findByCategoryid(id);
		if(list.size()>0) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseOBJ("ok", "tim thanh cong", list)
			);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseOBJ("false", "không tim thay sản phẩm", "")
			);
		}
	}
	
}
