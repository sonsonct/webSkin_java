package com.WebSkin.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.WebSkin.demo.models.Custumers;

public interface CustumersRepository extends JpaRepository<Custumers, Integer> {
	List<Custumers> findByEmail(String email);
	Custumers findByEmailAndPassword(String email, String password);
}
