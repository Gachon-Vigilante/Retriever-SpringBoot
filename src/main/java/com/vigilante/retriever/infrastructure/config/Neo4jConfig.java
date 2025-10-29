package com.vigilante.retriever.infrastructure.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.core.convert.Neo4jConversions;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.lang.Nullable;

@Configuration
@EnableNeo4jRepositories(basePackages = {"com.vigilante.retriever.v1.*.adapter.out.persistence.neo4j.repository"})
public class Neo4jConfig {

	private final Driver driver;

	public Neo4jConfig(Driver driver) {
		this.driver = driver;
	}

	@Bean
	public Neo4jClient neo4jClient() {
		return Neo4jClient.create(driver);
	}

	@Bean
	public Neo4jTemplate neo4jTemplate(Neo4jClient neo4jClient) {
		return new Neo4jTemplate(neo4jClient);
	}
}
