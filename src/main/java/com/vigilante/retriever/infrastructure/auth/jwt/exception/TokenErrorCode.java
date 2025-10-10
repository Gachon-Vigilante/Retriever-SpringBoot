package com.vigilante.retriever.infrastructure.auth.jwt.exception;

import com.vigilante.retriever.common.domain.code.BaseCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenErrorCode implements BaseCode {
	INVALID_REFRESH_TOKEN_ERROR("TOKEN-4001", "잘못된 리프레쉬 토큰입니다"),
	REFRESH_TOKEN_USER_ID_MISMATCH_ERROR("TOKEN-4002", "리프레쉬 토큰의 사용자 정보가 일치하지 않습니다"),
	REFRESH_TOKEN_SIGNATURE_ERROR("TOKEN-4003", "리프레쉬 토큰의 서명이 잘못되었습니다"),
	UNSUPPORTED_REFRESH_TOKEN_ERROR("TOKEN-4004", "지원하지 않는 리프레쉬 토큰입니다"),
	REFRESH_TOKEN_EMPTY_ERROR("TOKEN-4005", "리프레쉬 토큰이 비어있습니다"),
	AUTHENTICATION_CODE_EXPIRED("TOKEN-4011", "인가코드가 만료되었습니다"),
	EMPTY_OR_INVALID_TOKEN("TOKEN-4012", "토큰이 존재하지 않거나 유효하지 않습니다"),
	REFRESH_TOKEN_EXPIRED_ERROR("TOKEN-4013", "리프레쉬 토큰이 만료되었습니다"),
	UNKNOWN_REFRESH_TOKEN_ERROR("TOKEN-5001", "알 수 없는 리프레쉬 토큰 오류가 발생했습니다");

	private final String code;
	private final String message;
}

