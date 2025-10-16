package com.vigilante.retriever.v1.channel.domain.exception;

import static com.vigilante.retriever.v1.channel.domain.code.MessageErrorCode.*;

import com.vigilante.retriever.common.domain.exception.NotFoundException;

public class MessageNotFoundException extends NotFoundException {

	public MessageNotFoundException() {
		super(MESSAGE_NOT_FOUND);
	}
}
