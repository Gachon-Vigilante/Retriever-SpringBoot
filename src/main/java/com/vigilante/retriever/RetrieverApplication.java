package com.vigilante.retriever;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.annotation.PostConstruct;

@EnableMongoAuditing
@SpringBootApplication
public class RetrieverApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetrieverApplication.class, args);
	}

	@PostConstruct
	public void init() {
		// 비동기 요청에서도 SecurityContext를 유지하도록 설정
		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
	}

}
