package com.vigilante.retriever.v1.channel.domain.enums;

import com.vigilante.retriever.v1.channel.domain.exception.SenderTypeNotFoundException;

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
		throw new SenderTypeNotFoundException();
	}
}
