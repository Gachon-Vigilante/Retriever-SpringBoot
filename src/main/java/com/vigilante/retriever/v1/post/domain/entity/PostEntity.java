package com.vigilante.retriever.v1.post.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;

@Builder
public record PostEntity(
	String id,
	String link,
	String tag,
	String siteName,
	String title,
	String content,
	String source,
	List<String> promoSiteLink,
	List<String> promoChannelId,
	int clusterLabel,
	String author,
	boolean deleted,
	LocalDateTime timestamp,
	LocalDateTime deletedAt,

	// Mongo Auditing fields
	LocalDateTime createdAt,
	LocalDateTime updatedAt
) {
}
