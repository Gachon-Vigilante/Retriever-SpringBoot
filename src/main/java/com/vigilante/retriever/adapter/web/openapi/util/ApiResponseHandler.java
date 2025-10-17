package com.vigilante.retriever.adapter.web.openapi.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Component
public class ApiResponseHandler {

	private static final String DEFAULT_MEDIA_TYPE = "application/json";
	private static final String EXAMPLE_REF_PREFIX = "#/components/examples/";

	public void addSuccessResponse(
		Operation operation,
		String statusCode,
		String exampleKey,
		String mediaTypeStr,
		String description,
		String schemaRef,
		boolean withExamples,
		boolean overrideExisting
	) {
		ApiResponses responses = getOrCreateResponses(operation);

		ApiResponse response = responses.get(statusCode);
		if (response == null) {
			response = new ApiResponse().description("");
			responses.put(statusCode, response);
		}

		if (isNotBlank(description)) {
			response.setDescription(description);
		}

		Content content = getOrCreateContent(response);
		MediaType mediaType = getOrCreateMediaType(content, mediaTypeStr);

		if (isNotBlank(schemaRef)) {
			if (overrideExisting || mediaType.getSchema() == null) {
				mediaType.setSchema(new Schema<>().$ref(schemaRef));
			}
		}

		if (withExamples && isNotBlank(exampleKey)) {
			addExampleReference(mediaType, exampleKey);
		}
	}

	public void addErrorResponse(
		Operation operation,
		String statusCode,
		String exampleKey,
		String description,
		String mediaTypeStr,
		String schemaRef,
		boolean withExamples,
		boolean overrideExisting
	) {
		ApiResponses responses = getOrCreateResponses(operation);

		ApiResponse existing = responses.get(statusCode);
		if (existing != null && !overrideExisting) {
			return;
		}

		ApiResponse response = (existing != null) ? existing : new ApiResponse();
		if (isNotBlank(description)) {
			response.setDescription(description);
		} else if (response.getDescription() == null) {
			response.setDescription("");
		}
		responses.put(statusCode, response);

		Content content = getOrCreateContent(response);
		MediaType mediaType = getOrCreateMediaType(content, mediaTypeStr);

		if (isNotBlank(schemaRef)) {
			mediaType.setSchema(new Schema<>().$ref(schemaRef));
		}

		if (withExamples && isNotBlank(exampleKey)) {
			addExampleReference(mediaType, exampleKey);
		}
	}

	private void addExampleReference(MediaType mediaType, String exampleKey) {
		Example example = new Example();
		example.set$ref(EXAMPLE_REF_PREFIX + exampleKey);

		mediaType.setExample(null);

		Map<String, Example> currentExamples = mediaType.getExamples();
		if (currentExamples == null || currentExamples.isEmpty()) {
			mediaType.addExamples(exampleKey, example);
			return;
		}

		LinkedHashMap<String, Example> reordered = new LinkedHashMap<>();
		reordered.put(exampleKey, example);
		for (Map.Entry<String, Example> entry : currentExamples.entrySet()) {
			if (!entry.getKey().equals(exampleKey)) {
				reordered.put(entry.getKey(), entry.getValue());
			}
		}
		mediaType.setExamples(reordered);
	}

	private ApiResponses getOrCreateResponses(Operation operation) {
		ApiResponses responses = operation.getResponses();
		if (responses == null) {
			responses = new ApiResponses();
			operation.setResponses(responses);
		}
		return responses;
	}

	private Content getOrCreateContent(ApiResponse response) {
		Content content = response.getContent();
		if (content == null) {
			content = new Content();
			response.setContent(content);
		}
		return content;
	}

	private MediaType getOrCreateMediaType(Content content, String mediaTypeStr) {
		String mediaType = normalizeMediaType(mediaTypeStr);

		if (!"*/*".equals(mediaType) && content.containsKey("*/*")) {
			MediaType starType = content.get("*/*");
			if (isEmptyMediaType(starType)) {
				content.remove("*/*");
			} else {
				LinkedHashMap<String, MediaType> reordered = new LinkedHashMap<>();
				reordered.put(mediaType, content.getOrDefault(mediaType, new MediaType()));
				for (Map.Entry<String, MediaType> entry : content.entrySet()) {
					if (!entry.getKey().equals(mediaType)) {
						reordered.put(entry.getKey(), entry.getValue());
					}
				}
				content.clear();
				content.putAll(reordered);
				return content.get(mediaType);
			}
		}

		return content.computeIfAbsent(mediaType, k -> new MediaType());
	}

	private String normalizeMediaType(String mediaType) {
		if (mediaType == null || mediaType.trim().isEmpty()) {
			return DEFAULT_MEDIA_TYPE;
		}
		return mediaType.trim();
	}

	private boolean isEmptyMediaType(MediaType mediaType) {
		return mediaType.getSchema() == null &&
			mediaType.getExample() == null &&
			(mediaType.getExamples() == null || mediaType.getExamples().isEmpty());
	}

	private boolean isNotBlank(String str) {
		return str != null && !str.isBlank();
	}
}
