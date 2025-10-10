package com.vigilante.retriever.v1.chatbot.adapter.out.persistence.mongo.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.vigilante.retriever.v1.chatbot.adapter.out.mapper.ChatBotMongoMapper;
import com.vigilante.retriever.v1.chatbot.adapter.out.persistence.mongo.document.ChatBotDocument;
import com.vigilante.retriever.v1.chatbot.adapter.out.persistence.mongo.repository.ChatBotMongoRepository;
import com.vigilante.retriever.v1.chatbot.domain.entity.ChatBotEntity;
import com.vigilante.retriever.v1.chatbot.domain.port.out.ChatBotMongoPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ChatBotRepositoryAdapter implements ChatBotMongoPort {

	private final ChatBotMongoRepository chatBotMongoRepository;
	private final ChatBotMongoMapper chatBotPersistenceMapper;

	@Override
	public List<ChatBotEntity> findAll() {
		List<ChatBotDocument> allChatBot = chatBotMongoRepository.findAll();
		return chatBotPersistenceMapper.getEntityList(allChatBot);
	}

	@Override
	public Optional<ChatBotEntity> findById(String id) {
		return chatBotMongoRepository.findById(id).map(chatBotPersistenceMapper::toEntity);
	}

	@Override
	public List<ChatBotEntity> findByChannels(List<Long> channelIds) {
		List<ChatBotDocument> chatBotList = chatBotMongoRepository.findByChannels(channelIds);
		return chatBotPersistenceMapper.getEntityList(chatBotList);
	}
}
