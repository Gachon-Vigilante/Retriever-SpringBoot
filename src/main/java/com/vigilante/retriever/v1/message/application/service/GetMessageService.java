package com.vigilante.retriever.v1.message.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vigilante.retriever.v1.message.application.query.MessageMongoQuery;
import com.vigilante.retriever.v1.message.domain.entity.MessageEntity;
import com.vigilante.retriever.v1.message.domain.port.in.GetMessageUseCase;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetMessageService implements GetMessageUseCase {

	private final MessageMongoQuery messageMongoQuery;

	@Override
	public List<MessageEntity> findAll() {
		return messageMongoQuery.findAll();
	}

	@Override
	public List<MessageEntity> findByChannelId(Long channelId) {
		return messageMongoQuery.findByChannelId(channelId);
	}
}
