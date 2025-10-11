package com.vigilante.retriever.v1.post.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;

@Builder
public record PostEntity(
	String id,
	String link,
	Analysis analysis,
	String analysisJobId,
	String description,
	LocalDateTime discoveredAt,
	String domain,
	String html,
	LocalDateTime publishedAt,
	String text,
	String title,
	LocalDateTime updatedAt,
	List<Similarity> similarities
) {
	@Builder
	public record Analysis(
		boolean drugsRelated,
		List<Promotion> promotions
	) {
	}

	@Builder
	public record Promotion(
		String content,
		List<Identifier> identifiers
	) {
	}

	@Builder
	public record Identifier(
		String identifier,
		String channelId,
		boolean isProcessed,
		String error
	) {
	}

	@Builder
	public record Similarity(
		String postId,
		double similarity
	) {
	}
}
