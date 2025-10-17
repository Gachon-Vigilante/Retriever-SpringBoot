package com.vigilante.retriever.adapter.web.openapi.config;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;

import com.vigilante.retriever.adapter.web.openapi.annotation.ApiErrorExample;
import com.vigilante.retriever.adapter.web.openapi.annotation.ApiSuccessExample;
import com.vigilante.retriever.adapter.web.openapi.registry.ErrorExampleRegistry;
import com.vigilante.retriever.adapter.web.openapi.util.ApiResponseHandler;

import io.swagger.v3.oas.models.Operation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class OpenApiExamplesCustomizer {

	private static final String DEFAULT_MEDIA_TYPE = "application/json";

	private final ErrorExampleRegistry errorRegistry;
	private final ApiResponseHandler responseHandler;

	@Bean
	public OperationCustomizer operationExamplesCustomizer() {
		return (operation, handlerMethod) -> {
			Method apiMethod = resolveApiMethod(handlerMethod);

			processErrorExamples(operation, apiMethod);
			processSuccessExamples(operation, apiMethod);

			return operation;
		};
	}

	private void processErrorExamples(Operation operation, Method apiMethod) {
		ApiErrorExample errorAnnotation = AnnotatedElementUtils.findMergedAnnotation(apiMethod,
			ApiErrorExample.class);

		if (errorAnnotation == null) {
			return;
		}

		boolean withExamples = errorAnnotation.examples();
		boolean overrideExisting = errorAnnotation.overrideExisting();

		Set<String> customStatusCodes = extractCustomStatusCodes(errorAnnotation);

		addCommonErrorResponses(operation, errorAnnotation.include(), customStatusCodes, withExamples,
			overrideExisting);

		addCustomErrorResponses(operation, errorAnnotation.custom());
	}

	private void processSuccessExamples(Operation operation, Method apiMethod) {
		ApiSuccessExample successAnnotation = AnnotatedElementUtils.findMergedAnnotation(apiMethod,
			ApiSuccessExample.class);

		if (successAnnotation == null) {
			return;
		}

		boolean withExamples = successAnnotation.examples();
		boolean overrideExisting = successAnnotation.overrideExisting();

		for (ApiSuccessExample.Success success : successAnnotation.value()) {
			responseHandler.addSuccessResponse(
				operation,
				success.code(),
				normalizeString(success.exampleKey()),
				normalizeOrDefault(success.mediaType(), DEFAULT_MEDIA_TYPE),
				normalizeString(success.description()),
				normalizeString(success.schemaRef()),
				withExamples,
				overrideExisting
			);
		}
	}

	private Set<String> extractCustomStatusCodes(ApiErrorExample errorAnnotation) {
		Set<String> statusCodes = new HashSet<>();
		if (errorAnnotation.custom() != null) {
			for (ApiErrorExample.ErrorSpec spec : errorAnnotation.custom()) {
				String code = normalizeString(spec.code());
				if (code != null) {
					statusCodes.add(code);
				}
			}
		}
		return statusCodes;
	}

	private void addCommonErrorResponses(
		Operation operation,
		String[] includedStatusCodes,
		Set<String> customStatusCodes,
		boolean withExamples,
		boolean overrideExisting
	) {
		if (includedStatusCodes == null) {
			return;
		}

		for (String statusCode : includedStatusCodes) {
			if (customStatusCodes.contains(statusCode)) {
				continue;
			}

			String exampleKey = errorRegistry.keyFor(statusCode);
			responseHandler.addErrorResponse(
				operation,
				statusCode,
				exampleKey,
				null,
				DEFAULT_MEDIA_TYPE,
				null,
				withExamples,
				overrideExisting
			);
		}
	}

	private void addCustomErrorResponses(Operation operation, ApiErrorExample.ErrorSpec[] customSpecs) {
		if (customSpecs == null) {
			return;
		}

		for (ApiErrorExample.ErrorSpec spec : customSpecs) {
			responseHandler.addErrorResponse(
				operation,
				normalizeString(spec.code()),
				normalizeString(spec.exampleKey()),
				normalizeString(spec.description()),
				normalizeOrDefault(spec.mediaType(), DEFAULT_MEDIA_TYPE),
				normalizeString(spec.schemaRef()),
				true,
				spec.override()
			);
		}
	}

	private Method resolveApiMethod(HandlerMethod handlerMethod) {
		Method implMethod = handlerMethod.getMethod();

		if (hasRequestMapping(implMethod)) {
			return implMethod;
		}

		Method interfaceMethod = findMethodOnInterfaces(handlerMethod.getBeanType(), implMethod);
		return (interfaceMethod != null) ? interfaceMethod : implMethod;
	}

	private boolean hasRequestMapping(Method method) {
		return AnnotatedElementUtils.hasAnnotation(method, RequestMapping.class);
	}

	private Method findMethodOnInterfaces(Class<?> type, Method implMethod) {
		for (Class<?> interfaceClass : type.getInterfaces()) {
			Method found = getSameSignatureIfAnnotated(interfaceClass, implMethod);
			if (found != null) {
				return found;
			}

			Method deeper = findMethodOnInterfaces(interfaceClass, implMethod);
			if (deeper != null) {
				return deeper;
			}
		}
		return null;
	}

	private Method getSameSignatureIfAnnotated(Class<?> interfaceClass, Method implMethod) {
		try {
			Method candidate = interfaceClass.getMethod(implMethod.getName(), implMethod.getParameterTypes());
			boolean hasMapping = hasRequestMapping(candidate);
			boolean hasCustomAnnotation =
				AnnotatedElementUtils.hasAnnotation(candidate, ApiErrorExample.class) ||
					AnnotatedElementUtils.hasAnnotation(candidate, ApiSuccessExample.class);

			return (hasMapping || hasCustomAnnotation) ? candidate : null;
		} catch (NoSuchMethodException ignored) {
			return null;
		}
	}

	private String normalizeString(String str) {
		if (str == null) {
			return null;
		}
		String trimmed = str.trim();
		return trimmed.isEmpty() ? null : trimmed;
	}

	private String normalizeOrDefault(String str, String defaultValue) {
		String normalized = normalizeString(str);
		return (normalized != null) ? normalized : defaultValue;
	}
}
