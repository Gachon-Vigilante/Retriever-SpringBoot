package com.vigilante.retriever.v1.chatbot.domain.code;

import com.vigilante.retriever.common.domain.code.BaseCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChatBotErrorCode implements BaseCode {
	CHAT_BOT_NOT_FOUND("CHAT_BOT-4041", "해당하는 챗봇을 찾을 수 없습니다.");

	private final String code;
	private final String message;
}
