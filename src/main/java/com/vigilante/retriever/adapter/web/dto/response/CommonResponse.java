package com.vigilante.retriever.adapter.web.dto.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vigilante.retriever.adapter.web.code.CommonSuccessCode;
import com.vigilante.retriever.common.domain.code.BaseCode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
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

	public static ResponseEntity<CommonResponse<Void>> success() {
		return ResponseEntity.ok().body(successWithCode(CommonSuccessCode.SUCCESS));
	}

	public static <T> ResponseEntity<CommonResponse<T>> success(T data) {
		return ResponseEntity.ok().body(successWithCode(CommonSuccessCode.SUCCESS, data));
	}

	public static <T> ResponseEntity<CommonResponse<T>> retrieved(T data) {
		return ResponseEntity.ok().body(successWithCode(CommonSuccessCode.RETRIEVED, data));
	}

	public static ResponseEntity<CommonResponse<Void>> updated() {
		return ResponseEntity.ok().body(successWithCode(CommonSuccessCode.UPDATED));
	}

	public static <T> ResponseEntity<CommonResponse<T>> updated(T data) {
		return ResponseEntity.ok().body(successWithCode(CommonSuccessCode.UPDATED, data));
	}

	public static ResponseEntity<CommonResponse<Void>> deleted() {
		return ResponseEntity.ok().body(successWithCode(CommonSuccessCode.DELETED));
	}

	public static <T> ResponseEntity<CommonResponse<T>> created(T data) {
		return ResponseEntity.status(HttpStatus.CREATED).body(successWithCode(CommonSuccessCode.CREATED, data));
	}

	public static ResponseEntity<CommonResponse<Void>> created() {
		return ResponseEntity.status(HttpStatus.CREATED).body(successWithCode(CommonSuccessCode.CREATED));
	}

	private static <T> CommonResponse<T> successWithCode(BaseCode successCode, T data) {
		return CommonResponse.<T>builder()
			.success(true)
			.code(successCode.getCode())
			.message(successCode.getMessage())
			.data(data)
			.build();
	}

	private static CommonResponse<Void> successWithCode(BaseCode successCode) {
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
