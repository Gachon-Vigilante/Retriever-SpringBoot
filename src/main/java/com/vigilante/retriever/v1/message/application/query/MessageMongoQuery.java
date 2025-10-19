package com.vigilante.retriever.v1.message.application.query;

import static com.vigilante.retriever.v1.message.domain.code.MessageErrorCode.*;

import java.util.List;

import com.vigilante.retriever.common.domain.annotation.QueryService;
import com.vigilante.retriever.common.domain.exception.NotFoundException;
import com.vigilante.retriever.v1.message.domain.entity.MessageEntity;
import com.vigilante.retriever.v1.message.domain.port.out.MessageMongoPort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class MessageMongoQuery {

	private final MessageMongoPort messageMongoPort;

	public List<MessageEntity> findAll() {
		return messageMongoPort.findAll();
	}

	public MessageEntity getById(String id) {
		return messageMongoPort.findById(id)
			.orElseThrow(() -> new NotFoundException(MESSAGE_NOT_FOUND));
	}

	public List<MessageEntity> findByChannelId(long channelId) {
		return messageMongoPort.findByChannelId(channelId);
	}
}
