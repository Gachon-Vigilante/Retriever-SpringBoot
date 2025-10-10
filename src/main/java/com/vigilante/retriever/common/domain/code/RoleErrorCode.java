package com.vigilante.retriever.common.domain.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleErrorCode implements BaseCode {
	ROLE_NOT_FOUND("ROLE-4041", "해당하는 역할이 존재하지 않습니다.");

	private final String code;
	private final String message;
}

