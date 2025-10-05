package com.vigilante.retriever;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class RetrieverApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetrieverApplication.class, args);
	}

}
