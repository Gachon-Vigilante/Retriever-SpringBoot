package com.vigilante.retriever.v1.chatbot.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;

@Builder
public record ChatBotEntity(
	String id,
	List<Long> channels,
	List<String> chats,
	String scope,
	LocalDateTime updatedAt
) {
}
