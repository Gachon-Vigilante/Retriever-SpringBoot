package com.vigilante.retriever.v1.argot.domain.enums;

import com.vigilante.retriever.common.domain.code.BaseCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArgotErrorCode implements BaseCode {
	ARGOT_NOT_FOUND("ARGOT-4041", "해당하는 마약 은어를 찾을 수 없습니다.");

	private final String code;
	private final String message;
}
