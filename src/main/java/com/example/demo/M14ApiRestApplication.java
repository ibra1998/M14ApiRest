package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.dao.PicturesDAO;


@SpringBootApplication
public class M14ApiRestApplication {
	public static void main(String[] args) {
		SpringApplication.run(M14ApiRestApplication.class, args);
		
	}

}
