package com.WebSkin.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.WebSkin.demo.models.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{
	List<Cart> findByIdCustumer(int idCustumer);
	Cart findByIdProductAndIdCustumer(int idProduct, int idCustumer);
}
