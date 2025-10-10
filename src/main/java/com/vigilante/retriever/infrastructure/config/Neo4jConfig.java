package com.vigilante.retriever.infrastructure.config;

import org.neo4j.driver.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
@EnableNeo4jRepositories(basePackages = {
	"com.vigilante.retriever.v1.drug.adapter.out.persistence.neo4j.repository",
	"com.vigilante.retriever.v1.post.adapter.out.persistence.neo4j.repository",
	"com.vigilante.retriever.v1.channel.adapter.out.persistence.neo4j.repository",
	"com.vigilante.retriever.v1.argot.adapter.out.persistence.neo4j.repository"
})
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
