package com.vigilante.retriever.v1.bookmark.adapter.in.web.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record BookmarkInfoResponse(
	String id,
	String channelId,
	String userId,
	LocalDateTime createdAt,
	LocalDateTime updatedAt
) {
}
