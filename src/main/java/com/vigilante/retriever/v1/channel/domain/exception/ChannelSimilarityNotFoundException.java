package com.vigilante.retriever.v1.channel.domain.exception;

import static com.vigilante.retriever.v1.channel.domain.code.ChannelSimilarityErrorCode.*;

import com.vigilante.retriever.common.domain.exception.NotFoundException;

public class ChannelSimilarityNotFoundException extends NotFoundException {

	public ChannelSimilarityNotFoundException() {
		super(CHANNEL_SIMILARITY_NOT_FOUND);
	}
}
