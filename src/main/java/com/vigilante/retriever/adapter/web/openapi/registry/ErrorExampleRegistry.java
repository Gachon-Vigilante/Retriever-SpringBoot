package com.vigilante.retriever.adapter.web.openapi.registry;

import static com.vigilante.retriever.adapter.web.openapi.constant.ExampleKeyConstant.*;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ErrorExampleRegistry {

	private final Map<String, String> statusCodeToExampleKey = Map.of(
		"400", COMMON_BAD_REQUEST_400,
		"401", COMMON_UNAUTHORIZED_401,
		"403", COMMON_FORBIDDEN_403,
		"404", COMMON_NOT_FOUND_404,
		"409", COMMON_CONFLICT_409,
		"500", COMMON_SERVER_ERROR_500
	);

	public String keyFor(String statusCode) {
		return statusCodeToExampleKey.get(statusCode);
	}
}
