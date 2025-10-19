package com.vigilante.retriever.v1.message.domain.enums;

import static com.vigilante.retriever.v1.message.domain.code.MessageErrorCode.*;

import com.vigilante.retriever.common.domain.exception.NotFoundException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SenderType {
	USER("user"),
	CHANNEL("channel");

	private final String type;

	public static SenderType fromString(String type) {
		for (SenderType senderType : SenderType.values()) {
			if (senderType.type.equals(type)) {
				return senderType;
			}
		}
		throw new NotFoundException(SENDER_TYPE_NOT_FOUND);
	}
}
