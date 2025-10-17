package com.vigilante.retriever.adapter.web.openapi.util;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ExampleKeyGenerator {

	private static final String ANCHOR = "/openapi/examples/";
	private static final String EXT_JSON = ".json";

	public String generateKey(Resource resource) {
		try {
			String uri = resource.getURI().toString();
			int idx = uri.lastIndexOf(ANCHOR);
			String relative = (idx >= 0) ? uri.substring(idx + ANCHOR.length()) : resource.getFilename();

			if (relative == null || relative.isEmpty()) {
				return fallbackKey(resource);
			}

			return convertPathToKey(relative);
		} catch (Exception e) {
			log.warn("Failed to generate key from resource: {}", resource, e);
			return fallbackKey(resource);
		}
	}

	private String convertPathToKey(String relativePath) {
		String[] parts = relativePath.split("/");
		if (parts.length == 0) {
			return "";
		}

		for (int i = 0; i < parts.length - 1; i++) {
			parts[i] = toPascalCase(parts[i]);
		}

		String fileName = parts[parts.length - 1];
		parts[parts.length - 1] = convertFileName(fileName);

		return String.join("__", parts);
	}

	private String convertFileName(String fileName) {
		if (fileName.endsWith(EXT_JSON)) {
			fileName = fileName.substring(0, fileName.length() - EXT_JSON.length());
		}

		String[] tokens = fileName.split("[^a-zA-Z0-9]+");
		if (tokens.length == 0) {
			return toPascalCase(fileName);
		}

		StringBuilder result = new StringBuilder();
		String lastToken = tokens[tokens.length - 1];
		String statusCode = lastToken.matches("\\d{3}") ? lastToken : null;

		int nameEnd = (statusCode != null) ? tokens.length - 1 : tokens.length;
		for (int i = 0; i < nameEnd; i++) {
			if (tokens[i].isBlank()) {
				continue;
			}
			if (result.length() > 0) {
				result.append("__");
			}
			result.append(toPascalCase(tokens[i]));
		}

		if (statusCode != null) {
			if (result.length() > 0) {
				result.append("__");
			}
			result.append(statusCode);
		}

		return result.toString();
	}

	private String toPascalCase(String input) {
		if (input == null || input.isBlank()) {
			return "";
		}

		String[] tokens = input.split("[^a-zA-Z0-9]+");
		StringBuilder result = new StringBuilder();

		for (String token : tokens) {
			if (token.isBlank()) {
				continue;
			}
			char firstChar = Character.toUpperCase(token.charAt(0));
			String rest = (token.length() > 1) ? token.substring(1).toLowerCase() : "";
			result.append(firstChar).append(rest);
		}

		return result.toString();
	}

	private String fallbackKey(Resource resource) {
		String filename = resource.getFilename();
		if (filename == null) {
			return "Unknown";
		}
		if (filename.endsWith(EXT_JSON)) {
			filename = filename.substring(0, filename.length() - EXT_JSON.length());
		}
		return toPascalCase(filename);
	}
}
