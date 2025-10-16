package com.vigilante.retriever.v1.channel.domain.code;

import com.vigilante.retriever.common.domain.code.BaseCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageErrorCode implements BaseCode {
	MESSAGE_NOT_FOUND("MESSAGE-4041", "해당하는 메시지를 찾을 수 없습니다."),
	SENDER_TYPE_NOT_FOUND("MESSAGE-4042", "해당하는 발신자 타입을 찾을 수 없습니다.");

	private final String code;
	private final String message;
}
