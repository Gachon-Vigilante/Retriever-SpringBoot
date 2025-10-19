package com.vigilante.retriever.v1.message.adapter.out.persistence.mongo.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.message.adapter.out.mapper.MessageMongoMapper;
import com.vigilante.retriever.v1.message.adapter.out.persistence.mongo.document.MessageDocument;
import com.vigilante.retriever.v1.message.adapter.out.persistence.mongo.repository.MessageMongoRepository;
import com.vigilante.retriever.v1.message.domain.entity.MessageEntity;
import com.vigilante.retriever.v1.message.domain.port.out.MessageMongoPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MessageRepositoryAdapter implements MessageMongoPort {

	private final MessageMongoRepository messageMongoRepository;
	private final MessageMongoMapper messageMongoMapper;

	@Override
	public List<MessageEntity> findAll() {
		List<MessageDocument> allChannelData = messageMongoRepository.findAll();
		return messageMongoMapper.getEntityList(allChannelData);
	}

	@Override
	public List<MessageEntity> findByChannelId(Long channelId) {
		List<MessageDocument> channelDataList = messageMongoRepository.findByChannelId(channelId);
		return messageMongoMapper.getEntityList(channelDataList);
	}
}
