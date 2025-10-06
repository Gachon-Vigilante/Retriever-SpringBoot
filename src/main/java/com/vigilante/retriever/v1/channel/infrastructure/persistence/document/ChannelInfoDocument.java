package com.vigilante.retriever.v1.channel.infrastructure.persistence.document;

import java.time.LocalDateTime;
import java.util.List;

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
@Document(collection = "channel_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChannelInfoDocument {

	@Id
	private Long id;

	private String title;

	private String username;

	private Boolean restricted;

	private String link; // -> 초대코드로 변경

	private String description; // 모듈에서 저장 없음

	private LocalDateTime startedAt;

	@Field("discoveredAt")
	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private LocalDateTime deletedAt;

	private LocalDateTime chatbotUpdatedAt;

	private int promoCount; // 일단 보류

	private String status;

	private Catalog catalog;

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Catalog {

		@Field("chatIds")
		private List<Integer> chatIds;

		private String description;
	}
}
