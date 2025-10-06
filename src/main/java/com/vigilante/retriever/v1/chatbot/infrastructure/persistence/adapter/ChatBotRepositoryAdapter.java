package com.vigilante.retriever.v1.chatbot.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.vigilante.retriever.v1.chatbot.domain.entity.ChatBotEntity;
import com.vigilante.retriever.v1.chatbot.domain.port.out.ChatBotPersistencePort;
import com.vigilante.retriever.v1.chatbot.infrastructure.mapper.ChatBotPersistenceMapper;
import com.vigilante.retriever.v1.chatbot.infrastructure.persistence.document.ChatBotDocument;
import com.vigilante.retriever.v1.chatbot.infrastructure.persistence.repository.ChatBotMongoRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ChatBotRepositoryAdapter implements ChatBotPersistencePort {

	private final ChatBotMongoRepository chatBotMongoRepository;
	private final ChatBotPersistenceMapper chatBotPersistenceMapper;

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
