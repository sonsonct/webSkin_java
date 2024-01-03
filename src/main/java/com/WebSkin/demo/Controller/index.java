package com.WebSkin.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.WebSkin.demo.Repository.CustumersRepository;
import com.WebSkin.demo.models.Custumers;
import com.WebSkin.demo.models.Product;
import com.WebSkin.demo.models.ResponseOBJ;

@RestController
@RequestMapping(path = "/api/v2/Custumers")

public class index {
	@Autowired
	private CustumersRepository CustumersRepository;
	@GetMapping("/hello")
	public String hello() {
		
		return "helooo";
	}
	@GetMapping("/getALLCustumers")
	public List<Custumers> getCustumers() {
		
		return CustumersRepository.findAll();
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResponseOBJ> getCustumersById(@PathVariable Integer id) {
		
		Optional<Custumers> list =  CustumersRepository.findById(id);
		if(list.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseOBJ("ok", "tim thanh cong", list)
			);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseOBJ("false", "khong tim thay id = "+id, "")
			);
		}
	}
	@PostMapping("/insertCustumers")
	public ResponseEntity<ResponseOBJ> insertCustumers(@RequestBody Custumers custumers) {
		List<Custumers> fCustumers = CustumersRepository.findByEmail(custumers.getEmail().trim());
		if (fCustumers.size()>1) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
					new ResponseOBJ("false", "them that bai email "+ custumers.getEmail() +" da ton tai", "")
			);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseOBJ("ok", "them thanh cong", CustumersRepository.save(custumers))
			);
		}
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<ResponseOBJ> updateCustumers(@RequestBody Custumers newcustumers, @PathVariable Integer id) {
		Custumers updatecuCustumers = CustumersRepository.findById(id)
		.map(
			Custumers->{
				Custumers.setName(newcustumers.getName());
				Custumers.setEmail(newcustumers.getEmail());
				Custumers.setPhone_number(newcustumers.getPhone_number());
				Custumers.setAddress(newcustumers.getAddress());
				Custumers.setPassword(newcustumers.getPassword());
				Custumers.setVerify_status(newcustumers.getVerify_status());
				
				return CustumersRepository.save(Custumers);
			}
		).orElseGet(()->{
			newcustumers.setId(id);
			return CustumersRepository.save(newcustumers);
		});
		
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseOBJ("ok", "sua thanh cong", updatecuCustumers)
		);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseOBJ> deleteCustumersById(@PathVariable Integer id) {
		boolean check = CustumersRepository.existsById(id);
		if(check==true) {
			CustumersRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseOBJ("ok", "xoa thanh cong", "")
			);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseOBJ("false", "khong tim thay id = "+id, "")
			);
		}
	}
}
