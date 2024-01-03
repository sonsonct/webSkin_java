package com.WebSkin.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@PostMapping("/addprd")
	public ResponseEntity<ResponseOBJ> addProducts(@RequestBody Product newprd) {
		try {
			
			
			
			if (newprd.getProductName().isEmpty() || newprd.getImg_extra().isEmpty() || newprd.getCategoryid()==0 || newprd.getProduct_date()==null  ) {
				return ResponseEntity.status(HttpStatus.OK).body(
						new ResponseOBJ("false", "Vui long nhap day du thong tin", "")
					);
			}else {
				Product data = prdRepository.save(newprd);
				return ResponseEntity.status(HttpStatus.OK).body(
						new ResponseOBJ("ok", "Thêm thanh cong", data)
					);
			}
			
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseOBJ("false",  "Thêm that bai", e)
			);
		}
        
    }
	@PutMapping("/updateprd")
	public ResponseEntity<ResponseOBJ> putProducts(@RequestBody Product dataprd, @RequestParam Integer id) {
		try {
				Optional<Product> data = prdRepository.findById(id);
				if (data.isPresent()) {
				    Product product = data.get();
				    product.setProductName(dataprd.getProductName());
				    product.setImg_main(dataprd.getImg_main());
				    product.setQuantity(dataprd.getQuantity());
				    product.setCategoryid(dataprd.getCategoryid());
				    product.setProduct_date(dataprd.getProduct_date());
				    product.setDescribe(dataprd.getDescribe());
				    // Save the updated product
				    prdRepository.save(product);
				} else {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
							new ResponseOBJ("flse", "Sản phẩm không tồn tại", "")
						);
				}
				return ResponseEntity.status(HttpStatus.OK).body(
						new ResponseOBJ("ok", "Sửa thanh cong", data)
					);
			
			
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ResponseOBJ("false", "Sửa that bai", e)
			);
		}
        
    }
	@DeleteMapping("/deleteprd")
	public ResponseEntity<ResponseOBJ> deleteProductsById(@RequestParam Integer id) {
		try {
			Product data =prdRepository.findById(id).orElse(null);
			if (data != null) {
				prdRepository.delete(data);
				return ResponseEntity.status(HttpStatus.OK).body(
						new ResponseOBJ("ok", "Xoa thanh cong", "")
					);
	        } else {
	        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
	    				new ResponseOBJ("false", "San pham khong ton tai", "")
	    		);
	        }
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ResponseOBJ("false", "Xoa that bai", "")
			);
		}
        
    }
}
