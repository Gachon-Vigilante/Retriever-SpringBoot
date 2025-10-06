package com.vigilante.retriever.v1.chatbot.infrastructure.persistence.document;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Document(collection = "chat_bot")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatBotDocument {

	@Id
	private long id;

	private List<Long> channels;

	private List<String> chats;

	private String scope;

	private LocalDateTime updatedAt;
}
