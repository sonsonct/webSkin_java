package com.WebSkin.demo.Database;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.WebSkin.demo.Repository.CustumersRepository;
import com.WebSkin.demo.models.Custumers;

@Configuration
public class Database {
	@Bean
	CommandLineRunner initDB(CustumersRepository custumersRepository) {
		
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
//				// TODO Auto-generated method stub
//				Custumers A = new Custumers("nguyen van a", "a@.com", "0999999", "kjajsj", "mádj", 0);
//				Custumers B = new Custumers("nguyen van c", "v@.com", "09997899", "kkla", "mjsjaj", 1);
//				System.out.println("inset data"+ custumersRepository.save(A));
//				System.out.println("inset data"+ custumersRepository.save(B));
			}
		};
	}
	
}
