package com.vigilante.retriever.v1.chatbot.application.query;

import java.util.List;

import com.vigilante.retriever.global.common.annotation.QueryService;
import com.vigilante.retriever.v1.chatbot.domain.entity.ChatBotEntity;
import com.vigilante.retriever.v1.chatbot.domain.exception.ChatBotNotFoundException;
import com.vigilante.retriever.v1.chatbot.domain.port.out.ChatBotPersistencePort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class ChatBotQuery {

	private final ChatBotPersistencePort chatBotPersistencePort;

	List<ChatBotEntity> findAll() {
		return chatBotPersistencePort.findAll();
	}

	ChatBotEntity getById(String id) {
		return chatBotPersistencePort.findById(id).orElseThrow(ChatBotNotFoundException::new);
	}

	List<ChatBotEntity> findByChannels(List<Long> channelIds) {
		return chatBotPersistencePort.findByChannels(channelIds);
	}
}
