package com.vigilante.retriever.v1.chatbot.application.query;

import java.util.List;

import com.vigilante.retriever.global.common.annotation.QueryService;
import com.vigilante.retriever.v1.chatbot.domain.entity.ChatBotEntity;
import com.vigilante.retriever.v1.chatbot.domain.exception.ChatBotNotFoundException;
import com.vigilante.retriever.v1.chatbot.domain.port.out.ChatBotMongoPort;

import lombok.RequiredArgsConstructor;

@QueryService
@RequiredArgsConstructor
public class ChatBotQuery {

	private final ChatBotMongoPort chatBotMongoPort;

	List<ChatBotEntity> findAll() {
		return chatBotMongoPort.findAll();
	}

	ChatBotEntity getById(String id) {
		return chatBotMongoPort.findById(id).orElseThrow(ChatBotNotFoundException::new);
	}

	List<ChatBotEntity> findByChannels(List<Long> channelIds) {
		return chatBotMongoPort.findByChannels(channelIds);
	}
}
