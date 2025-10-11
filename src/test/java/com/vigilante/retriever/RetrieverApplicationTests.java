package com.vigilante.retriever;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import com.vigilante.retriever.config.TestContainersConfig;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestContainersConfig.class)
class RetrieverApplicationTests {

	@Test
	void contextLoads() {
	}

}
