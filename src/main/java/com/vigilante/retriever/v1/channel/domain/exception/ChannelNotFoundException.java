package com.vigilante.retriever.v1.channel.domain.exception;

import static com.vigilante.retriever.v1.channel.domain.enums.ChannelErrorCode.*;

import com.vigilante.retriever.common.domain.exception.NotFoundException;

public class ChannelNotFoundException extends NotFoundException {

	public ChannelNotFoundException() {
		super(CHANNEL_NOT_FOUND);
	}
}
