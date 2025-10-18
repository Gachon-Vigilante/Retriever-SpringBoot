package com.vigilante.retriever.v1.bookmark.domain.entity;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record BookmarkEntity(
	String id,
	String channelId,
	String userId,

	// Mongo Auditing fields
	LocalDateTime createdAt,
	LocalDateTime updatedAt
) {
	public static BookmarkEntity create(String channelId, String userId) {
		return BookmarkEntity.builder()
			.channelId(channelId)
			.userId(userId)
			.build();
	}
}
