package com.vigilante.retriever.v1.message.application.query;

import java.util.List;

import com.vigilante.retriever.common.domain.annotation.QueryService;
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

	public List<MessageEntity> findByChannelId(Long channelId) {
		return messageMongoPort.findByChannelId(channelId);
	}
}
