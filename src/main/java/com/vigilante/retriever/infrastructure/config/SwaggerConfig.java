package com.vigilante.retriever.infrastructure.config;

import java.util.Collections;
import java.util.List;

import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vigilante.retriever.adapter.web.openapi.annotation.DisableSwaggerSecurity;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openApi() {
		String jwt = "JWT";
		SecurityRequirement securityRequirement = new SecurityRequirement().addList("JWT");
		Components components = new Components().addSecuritySchemes(jwt, new SecurityScheme()
			.name(jwt)
			.type(SecurityScheme.Type.HTTP)
			.scheme("bearer")
			.bearerFormat("JWT")
		);

		Server server = new Server();
		server.setUrl("/");

		return new OpenAPI()
			.info(apiInfo())
			.servers(List.of(server))
			.addSecurityItem(securityRequirement)
			.components(components);
	}

	private Info apiInfo() {
		return new Info()
			.title("Retriever SpringBoot Server API Documentation")
			.description("""
				마약범죄 인텔리전스 구축 시스템
				
				## 주요 기능
				- **마약 홍보 현황 수집 및 단서 보존**: 마약 홍보 게시물 판별 및 텔레그램 채널 수집
				
				## 기술 스택
				- Java 21, Spring Boot 3.5.6
				- MongoDB 8.0.14, Redis 8.0.4, Neo4j 2025.05.0
				- Spring Security + JWT
				""")
			.version("v1.0.0")
			.contact(new Contact()
				.name("김지성 팀장")
				.email("softchocopie@gmail.com"));
	}

	@Bean
	public OperationCustomizer customize() {
		return (operation, handlerMethod) -> {
			DisableSwaggerSecurity methodAnnotation = handlerMethod.getMethodAnnotation(DisableSwaggerSecurity.class);
			if (methodAnnotation != null) {
				operation.setSecurity(Collections.emptyList());
			}
			return operation;
		};
	}
}
