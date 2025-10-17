package com.vigilante.retriever.adapter.web.openapi.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vigilante.retriever.adapter.web.openapi.util.ExampleKeyGenerator;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class OpenApiExamplesRegistrar {

	private static final String SCAN_PATTERN = "classpath*:/openapi/examples/**/*.json";

	private final ExampleKeyGenerator keyGenerator;

	private static String sortKeyOf(Resource resource) {
		try {
			return resource.getURI().toString();
		} catch (Exception ignore) {
			return resource.getFilename() != null ? resource.getFilename() : resource.toString();
		}
	}

	@Bean
	public OpenApiCustomizer registerExamples(ObjectMapper objectMapper) {
		return openApi -> {
			Components components = ensureComponents(openApi);
			Map<String, Example> examples = ensureExamplesMap(components);

			Resource[] resources = findResources();
			if (resources.length == 0) {
				log.info("No example files found for pattern: {}", SCAN_PATTERN);
				return;
			}

			Arrays.sort(resources, Comparator.comparing(OpenApiExamplesRegistrar::sortKeyOf));

			for (Resource resource : resources) {
				processResource(resource, objectMapper, examples);
			}
		};
	}

	private void processResource(Resource resource, ObjectMapper objectMapper, Map<String, Example> examples) {
		if (!resource.isReadable()) {
			log.warn("Example resource is not readable: {}", resource);
			return;
		}

		String key = keyGenerator.generateKey(resource);

		try (InputStream inputStream = resource.getInputStream()) {
			JsonNode node = objectMapper.readTree(inputStream);

			Example example = new Example();
			example.setSummary(key);
			example.setValue(node);

			Example previous = examples.put(key, example);
			if (previous != null) {
				log.warn("Duplicate example key detected: {} (overwriting). Resource: {}", key, resource);
			}

		} catch (IOException e) {
			log.warn("Failed to read example file: (key={})", key, e);
		} catch (Exception e) {
			log.warn("Unexpected error while loading example: {} (key={})", resource, key, e);
		}
	}

	private Components ensureComponents(OpenAPI openApi) {
		Components components = openApi.getComponents();
		if (components == null) {
			components = new Components();
			openApi.setComponents(components);
		}
		return components;
	}

	private Map<String, Example> ensureExamplesMap(Components components) {
		Map<String, Example> examples = components.getExamples();
		if (examples == null) {
			examples = new LinkedHashMap<>();
			components.setExamples(examples);
		}
		return examples;
	}

	private Resource[] findResources() {
		try {
			PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			return resolver.getResources(SCAN_PATTERN);
		} catch (IOException e) {
			throw new IllegalStateException("Failed to scan example resources", e);
		}
	}
}
