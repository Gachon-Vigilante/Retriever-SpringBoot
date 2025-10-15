package com.vigilante.retriever.v1.channel.domain.exception;

import static com.vigilante.retriever.v1.channel.domain.code.ChannelErrorCode.*;

import com.vigilante.retriever.common.domain.exception.NotFoundException;

public class ChannelStatusNotFoundException extends NotFoundException {

	public ChannelStatusNotFoundException() {
		super(CHANNEL_STATUS_NOT_FOUND);
	}
}
