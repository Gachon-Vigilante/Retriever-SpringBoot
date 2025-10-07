package com.vigilante.retriever.v1.channel.domain.exception;

import static com.vigilante.retriever.v1.channel.domain.enums.ChannelInfoErrorCode.*;

import com.vigilante.retriever.global.exception.NotFoundException;

public class ChannelInfoNotFoundException extends NotFoundException {

	public ChannelInfoNotFoundException() {
		super(CHANNEL_INFO_NOT_FOUND);
	}
}
