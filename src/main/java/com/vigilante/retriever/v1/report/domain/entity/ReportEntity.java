package com.vigilante.retriever.v1.report.domain.entity;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record ReportEntity(
	String id,
	long channelId,
	int chatId,
	String type,
	String content,
	String description,
	LocalDateTime timestamp
) {
}
