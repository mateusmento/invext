package com.invext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class InvextApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvextApiApplication.class, args);
	}
}
