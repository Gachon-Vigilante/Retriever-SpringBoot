package com.vigilante.retriever.v1.channel.domain.exception;

import static com.vigilante.retriever.v1.channel.domain.enums.ChannelDataErrorCode.*;

import com.vigilante.retriever.common.domain.exception.NotFoundException;

public class ChannelDataNotFoundException extends NotFoundException {

	public ChannelDataNotFoundException() {
		super(CHANNEL_DATA_NOT_FOUND);
	}
}
