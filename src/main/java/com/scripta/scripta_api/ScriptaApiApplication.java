package com.scripta.scripta_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages= "com.scripta.scripta_api.repositories")
public class ScriptaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScriptaApiApplication.class, args);
	}

}
