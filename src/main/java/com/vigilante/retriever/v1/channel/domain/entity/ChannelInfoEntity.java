package com.vigilante.retriever.v1.channel.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;

@Builder
public record ChannelInfoEntity(
	Long id,
	String title,
	String username,
	Boolean restricted,
	String link,
	String description,
	LocalDateTime startedAt,
	LocalDateTime createdAt,
	LocalDateTime updatedAt,
	LocalDateTime deletedAt,
	LocalDateTime chatbotUpdatedAt,
	int promoCount,
	String status,
	Catalog catalog
) {

	@Builder
	public record Catalog(
		List<Integer> chatIds,
		String description
	) {
	}
}
