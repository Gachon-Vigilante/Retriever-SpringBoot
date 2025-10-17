package com.vigilante.retriever.adapter.web.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vigilante.retriever.adapter.web.code.CommonSuccessCode;
import com.vigilante.retriever.common.domain.code.BaseCode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {
	private final boolean success;
	private final String code;
	private final String message;
	private final T data;

	@Builder.Default
	private final LocalDateTime timestamp = LocalDateTime.now();

	public static CommonResponse<Void> success() {
		return successWithCode(CommonSuccessCode.SUCCESS);
	}

	public static <T> CommonResponse<T> success(T data) {
		return successWithCode(CommonSuccessCode.SUCCESS, data);
	}

	public static <T> CommonResponse<T> retrieved(T data) {
		return successWithCode(CommonSuccessCode.RETRIEVED, data);
	}

	public static CommonResponse<Void> updated() {
		return successWithCode(CommonSuccessCode.UPDATED);
	}

	public static <T> CommonResponse<T> updated(T data) {
		return successWithCode(CommonSuccessCode.UPDATED, data);
	}

	public static CommonResponse<Void> deleted() {
		return successWithCode(CommonSuccessCode.DELETED);
	}

	public static <T> CommonResponse<T> created(T data) {
		return successWithCode(CommonSuccessCode.CREATED, data);
	}

	public static CommonResponse<Void> created() {
		return successWithCode(CommonSuccessCode.CREATED);
	}

	public static <T> CommonResponse<T> successWithCode(BaseCode successCode, T data) {
		return CommonResponse.<T>builder()
			.success(true)
			.code(successCode.getCode())
			.message(successCode.getMessage())
			.data(data)
			.build();
	}

	public static CommonResponse<Void> successWithCode(BaseCode successCode) {
		return CommonResponse.<Void>builder()
			.success(true)
			.code(successCode.getCode())
			.message(successCode.getMessage())
			.build();
	}

	public static <T> CommonResponse<T> error(BaseCode errorCode) {
		return CommonResponse.<T>builder()
			.success(false)
			.code(errorCode.getCode())
			.message(errorCode.getMessage())
			.build();
	}

	public static <T> CommonResponse<T> error(BaseCode errorCode, String overrideMessage) {
		return CommonResponse.<T>builder()
			.success(false)
			.code(errorCode.getCode())
			.message(overrideMessage)
			.build();
	}
}
