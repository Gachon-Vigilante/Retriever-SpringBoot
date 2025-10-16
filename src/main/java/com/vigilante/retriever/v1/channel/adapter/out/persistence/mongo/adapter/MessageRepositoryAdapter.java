package com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.channel.adapter.out.mapper.MessageMongoMapper;
import com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.document.MessageDocument;
import com.vigilante.retriever.v1.channel.adapter.out.persistence.mongo.repository.MessageMongoRepository;
import com.vigilante.retriever.v1.channel.domain.entity.MessageEntity;
import com.vigilante.retriever.v1.channel.domain.port.out.MessageMongoPort;

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
	public Optional<MessageEntity> findById(String id) {
		return messageMongoRepository.findById(id).map(messageMongoMapper::toEntity);
	}

	@Override
	public List<MessageEntity> findByChannelId(long channelId) {
		List<MessageDocument> channelDataList = messageMongoRepository.findByChannelId(channelId);
		return messageMongoMapper.getEntityList(channelDataList);
	}
}
