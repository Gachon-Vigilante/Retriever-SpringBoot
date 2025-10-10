package com.vigilante.retriever.v1.user.domain.code;

import com.vigilante.retriever.common.domain.code.BaseCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RefreshTokenErrorCode implements BaseCode {
	REFRESH_TOKEN_NOT_FOUND("TOKEN-4041", "리프레쉬 토큰이 존재하지 않습니다");

	private final String code;
	private final String message;
}
