package com.vigilante.retriever.v1.chatbot.domain.exception;

import static com.vigilante.retriever.v1.chatbot.domain.enums.ChatBotErrorCode.*;

import com.vigilante.retriever.common.domain.exception.NotFoundException;

public class ChatBotNotFoundException extends NotFoundException {

	public ChatBotNotFoundException() {
		super(CHAT_BOT_NOT_FOUND);
	}
}
