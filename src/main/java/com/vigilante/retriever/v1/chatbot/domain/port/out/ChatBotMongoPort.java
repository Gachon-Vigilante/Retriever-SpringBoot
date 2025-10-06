package com.vigilante.retriever.v1.chatbot.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.vigilante.retriever.v1.chatbot.domain.entity.ChatBotEntity;

public interface ChatBotMongoPort {

	List<ChatBotEntity> findAll();

	Optional<ChatBotEntity> findById(String id);

	List<ChatBotEntity> findByChannels(List<Long> channelIds);
}
