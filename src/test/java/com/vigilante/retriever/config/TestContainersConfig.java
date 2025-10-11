package com.vigilante.retriever.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestContainersConfig {

	private static final String MONGO_IMAGE = "mongo:8.0.14";
	private static final String NEO4J_IMAGE = "neo4j:2025.05.0";
	private static final String REDIS_IMAGE = "redis:8.0.4";

	private static final String NEO4J_PASSWORD = "testPassword123";

	private static final MongoDBContainer mongoDBContainer;
	private static final Neo4jContainer<?> neo4jContainer;
	private static final GenericContainer<?> redisContainer;

	static {
		// MongoDB 컨테이너 초기화
		mongoDBContainer = new MongoDBContainer(DockerImageName.parse(MONGO_IMAGE))
			.withExposedPorts(27017)
			.withReuse(true);
		mongoDBContainer.start();

		// Neo4j 컨테이너 초기화
		neo4jContainer = new Neo4jContainer<>(DockerImageName.parse(NEO4J_IMAGE))
			.withAdminPassword(NEO4J_PASSWORD)
			.withReuse(true);
		neo4jContainer.start();

		// Redis 컨테이너 초기화
		redisContainer = new GenericContainer<>(DockerImageName.parse(REDIS_IMAGE))
			.withExposedPorts(6379)
			.withReuse(true);
		redisContainer.start();
	}

	/**
	 * Testcontainers의 동적 포트를 Spring 속성에 등록
	 */
	@DynamicPropertySource
	static void registerDynamicProperties(DynamicPropertyRegistry registry) {
		// MongoDB 설정
		registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
		registry.add("spring.data.mongodb.database", () -> "test");

		// Neo4j 설정
		registry.add("spring.neo4j.uri", neo4jContainer::getBoltUrl);
		registry.add("spring.neo4j.authentication.username", () -> "neo4j");
		registry.add("spring.neo4j.authentication.password", () -> NEO4J_PASSWORD);

		// Redis 설정
		registry.add("spring.data.redis.host", redisContainer::getHost);
		registry.add("spring.data.redis.port", () -> redisContainer.getMappedPort(6379));
		registry.add("spring.data.redis.password", () -> "");
	}

	@Bean
	@ServiceConnection
	public MongoDBContainer mongoDBContainer() {
		return mongoDBContainer;
	}

	@Bean
	@ServiceConnection
	public Neo4jContainer<?> neo4jContainer() {
		return neo4jContainer;
	}

	@Bean
	@ServiceConnection(name = "redis")
	public GenericContainer<?> redisContainer() {
		return redisContainer;
	}
}

