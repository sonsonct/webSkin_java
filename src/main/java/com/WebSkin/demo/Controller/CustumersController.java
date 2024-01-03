package com.WebSkin.demo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.WebSkin.demo.Repository.CustumersRepository;
import com.WebSkin.demo.models.Custumers;
import com.WebSkin.demo.models.ResponseOBJ;

@RestController
@RequestMapping(path = "/api/v1/Custumers")
@CrossOrigin(origins = "http://localhost:3000")
	
public class CustumersController {
	@Autowired
	private CustumersRepository CustumersRepository;
//	@GetMapping("/hello")
//	public String hello() {
//		
//		return "helooo";
//	}
	@PostMapping("/login")
	public ResponseEntity<ResponseOBJ> Login(@RequestBody Map<String, String> request){
		String email = request.get("email");
	    String password = request.get("password");
		Custumers custumers = CustumersRepository.findByEmailAndPassword(email, password);
		if(custumers!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseOBJ("true", "dang nhap thanh cong", custumers)
			);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseOBJ("true", "sai tai khoan mat khau", "")
			);
		}
		
	}
	@PostMapping("/register")
	public ResponseEntity<ResponseOBJ> insertCustumers(@RequestBody Custumers custumers) {
		List<Custumers> fCustumers = CustumersRepository.findByEmail(custumers.getEmail().trim());
		if (fCustumers.size()>0) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseOBJ("false", "thêm thất bại email  "+ custumers.getEmail() +" đã đăng ký", "")
			);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseOBJ("ok", "them thanh cong", CustumersRepository.save(custumers))
			);
		}
		
	}
}
