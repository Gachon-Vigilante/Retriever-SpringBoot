package com.vigilante.retriever.v1.channel.domain.exception;

import static com.vigilante.retriever.v1.channel.domain.code.MessageErrorCode.*;

import com.vigilante.retriever.common.domain.exception.NotFoundException;

public class SenderTypeNotFoundException extends NotFoundException {

	public SenderTypeNotFoundException() {
		super(SENDER_TYPE_NOT_FOUND);
	}
}
