package com.vigilante.retriever.global.exception.dto.response;

import java.time.LocalDateTime;

import com.vigilante.retriever.global.common.code.BaseCode;

public record ErrorResponse(
	String code,
	String message,
	LocalDateTime timestamp
) {

	public static ErrorResponse of(BaseCode baseCode) {
		return new ErrorResponse(baseCode.getCode(), baseCode.getMessage(), LocalDateTime.now());
	}

	public static ErrorResponse of(BaseCode baseCode, String overrideMessage) {
		return new ErrorResponse(baseCode.getCode(), overrideMessage, LocalDateTime.now());
	}
}
