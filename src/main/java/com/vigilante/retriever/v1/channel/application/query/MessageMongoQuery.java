package com.vigilante.retriever.v1.channel.application.query;

import java.util.List;

import com.vigilante.retriever.common.domain.annotation.QueryService;
import com.vigilante.retriever.v1.channel.domain.entity.MessageEntity;
import com.vigilante.retriever.v1.channel.domain.exception.MessageNotFoundException;
import com.vigilante.retriever.v1.channel.domain.port.out.MessageMongoPort;

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
			.orElseThrow(MessageNotFoundException::new);
	}

	public List<MessageEntity> findByChannelId(long channelId) {
		return messageMongoPort.findByChannelId(channelId);
	}
}
