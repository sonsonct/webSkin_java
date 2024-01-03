package com.WebSkin.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.WebSkin.demo.models.Product;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer>{
    List<Product> findByProductNameContaining(@Param("keyword")String productName);
    List<Product> findByCategoryid(int categoryid);
    Product findById(int id);
}
