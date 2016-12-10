package com.mirae;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mirae.test.dao.TestDAO;

@SpringBootApplication
public class MiraeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiraeApplication.class, args);
	}

	@Bean
	public CommandLineRunner runOnStartUp(TestDAO dao) {
		return (args) -> {
			System.out.println(dao.selectList(null));
		};
	}

}
