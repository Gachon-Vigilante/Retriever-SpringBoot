package com.vigilante.retriever.v1.user.domain.code;

import com.vigilante.retriever.common.domain.code.BaseCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseCode {
	USER_NOT_FOUND("USER-4041", "사용자를 찾을 수 없습니다."),
	USER_DUPLICATED("USER-4091", "이미 존재하는 사용자입니다.");

	private final String code;
	private final String message;
}
