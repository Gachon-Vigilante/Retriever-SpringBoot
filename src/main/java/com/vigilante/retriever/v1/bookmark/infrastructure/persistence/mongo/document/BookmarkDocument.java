package com.vigilante.retriever.v1.bookmark.infrastructure.persistence.mongo.document;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Document(collection = "bookmarks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BookmarkDocument {

	@Id
	private String id;

	private String channelId;

	@Field("userId")
	private String telegramUserId;

	private LocalDateTime createdAt;
}
