package com.vigilante.retriever;

import com.vigilante.retriever.config.TestContainersConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestContainersConfig.class)
class RetrieverApplicationTests {

	@Test
	void contextLoads() {
	}

}
