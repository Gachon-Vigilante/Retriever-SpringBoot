package com.vigilante.retriever.v1.report.adapter.in.web.dto;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record ReportInfoResponse(
	String id,
	long channelId,
	int chatId,
	String type,
	String content,
	String description,
	LocalDateTime timestamp
) {
}
